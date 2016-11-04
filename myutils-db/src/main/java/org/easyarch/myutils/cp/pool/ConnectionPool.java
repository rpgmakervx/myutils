package org.easyarch.myutils.cp.pool;/**
 * Description : 
 * Created by YangZH on 16-11-4
 *  上午2:00
 */


import org.easyarch.myutils.cp.ds.DataSourceAdapter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Description :
 * Created by code4j on 16-11-4
 * 上午2:00
 */

public class ConnectionPool extends DataSourceAdapter {

    private CopyOnWriteArrayList<Connection> conpool = new CopyOnWriteArrayList<Connection>();

    private LinkedBlockingQueue<Connection> waitQueue = new LinkedBlockingQueue<Connection>();

    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }
}
