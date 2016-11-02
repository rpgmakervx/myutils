package org.easyarch.myutils.db.handler;/**
 * Description : 
 * Created by YangZH on 16-11-3
 *  上午12:00
 */

import org.easyarch.myutils.db.processor.BasicProcessor;
import org.easyarch.myutils.db.processor.Processor;

import java.sql.ResultSet;

/**
 * Description :
 * Created by code4j on 16-11-3
 * 上午12:00
 */

public class BeanResultSetHadler<T> implements ResultSetHandler<T> {

    private Processor processor;

    private Class<T> clazz;

    public BeanResultSetHadler(Class<T> clazz) {
        this.clazz = clazz;
        this.processor = new BasicProcessor();
    }

    public BeanResultSetHadler(Class<T> clazz, Processor processor) {
        this.clazz = clazz;
        this.processor = processor;
    }

    public Class<T> getType(){
        return clazz;
    }

    @Override
    public T handle(ResultSet rs) throws Exception {
        return processor.bean(rs, clazz);
    }
}
