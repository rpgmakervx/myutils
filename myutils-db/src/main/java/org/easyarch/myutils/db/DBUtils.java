package org.easyarch.myutils.db;/**
 * Description : 
 * Created by YangZH on 16-11-2
 *  上午10:37
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Description :
 * Created by code4j on 16-11-2
 * 上午10:37
 */

public final class DBUtils {

    public static void closeAll(Connection conn, Statement stmt,
                                    ResultSet rs) {
        try {
            if (rs != null&&!rs.isClosed()){
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (stmt != null&&!stmt.isClosed())
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn!=null&&!conn.isClosed()){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void close(Connection conn) {
        closeAll(conn, null, null);
    }

    public static void close(ResultSet rs) {
        closeAll(null, null, rs);
    }

    public static void close(Statement stmt) {
        closeAll(null,stmt,null);
    }
}
