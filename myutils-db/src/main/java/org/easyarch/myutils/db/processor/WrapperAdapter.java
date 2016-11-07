package org.easyarch.myutils.db.processor;/**
 * Description : 
 * Created by YangZH on 16-11-3
 *  上午10:34
 */

import java.sql.ResultSet;
import java.util.List;

/**
 * Description :
 * Created by code4j on 16-11-3
 * 上午10:34
 */

public class WrapperAdapter<T> implements Wrapper<T> {


    @Override
    public List<T> list(ResultSet rs, Class<T> type) {
        throw new RuntimeException("adapter should be rewrite so you can use this method");
    }

    @Override
    public T bean(ResultSet rs, Class<T> type) {
        throw new RuntimeException("adapter should be rewrite so you can use this method");
    }
}
