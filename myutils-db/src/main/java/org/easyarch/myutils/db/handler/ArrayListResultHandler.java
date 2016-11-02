package org.easyarch.myutils.db.handler;/**
 * Description : 
 * Created by YangZH on 16-11-2
 *  下午6:20
 */

import org.easyarch.myutils.db.processor.BasicProcessor;
import org.easyarch.myutils.db.processor.Processor;

import java.sql.ResultSet;
import java.util.List;

/**
 * Description :
 * Created by code4j on 16-11-2
 * 下午6:20
 */

public class ArrayListResultHandler<T> extends AbstractResultSetHandler<List<T>>{

    public ArrayListResultHandler(){
        super(new BasicProcessor());
    }

    public ArrayListResultHandler(Processor processor) {
        super(processor);
    }

    @Override
    public List<T> handleOneRow(ResultSet rs) {
        return null;
    }
}
