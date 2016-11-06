package org.easyarch.myutils.db.exec;/**
 * Description : 
 * Created by YangZH on 16-11-3
 *  下午3:11
 */

import javax.sql.DataSource;

/**
 * Description :
 * Created by code4j on 16-11-3
 * 下午3:11
 */

public class MySqlExecutor extends SqlExecutor {

    public MySqlExecutor(){
        super(true);
    }

    public MySqlExecutor(DataSource ds) {
        super(ds,true);
    }
}
