package org.easyarch.myutils.db;/**
 * Description : 
 * Created by YangZH on 16-11-2
 *  上午11:23
 */

import org.easyarch.myutils.db.handler.BeanListResultSetHadler;
import org.easyarch.myutils.db.test.User;

import java.sql.*;
import java.util.List;

/**
 * Description :
 * Created by code4j on 16-11-2
 * 上午11:23
 */

public class Main {

    private Connection connection;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    private static final String URL = "jdbc:mysql://localhost:3306/database?useUnicode=true&amp;characterEncoding=utf8&amp;useSSL=false";
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

    public Main() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        return connection;
    }

    public <T> List<T> queryList(String sql, BeanListResultSetHadler<T> rshandler, Object... params) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            ParameterMetaData meta = ps.getParameterMetaData();
            int count = meta.getParameterCount();
            int paramLength = params == null ? 0 : params.length;
//            StringTrimmedResultSet.wrap()
            if (paramLength != count) {
                throw new IllegalArgumentException("your param not match query string's param");
            }

            for (int index = 0; index < paramLength; index++) {
                if (params[index] == null) {
                    ps.setNull(index, Types.VARCHAR);
                    continue;
                }
                ps.setObject(index + 1, params[index]);
            }
            rs = ps.executeQuery();
            List<T> list = rshandler.handle(rs);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtils.close(connection);
            DBUtils.close(rs);
            DBUtils.close(ps);
        }
    }

    public static void main(String[] args) {
        Main ma = new Main();
        List<User> users = ma.queryList("select * from user where age = ?",
                    new BeanListResultSetHadler<User>(User.class), 20);
        System.out.println(users);
    }

//    private List<User> queryList(String s, BeanListResultSetHadler<User> userBeanListResultSetHadler, int i) {
//
//
//    }
}
