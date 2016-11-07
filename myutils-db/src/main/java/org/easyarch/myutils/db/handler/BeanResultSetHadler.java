package org.easyarch.myutils.db.handler;/**
 * Description : 
 * Created by YangZH on 16-11-3
 *  上午12:00
 */

import org.easyarch.myutils.db.processor.BeanWrapper;
import org.easyarch.myutils.db.processor.Wrapper;

import java.sql.ResultSet;

/**
 * Description :
 * Created by code4j on 16-11-3
 * 上午12:00
 */

public class BeanResultSetHadler<T> implements ResultSetHandler<T> {

    private Wrapper<T> wrapper;

    private Class<T> type;

    public BeanResultSetHadler(Class<T> clazz) {
        this(new BeanWrapper<T>(),clazz);
    }

    public BeanResultSetHadler(Wrapper<T> wrapper,Class<T> type) {
        this.wrapper = wrapper;
        this.type = type;
    }


    @Override
    public T handle(ResultSet rs) throws Exception {
        return wrapper.bean(rs, type);
    }
}
