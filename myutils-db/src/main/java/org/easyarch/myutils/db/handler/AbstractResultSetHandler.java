package org.easyarch.myutils.db.handler;/**
 * Description : 
 * Created by YangZH on 16-11-2
 *  下午7:08
 */

import org.easyarch.myutils.db.processor.Wrapper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Description :
 * Created by code4j on 16-11-2
 * 下午7:08
 */

abstract public class AbstractResultSetHandler<T> implements ResultSetHandler<List<T>>{

    protected Wrapper wrapper;
    protected Class<T> type;
    public AbstractResultSetHandler(Wrapper wrapper,Class<T> type){
        this.wrapper = wrapper;
        this.type = type;
    }

    @Override
    public List<T> handle(ResultSet rs) throws Exception {
        List<T> list = new ArrayList<T>();
        while (rs.next()){
            list.add(handleOneRow(rs));
        }
        return list;
    }

    public abstract T handleOneRow(ResultSet rs);
}
