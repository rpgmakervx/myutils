package org.easyarch.myutils.db.handler;/**
 * Description : 
 * Created by YangZH on 16-11-3
 *  上午12:00
 */

import org.easyarch.myutils.db.processor.BasicWrapper;
import org.easyarch.myutils.db.processor.Wrapper;

import java.sql.ResultSet;

/**
 * Description :
 * Created by code4j on 16-11-3
 * 上午12:00
 */

public class BeanResultSetHadler<T> implements ResultSetHandler<T> {

    private Wrapper wrapper;

    private Class<T> clazz;

    public BeanResultSetHadler(Class<T> clazz) {
        this.clazz = clazz;
        this.wrapper = new BasicWrapper();
    }

    public BeanResultSetHadler(Class<T> clazz, Wrapper wrapper) {
        this.clazz = clazz;
        this.wrapper = wrapper;
    }

    public Class<T> getType(){
        return clazz;
    }

    @Override
    public T handle(ResultSet rs) throws Exception {
        return wrapper.bean(rs, clazz);
    }
}
