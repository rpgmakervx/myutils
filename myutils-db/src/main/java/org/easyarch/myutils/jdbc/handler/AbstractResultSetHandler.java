package org.easyarch.myutils.jdbc.handler;/**
 * Description : 
 * Created by YangZH on 16-11-2
 *  下午7:08
 */

import org.easyarch.myutils.jdbc.wrapper.Wrapper;

/**
 * Description :
 * Created by code4j on 16-11-2
 * 下午7:08
 */

abstract public class AbstractResultSetHandler<T> implements ResultSetHandler<T>{

    protected Wrapper<T> wrapper;
    protected Class<T> type;
    public AbstractResultSetHandler(Wrapper wrapper,Class<T> type){
        this.wrapper = wrapper;
        this.type = type;
    }

}
