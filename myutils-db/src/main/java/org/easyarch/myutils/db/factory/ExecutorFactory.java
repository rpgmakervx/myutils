package org.easyarch.myutils.db.factory;

import org.easyarch.myutils.db.exec.MySqlExecutor;
import org.easyarch.myutils.db.exec.OracleExecutor;

/**
 * Description :
 * Created by xingtianyu on 16-12-25
 * 下午8:55
 * description:
 */

public class ExecutorFactory {

    public static MySqlExecutor getMysql(){
        return new MySqlExecutor();
    }

    public static OracleExecutor getOracle(){
        return new OracleExecutor();
    }
}
