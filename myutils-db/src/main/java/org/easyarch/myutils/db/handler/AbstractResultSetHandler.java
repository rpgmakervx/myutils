package org.easyarch.myutils.db.handler;/**
 * Description : 
 * Created by YangZH on 16-11-2
 *  下午7:08
 */

import org.easyarch.myutils.db.processor.Wrapper;

import java.sql.ResultSet;

/**
 * Description :
 * Created by code4j on 16-11-2
 * 下午7:08
 */

abstract public class AbstractResultSetHandler<T> implements ResultSetHandler<T>{

    protected Wrapper wrapper;
    protected Class<T> type;
    public AbstractResultSetHandler(Wrapper wrapper,Class<T> type){
        this.wrapper = wrapper;
        this.type = type;
    }

    @Override
    public T handle(ResultSet rs) throws Exception {
        return handleOneRow(rs);
    }

    public abstract T handleOneRow(ResultSet rs);
}
