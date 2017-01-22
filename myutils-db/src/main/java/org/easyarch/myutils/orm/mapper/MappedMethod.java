package org.easyarch.myutils.orm.mapper;

import org.easyarch.myutils.orm.session.Configuration;
import org.easyarch.myutils.orm.session.DBSession;

/**
 * Description :
 * Created by xingtianyu on 17-1-22
 * 下午11:17
 * description:
 */

public class MappedMethod {
    private DBSession session;
    private SqlType type;

    public MappedMethod(DBSession session, SqlType type) {
        this.session = session;
        this.type = type;
    }

    public Object delegateExecute(String methodName,Object[] args){
        String sql = Configuration.getInstance().getMappedSql(methodName);
        return null;
    }
}
