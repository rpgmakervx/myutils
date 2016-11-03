package org.easyarch.myutils.db.handler;/**
 * Description : 
 * Created by YangZH on 16-11-2
 *  下午6:20
 */

import org.easyarch.myutils.db.processor.BasicWrapper;
import org.easyarch.myutils.db.processor.Wrapper;

import java.sql.ResultSet;

/**
 * Description :
 * Created by code4j on 16-11-2
 * 下午6:20
 */

public class ArrayListResultHandler<T> extends AbstractResultSetHandler<T>{


    public ArrayListResultHandler(Class<T> type){
        this(new BasicWrapper(),type);
    }

    public ArrayListResultHandler(Wrapper wrapper,Class<T> type) {
        super(wrapper,type);
    }

    @Override
    public T handleOneRow(ResultSet rs) {
        return wrapper.bean(rs,type);
    }
}
