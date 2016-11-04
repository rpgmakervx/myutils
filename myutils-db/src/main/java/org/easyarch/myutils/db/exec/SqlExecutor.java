package org.easyarch.myutils.db.exec;/**
 * Description : 
 * Created by YangZH on 16-11-2
 *  上午11:11
 */

import org.easyarch.myutils.db.DBUtils;
import org.easyarch.myutils.db.connector.ConnectInfo;
import org.easyarch.myutils.db.connector.DBConnector;
import org.easyarch.myutils.db.handler.BeanListResultSetHadler;
import org.easyarch.myutils.db.handler.ResultSetHandler;
import org.easyarch.myutils.db.test.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Description :
 * Created by code4j on 16-11-2
 * 上午11:11
 */

public class SqlExecutor extends AbstractExecutor{


    public SqlExecutor(DBConnector connector,boolean supportMeta){
        super(connector,supportMeta);
    }

    public SqlExecutor(DBConnector connector){
        super(connector);
    }

    public <T> T query(String sql, ResultSetHandler<T> rshandler) {
        Connection conn = connector.getConnection();
        return query(conn, false,sql, rshandler,(T)null);
    }
    public <T> T query(Connection conn, String sql, ResultSetHandler<T> rshandler) {
        return query(conn, true,sql, rshandler,(T)null);
    }
    public <T> T query(Connection conn, boolean closeCon,String sql, ResultSetHandler<T> rshandler, Object... params) {
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
    public <T> T query(Connection conn, boolean closeCon, String sql, ResultSetHandler<T> rshandler, T bean) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = prepareStatement(conn, sql);
            fillStatementWithBean(ps, bean);
            rs = ps.executeQuery();
            T result = rshandler.handle(rs);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            if (closeCon)
                DBUtils.close(conn);

        }
    }

    public<T> int update(String sql,T bean){
        Connection conn = connector.getConnection();
        return update(conn,sql,bean);
    }

    public<T> int update(Connection conn,String sql,T bean){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = prepareStatement(conn, sql);
            fillStatementWithBean(ps,bean);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            DBUtils.rollBack(conn);
            return -1;
        }finally {
            DBUtils.closeAll(conn,ps,rs);
        }
    }

    public int update(Connection conn,String sql,Object... params){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = prepareStatement(conn, sql);
            fillStatement(ps,params);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            DBUtils.rollBack(conn);
            return -1;
        }finally {
            DBUtils.closeAll(conn,ps,rs);
        }
    }
    public static void main(String[] args) {
        ConnectInfo.config("root","123456",
                "jdbc:mysql://localhost:3306/database?useUnicode=true&amp;characterEncoding=utf8&amp;useSSL=false","com.mysql.jdbc.Driver");
        SqlExecutor executor = new MySqlExecutor(new DBConnector());
        List<User> users = executor.query("select * from user ",
                new BeanListResultSetHadler<User>(User.class));
        System.out.println(users);
    }
}
