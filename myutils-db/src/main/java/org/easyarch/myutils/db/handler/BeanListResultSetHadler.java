package org.easyarch.myutils.db.handler;/**
 * Description : 
 * Created by YangZH on 16-11-3
 *  上午12:00
 */

import org.easyarch.myutils.db.processor.BasicProcessor;
import org.easyarch.myutils.db.processor.Processor;

import java.sql.ResultSet;
import java.util.List;

/**
 * Description :
 * Created by code4j on 16-11-3
 * 上午12:00
 */

public class BeanListResultSetHadler<T> implements ResultSetHandler<List<T>> {

    private Processor processor;

    private Class<T> clazz;

    public BeanListResultSetHadler(Class<T> clazz) {
        this.clazz = clazz;
        this.processor = new BasicProcessor();
    }

    public BeanListResultSetHadler(Class<T> clazz, Processor processor) {
        this.clazz = clazz;
        this.processor = processor;
    }

    public Class<T> getType(){
        return clazz;
    }

    @Override
    public List<T> handle(ResultSet rs) throws Exception {
        return processor.list(rs, clazz);
    }
}
