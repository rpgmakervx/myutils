package org.easyarch.myutils.db;/**
 * Description : 
 * Created by YangZH on 16-11-2
 *  上午10:37
 */

import org.easyarch.myutils.db.exec.SqlExecutor;
import org.easyarch.myutils.db.test.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Description :
 * Created by code4j on 16-11-2
 * 上午10:37
 */

public final class DBUtils {

    public static SqlExecutor executor;

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
                stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn!=null&&!conn.isClosed()){
                conn.close();
                System.out.println("connection close from DBUtils");
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

    public static void rollBack(Connection conn){
        try {
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollBackAndClose(Connection conn){
        if (conn != null){
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                close(conn);
            }
        }
    }


    public static<T> T selectById(String sql,String id){
//        executor.query(sql,new id);
        return null;
    }

    public static void main(String[] args) {
        LinkedBlockingQueue<User> queue = new LinkedBlockingQueue();
        User u = new User();
        queue.add(u);
        queue.add(new User());
        queue.add(new User());
        queue.add(new User());
        queue.add(new User());
        queue.add(new User());
        queue.add(new User());
        queue.add(new User());
        System.out.println(queue.remove(u));
    }
}
