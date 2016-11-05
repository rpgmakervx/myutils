package org.easyarch.myutils.cp.factory;/**
 * Description : 
 * Created by YangZH on 16-11-4
 *  上午2:00
 */


import org.easyarch.myutils.cp.ds.DataSourceAdapter;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Description :
 * Created by code4j on 16-11-4
 * 上午2:00
 */

public class ConnectionPool extends DataSourceAdapter {


    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }
}
