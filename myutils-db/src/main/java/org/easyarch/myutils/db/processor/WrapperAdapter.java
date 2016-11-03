package org.easyarch.myutils.db.processor;/**
 * Description : 
 * Created by YangZH on 16-11-3
 *  上午10:34
 */

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Description :
 * Created by code4j on 16-11-3
 * 上午10:34
 */

public class WrapperAdapter implements Wrapper {

    protected Wrapper wrapper;

    public WrapperAdapter(){
        this( new BasicWrapper());
    }

    public WrapperAdapter(Wrapper wrapper){
        this.wrapper = wrapper;
    }

    @Override
    public <T> List<T> list(ResultSet rs, Class<T> type) {
        return wrapper.list(rs,type);
    }

    @Override
    public <T> Set<T> set(ResultSet rs, Class<T> type) {
        return wrapper.set(rs,type);
    }

    @Override
    public Object[] array(ResultSet rs, Class type) {
        return wrapper.array(rs,type);
    }

    @Override
    public <T> T bean(ResultSet rs, Class<T> type) {
        return wrapper.bean(rs,type);
    }

    @Override
    public <T> Map<String, T> map(ResultSet rs, Class<T> type) {
        return wrapper.map(rs,type);
    }
}
