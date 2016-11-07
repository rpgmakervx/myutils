package org.easyarch.myutils.cp.ds;/**
 * Description : 
 * Created by YangZH on 16-11-5
 *  下午10:04
 */

import org.easyarch.myutils.cp.cfg.PoolConfig;
import org.easyarch.myutils.db.DBUtils;
import org.easyarch.myutils.db.cfg.ConnConfig;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description :
 * Created by code4j on 16-11-5
 * 下午10:04
 */

public class DBCPool extends DataSourceAdapter {
    private Queue<Connection> conpool;
    private BlockingQueue<Connection> idleQueue;
    public static final Set<Connection> realconns = new CopyOnWriteArraySet<Connection>();
    private final int maxPoolSize;
    private final int minIdle;
    private final int maxIdle;
    private final long keepAliveTime;
    private AtomicInteger currentPoolSize = new AtomicInteger(0);

    static{
        try {
            Class.forName(ProcessWatcher.class.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public DBCPool(BlockingQueue queue) {
        maxPoolSize = PoolConfig.getMaxPoolSize();
        minIdle = PoolConfig.getMinIdle();
        maxIdle = PoolConfig.getMaxIdle();
        keepAliveTime = PoolConfig.getKeepAliveTime();
        conpool = new ConcurrentLinkedQueue<Connection>();
        idleQueue = queue;
        initQueue();
    }

    public DBCPool(int maxPoolSize,int maxIdle,
                   int minIdle,long keepAliveTime, BlockingQueue queue) {
        PoolConfig.config(maxPoolSize, maxIdle,minIdle , keepAliveTime);
        this.maxPoolSize = PoolConfig.getMaxPoolSize();
        this.maxIdle = PoolConfig.getMaxIdle();
        this.minIdle = PoolConfig.getMinIdle();
        this.keepAliveTime = PoolConfig.getKeepAliveTime();
        conpool = new ConcurrentLinkedQueue<Connection>();
        idleQueue = queue;
        System.out.println("init");
        initQueue();
    }

    private void initQueue() {
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
            conn = idleQueue.poll();
            System.out.println(realconns.size()+" 从队列中获取到一个资源:"+conn);
            if (conn == null||idleQueue.isEmpty()) {
                if (currentPoolSize.get() < maxPoolSize){
                    conn = createConnection();
                    System.out.println("队列没有，在池中创建:"+conn);
                    conpool.offer(conn);
                    currentPoolSize.incrementAndGet();
                    return conn;
                }
                System.out.println("try again....");
                conn = idleQueue.poll(keepAliveTime, TimeUnit.MILLISECONDS);
                if(conn == null) {
                    throw new RuntimeException("db connection pool was full");
                }
                System.out.println("got it!!"+conn);
            }
            return (Connection) Proxy.newProxyInstance(getClass().getClassLoader(),
                    new Class[]{Connection.class}, new ConnectionProxy(conn));
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
        Connection conn = DriverManager.getConnection(ConnConfig.getUrl()
                , ConnConfig.getUser(), ConnConfig.getPassword());
            realconns.add(conn);
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException("fail to create connection:\n" + e.getMessage());
        }
    }

    protected void recover(Connection conn) {
        if (conn != null) {
            System.out.println("recover ok? "+idleQueue.offer(conn));
            conpool.remove(conn);
            currentPoolSize.decrementAndGet();
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
            recover(conn);
            return null;
        }
    }
    static void kill(){
        for (Connection conn: DBCPool.realconns){
            DBUtils.close(conn);
        }
    }
}
