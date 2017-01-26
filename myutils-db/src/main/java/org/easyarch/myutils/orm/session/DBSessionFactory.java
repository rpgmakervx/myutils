package org.easyarch.myutils.orm.session;

import org.easyarch.myutils.pool.ds.DBCPool;
import org.easyarch.myutils.pool.ds.DBCPoolFactory;
import org.easyarch.myutils.jdbc.exec.MySqlExecutor;
import org.easyarch.myutils.jdbc.exec.SqlExecutor;
import org.easyarch.myutils.orm.session.impl.DefaultDBSession;

import java.sql.Connection;

/**
 * Description :
 * Created by xingtianyu on 16-12-29
 * 上午12:11
 * description:
 */

public class DBSessionFactory {

    private static final DBCPool pool = (DBCPool) DBCPoolFactory.newConfigedDBCPool();

    private Configuration configuration;

    public DBSessionFactory(Configuration configuration){
        this.configuration = configuration;
    }

    public DBSession newSession(Connection connection){
        SqlExecutor executor = new MySqlExecutor(connection);
        return new DefaultDBSession(configuration,executor);
    }

}
