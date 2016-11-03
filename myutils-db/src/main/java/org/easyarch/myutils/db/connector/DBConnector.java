package org.easyarch.myutils.db.connector;/**
 * Description : 
 * Created by YangZH on 16-11-3
 *  下午3:13
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Description :
 * Created by code4j on 16-11-3
 * 下午3:13
 */

public class DBConnector {

    static {
        try {
            Class.forName(ConnectInfo.getDrivername());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(ConnectInfo.getUrl()
                    ,ConnectInfo.getUsername(),ConnectInfo.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
