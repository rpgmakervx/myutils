package org.easyarch.myutils.db.exec;/**
 * Description : 
 * Created by YangZH on 16-11-2
 *  上午11:11
 */

import org.easyarch.myutils.cp.cfg.PoolConfig;
import org.easyarch.myutils.cp.ds.DBCPoolFactory;
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

    private Connection conn;

    public SqlExecutor(Connection conn,boolean supportMeta){
        super(supportMeta);
        this.conn = conn;
    }

    /**
     *
     * @param sql
     * @param rshandler
     * @param params
     * @param <T>
     * @return
     */
    public <T> T query(String sql, ResultSetHandler<T> rshandler, Object[] params) {
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
     * 提供connection的修改操作
     * @param sql
     * @param params
     * @return
     */
    public int alter(String sql,Object[] params){
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

    public void rollback(){
        ConnectionUtils.close(conn);
    }

    /**
     * 提供connection的批量修改操作
     * @param sql
     * @param params
     */
    public void alterBatch(String sql,Object[][]params){
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


    public static void main(String[] args) throws SQLException {
        ConnConfig.config("root", "123456",
                "jdbc:mysql://localhost:3306/database?useUnicode=true&amp;characterEncoding=utf8&amp;useSSL=false", "com.mysql.jdbc.Driver");
        PoolConfig.config(200, 50, 5, 3 * 1000L);
        DataSource dataSource = DBCPoolFactory.newConfigedDBCPool();
        final SqlExecutor executor = new MySqlExecutor(dataSource.getConnection());
        Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement("");
        List<User> user = executor.query("select * from user ",
                new BeanListResultSetHadler<User>(User.class), null);
//        int result = executor.alter(connection,"insert into user values(?,?,?,?,?)",10,"laisbfdsfk","583110127","13652212569",30);
//        System.out.println("end "+result);
    }
}
