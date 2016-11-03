package org.easyarch.myutils.db.exec;/**
 * Description : 
 * Created by YangZH on 16-11-2
 *  上午11:11
 */

import org.easyarch.myutils.db.DBUtils;
import org.easyarch.myutils.db.handler.BeanListResultSetHadler;
import org.easyarch.myutils.db.handler.BeanResultSetHadler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Description :
 * Created by code4j on 16-11-2
 * 上午11:11
 */

public class SqlExecutor extends AbstractExecutor{


    public SqlExecutor(boolean supportMeta){
        super(supportMeta);
    }

    public <T> T query(Connection conn, String sql, BeanResultSetHadler<T> rshandler, Object... params) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = prepareStatement(conn, sql);
            fillStatement(ps,params);
            rs = ps.executeQuery();
            T result = rshandler.handle(rs);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtils.closeAll(conn, ps, rs);
        }
    }
    public <T> T query(Connection conn, String sql, BeanResultSetHadler<T> rshandler, Object bean) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = prepareStatement(conn, sql);
            fillStatement(ps,bean);
            rs = ps.executeQuery();
            T result = rshandler.handle(rs);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtils.closeAll(conn, ps, rs);
        }
    }

    public <T> List<T> queryList(Connection conn, String sql, BeanListResultSetHadler<T> rshandler, Object... params) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = prepareStatement(conn, sql);
            fillStatement(ps,params);
            rs = ps.executeQuery();
            List<T> list = rshandler.handle(rs);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtils.closeAll(conn, ps, rs);
        }
    }
    public <T> List<T> queryList(Connection conn, String sql, BeanListResultSetHadler<T> rshandler, Object bean) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = prepareStatement(conn, sql);
            fillStatementWithBean(ps, bean);
            rs = ps.executeQuery();
            List<T> list = rshandler.handle(rs);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtils.closeAll(conn, ps, rs);
        }
    }

    public static void main(String[] args) {
//        SqlExecutor executor = new MySqlExecutor();
//        List<User> users = executor.queryList("select * from user where age = ?",
//                new BeanListResultSetHadler<User>(User.class), 20);
//        System.out.println(users);
    }
}
