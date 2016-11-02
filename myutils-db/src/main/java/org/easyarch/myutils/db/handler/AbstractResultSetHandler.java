package org.easyarch.myutils.db.handler;/**
 * Description : 
 * Created by YangZH on 16-11-2
 *  下午7:08
 */

import org.easyarch.myutils.db.processor.Processor;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Description :
 * Created by code4j on 16-11-2
 * 下午7:08
 */

abstract public class AbstractResultSetHandler<T> implements ResultSetHandler<List<T>>{

    private Processor processor;

    public AbstractResultSetHandler(Processor processor){
        this.processor = processor;
    }

    @Override
    public List<T> handle(ResultSet rs) throws Exception {
        List<T> list = new ArrayList<T>();
        int index = 0;
        while (rs.next()){
            list.add((T) rs.getObject(index));
        }
        return list;
    }

    public abstract T handleOneRow(ResultSet rs);
}
