package org.easyarch.myutils.db.exec;/**
 * Description : 
 * Created by YangZH on 16-11-2
 *  上午11:11
 */

import org.easyarch.myutils.cp.cfg.PoolConfig;
import org.easyarch.myutils.cp.factory.DBCPoolFactory;
import org.easyarch.myutils.db.ConnectionUtils;
import org.easyarch.myutils.db.cfg.ConnConfig;
import org.easyarch.myutils.db.handler.BeanListResultSetHadler;
import org.easyarch.myutils.db.handler.ResultSetHandler;
import org.easyarch.myutils.test.User;

import javax.sql.DataSource;
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


    public SqlExecutor(boolean supportMeta){
        super(null,supportMeta);
    }

    public SqlExecutor(DataSource ds,boolean supportMeta){
        super(ds,supportMeta);
    }

    public SqlExecutor(DataSource ds){
        super(ds);
    }

    public <T> T query(String sql, ResultSetHandler<T> rshandler,Object...params) {
        Connection conn = null;
        try {
            conn = ds.getConnection();
            return query(conn,sql, rshandler,params);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> T query(Connection conn, String sql, ResultSetHandler<T> rshandler, Object... params) {
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
            ConnectionUtils.close(rs);
            ConnectionUtils.close(ps);
            ConnectionUtils.close(conn);
        }
    }

    /**
     * 修改操作
     * @param sql
     * @param bean
     * @param <T>
     * @return
     */
    public<T> int alter(String sql,Object ...bean){
        Connection conn = null;
        try {
            conn = ds.getConnection();
            return alter(conn,sql,bean);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 提供connection的修改操作
     * @param conn
     * @param sql
     * @param params
     * @return
     */
    public int alter(Connection conn,String sql,Object... params){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = prepareStatement(conn, sql);
            fillStatement(ps,params);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            ConnectionUtils.rollBack(conn);
            return -1;
        }finally {
            ConnectionUtils.close(rs);
            ConnectionUtils.close(ps);
            ConnectionUtils.close(conn);
        }
    }

    /**
     * 提供connection的批量修改操作
     * @param conn
     * @param sql
     * @param params
     */
    public void alterBatch(Connection conn,String sql,Object[][]params){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ConnectionUtils.beginTransaction(conn);
            ps = batchPrepareStatement(conn, sql);
            fillStatement(ps,params);
            ps.executeBatch();
            ConnectionUtils.commit(conn);
            return ;
        } catch (SQLException e) {
            e.printStackTrace();
            ConnectionUtils.rollBack(conn);
        }finally {
            ConnectionUtils.close(rs);
            ConnectionUtils.close(ps);
            ConnectionUtils.close(conn);
        }
    }

    /**
     * 批量修改操作
     * @param sql
     * @param params
     */
    public void alterBatch(String sql,Object[][]params){
        Connection conn = null;
        try {
            conn = ds.getConnection();
            alterBatch(conn,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        ConnConfig.config("root", "123456",
                "jdbc:mysql://localhost:3306/database?useUnicode=true&amp;characterEncoding=utf8&amp;useSSL=false", "com.mysql.jdbc.Driver");
        PoolConfig.config(200, 50, 5, 3 * 1000L);
        final SqlExecutor executor = new MySqlExecutor(DBCPoolFactory.newConfigedDBCPool());
        List<User> user = executor.query("select * from user ",
                new BeanListResultSetHadler<User>(User.class), null);
        int result = executor.alter("insert into user values(?,?,?,?,?)",10,"laisbfdsfk","583110127","13652212569",30);
        System.out.println("end "+result);
    }
}
