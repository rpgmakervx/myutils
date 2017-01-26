package org.easyarch.myutils.orm.session;

import org.easyarch.myutils.cp.ds.DBCPool;
import org.easyarch.myutils.cp.ds.DBCPoolFactory;
import org.easyarch.myutils.db.exec.MySqlExecutor;
import org.easyarch.myutils.db.exec.SqlExecutor;
import org.easyarch.myutils.orm.session.impl.DefaultDBSession;

/**
 * Description :
 * Created by xingtianyu on 16-12-29
 * 上午12:11
 * description:
 */

public class DBSessionFactory {

    private static final DBCPool pool = (DBCPool) DBCPoolFactory.newConfigedDBCPool();

    public static DBSession newSession(){
        SqlExecutor executor = new MySqlExecutor(pool.getConnection());
        return new DefaultDBSession(executor);
    }

}
