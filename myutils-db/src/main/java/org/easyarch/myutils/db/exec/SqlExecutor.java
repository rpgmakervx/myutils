package org.easyarch.myutils.db.exec;/**
 * Description : 
 * Created by YangZH on 16-11-2
 *  上午11:11
 */

import org.easyarch.myutils.cp.cfg.PoolConfig;
import org.easyarch.myutils.cp.factory.DBCPoolFactory;
import org.easyarch.myutils.db.DBUtils;
import org.easyarch.myutils.db.cfg.ConnConfig;
import org.easyarch.myutils.db.handler.BeanListResultSetHadler;
import org.easyarch.myutils.db.handler.ResultSetHandler;
import org.easyarch.myutils.db.test.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    public <T> T query(String sql, ResultSetHandler<T> rshandler,Object obj) {
        Connection conn = null;
        try {
            conn = ds.getConnection();
            return query(conn, false,sql, rshandler,obj);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public <T> T query(String sql, boolean closeCon,ResultSetHandler<T> rshandler,Object obj) {
        Connection conn = null;
        try {
            conn = ds.getConnection();
            return query(conn, closeCon,sql, rshandler,obj);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> T query(Connection conn, String sql, ResultSetHandler<T> rshandler) {
        return query(conn, false,sql, rshandler,(T)null);
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
            DBUtils.close(rs);
            DBUtils.close(ps);
            if (closeCon)
                DBUtils.close(conn);
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
        Connection conn = null;
        try {
            conn = ds.getConnection();
            return update(conn,false,sql,bean);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public<T> int update(Connection conn,boolean closeCon,String sql,T bean){
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
            DBUtils.close(rs);
            DBUtils.close(ps);
            if (closeCon)
                DBUtils.close(conn);
        }
    }

    public int update(Connection conn,boolean closeCon,String sql,Object... params){
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
            DBUtils.close(rs);
            DBUtils.close(ps);
            if (closeCon)
                DBUtils.close(conn);
        }
    }

//    protected static Connection createConnection() {
//        try {
//            return DriverManager.getConnection(ConnConfig.getUrl()
//                    , ConnConfig.getUser(), ConnConfig.getPassword());
//        } catch (SQLException e) {
//            throw new RuntimeException("fail to create connection:\n" + e.getMessage());
//        }
//    }
    public static void main(String[] args) {
        ConnConfig.config("root", "123456",
                "jdbc:mysql://localhost:3306/database?useUnicode=true&amp;characterEncoding=utf8&amp;useSSL=false", "com.mysql.jdbc.Driver");
        PoolConfig.config(100, 5, 20, 60 * 1000L);
        final SqlExecutor executor = new MySqlExecutor(DBCPoolFactory.newConfigedDBCPool());
//        final SqlExecutor executor = new MySqlExecutor();
        ExecutorService pool = Executors.newCachedThreadPool();
        List<User> user = executor.query("select * from user ",true,
                new BeanListResultSetHadler<User>(User.class), null);
        pool.submit(new Runnable() {
            @Override
            public void run() {
                int index = 0;
                while (index < 2000) {
                    List<User> user = executor.query("select * from user ",true,
                            new BeanListResultSetHadler<User>(User.class), null);
                    System.out.println(Thread.currentThread().getName() + " index:" + index);
                    index++;
                }
                System.out.println("thread1 ended");
            }
        });
        pool.submit(new Runnable() {
            @Override
            public void run() {
                int index = 0;
                while (index < 3000) {
                    List<User> user =  executor.query("select * from user ",true,
                            new BeanListResultSetHadler<User>(User.class),null);
                    System.out.println(Thread.currentThread().getName()+" index:"+index);
                    index++;
                }
                System.out.println("thread2 ended");
            }
        });
        pool.shutdown();
//        executor.query(createConnection(),"select * from user ",
//                            new BeanListResultSetHadler<User>(User.class));
        System.out.println("programme ended");

    }
}
