package org.easyarch.myutils.cp.ds;/**
 * Description : 
 * Created by YangZH on 16-11-5
 *  下午10:04
 */

import org.easyarch.myutils.cp.cfg.PoolConfig;
import org.easyarch.myutils.db.connector.ConnectInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Description :
 * Created by code4j on 16-11-5
 * 下午10:04
 */

public class EasyDataSource extends DataSourceAdapter {
    private List<MyConnection> conpool;
    private LinkedBlockingQueue<MyConnection> waitQueue;
    private int maxPoolSize;
    private int minInit;
    private int maxWait;
    private long keepAliveTime;

    public EasyDataSource() {
        maxPoolSize = PoolConfig.getMaxPoolSize();
        minInit = PoolConfig.getMinInit();
        maxWait = PoolConfig.getMaxWait();
        keepAliveTime = PoolConfig.getKeepAliveTime();
        conpool = new CopyOnWriteArrayList<MyConnection>();
        waitQueue = new LinkedBlockingQueue<MyConnection>();
        initConnection();
    }

    private void initConnection() {
        for (int index = 0; index < minInit; index++) {
            try {
                Connection conn = DriverManager.getConnection(ConnectInfo.getUrl()
                        , ConnectInfo.getUser(), ConnectInfo.getPassword());
                MyConnection myconn = new MyConnection(System.currentTimeMillis(), conn);
                conpool.add(myconn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Connection getConnection() throws SQLException {


        return null;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    class MyConnection {
        private Connection conn;
        private long timestamp;

        public MyConnection(long timestamp, Connection conn) {
            this.timestamp = timestamp;
            this.conn = conn;
        }

        public Connection getConn() {
            return conn;
        }

        public long getTimestamp() {
            return timestamp;
        }
    }
}
