package org.easyarch.myutils.orm.jdbc;

import org.easyarch.myutils.db.exec.SqlExecutor;

import javax.sql.DataSource;
import java.util.List;

/**
 * Description :
 * Created by xingtianyu on 16-12-29
 * 上午12:34
 * description:
 */

public class SqlRunner {

    private SqlExecutor executor;

    private DataSource ds;

    public SqlRunner(SqlExecutor executor,DataSource ds){
        this.executor = executor;
        this.ds = ds;
    }

    public SqlRunner(DataSource ds){
        this.ds = ds;
    }

    public<E> List<E> selectList(String bind,Object parameter){
//        try {
//            return executor.query(ds.getConnection(),sql,new BeanListResultSetHadler<E>(cls),parameters);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return null;
    }
}
