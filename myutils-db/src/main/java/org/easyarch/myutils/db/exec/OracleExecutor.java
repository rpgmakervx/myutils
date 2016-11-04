package org.easyarch.myutils.db.exec;/**
 * Description : 
 * Created by YangZH on 16-11-3
 *  下午3:11
 */

import org.easyarch.myutils.db.connector.DBConnector;

/**
 * Description :
 * Created by code4j on 16-11-3
 * 下午3:11
 */

public class OracleExecutor extends SqlExecutor {
    public OracleExecutor(DBConnector connector) {
        super(connector,false);
    }
}
