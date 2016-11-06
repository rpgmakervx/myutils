package org.easyarch.myutils.cp.ds;/**
 * Description : 
 * Created by YangZH on 16-11-5
 *  下午10:04
 */

import org.easyarch.myutils.cp.cfg.PoolConfig;
import org.easyarch.myutils.db.cfg.ConnConfig;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Description :
 * Created by code4j on 16-11-5
 * 下午10:04
 */

public class DBCPool extends DataSourceAdapter {
    private List<Connection> conpool;
    private BlockingQueue<Connection> idleQueue;
    private int maxPoolSize;
    private int minIdle;
    private int maxIdle;
    private long keepAliveTime;

    public DBCPool(BlockingQueue queue) {
        maxPoolSize = PoolConfig.getMaxPoolSize();
        minIdle = PoolConfig.getMinIdle();
        maxIdle = PoolConfig.getMaxIdle();
        keepAliveTime = PoolConfig.getKeepAliveTime();
        conpool = new CopyOnWriteArrayList<Connection>();
        idleQueue = queue;
        initConnection();
    }

    public DBCPool(int maxPoolSize, int minIdle,
                   int maxIdle, long keepAliveTime, BlockingQueue queue) {
        PoolConfig.config(maxPoolSize, minIdle, maxIdle, keepAliveTime);
        this.maxPoolSize = PoolConfig.getMaxPoolSize();
        this.minIdle = PoolConfig.getMinIdle();
        this.maxIdle = PoolConfig.getMaxIdle();
        this.keepAliveTime = PoolConfig.getKeepAliveTime();
        conpool = new CopyOnWriteArrayList<Connection>();
        idleQueue = queue;
        initConnection();
    }

    private void initConnection() {
        for (int index = 0; index < minIdle; index++) {
            try {
                Connection conn = createConnection();
                idleQueue.offer(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Connection getConnection() {
        Connection conn;
        try {
            conn = idleQueue.poll(keepAliveTime, TimeUnit.MILLISECONDS);
            System.out.println(Thread.currentThread().getName()+"从队列中获取到一个资源:"+conn);
            if (conn == null||idleQueue.isEmpty()) {
                ensureMinIdle();
            }
            if (conpool.size() < maxPoolSize) {
                conn = conn==null?createConnection():conn;
                conpool.add(conn);
            } else {
                throw new RuntimeException("db connection pool was full");
            }
            Connection cn = (Connection) Proxy.newProxyInstance(getClass().getClassLoader(),
                    new Class[]{Connection.class}, new ConnectionProxy(conn));
            return cn;
//            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void ensureMinIdle() {
        while (idleQueue.size() < minIdle) {
            idleQueue.offer(createConnection());
        }
    }

    protected Connection createConnection() {
        try {
            return DriverManager.getConnection(ConnConfig.getUrl()
                    , ConnConfig.getUser(), ConnConfig.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException("fail to create connection:\n" + e.getMessage());
        }
    }

    protected void recover(Connection conn) {
        if (conn != null) {
            idleQueue.offer(conn);
            conpool.remove(conn);
        }
    }

    protected void release(Connection conn) {
        if (conn != null) {
            idleQueue.remove(conn);
            conpool.remove(conn);
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        throw new RuntimeException("don't support this way to get connection");
    }

    class ConnectionProxy implements InvocationHandler {
        private static final String CLOSE = "close";

        private Connection conn;

        public ConnectionProxy(Connection conn) {
            this.conn = conn;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (!CLOSE.equals(method.getName())) {
                method.setAccessible(true);
                return method.invoke(conn, args);
            }
            System.out.println("connection closed!");
            recover(conn);
            return null;
        }
    }
}
