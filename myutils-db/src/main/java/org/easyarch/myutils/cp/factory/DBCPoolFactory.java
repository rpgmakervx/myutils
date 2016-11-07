package org.easyarch.myutils.cp.factory;/**
 * Description : 
 * Created by YangZH on 16-11-4
 *  上午2:00
 */


import org.easyarch.myutils.cp.cfg.PoolConfig;
import org.easyarch.myutils.cp.ds.DBCPool;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * Description :
 * Created by code4j on 16-11-4
 * 上午2:00
 */

public class DBCPoolFactory {

    public static DataSource newFixedDBCPool(int maxPoolSize) {
        return new DBCPool(maxPoolSize, maxPoolSize >> 2, maxPoolSize >> 3,
                1000 * 30L, new LinkedBlockingQueue<Connection>(maxPoolSize));
    }

    public static DataSource newCachedDBCPool() {
        return new DBCPool(Integer.MAX_VALUE, Integer.MAX_VALUE, 10,
                0L, new LinkedTransferQueue());
    }
    public static DataSource newConfigedDBCPool() {
        return new DBCPool(new LinkedBlockingQueue<Connection>(PoolConfig.getMaxIdle()));
    }

}
