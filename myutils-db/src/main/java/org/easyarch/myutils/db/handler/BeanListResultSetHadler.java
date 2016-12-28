package org.easyarch.myutils.db.handler;/**
 * Description : 
 * Created by YangZH on 16-11-3
 *  上午12:00
 */

import org.easyarch.myutils.User;
import org.easyarch.myutils.db.wrapper.BeanWrapper;
import org.easyarch.myutils.db.wrapper.Wrapper;

import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.util.List;

/**
 * Description :
 * Created by code4j on 16-11-3
 * 上午12:00
 */

public class BeanListResultSetHadler<T> implements ResultSetHandler<List<T>> {

    protected Wrapper<T> wrapper;

    protected Class<T> type;

    public BeanListResultSetHadler(Class<T> type){
        this(new BeanWrapper<T>(),type);
    }

    public BeanListResultSetHadler(Wrapper<T> wrapper ,Class<T> type) {
        this.wrapper = wrapper;
        this.type = type;
    }


    @Override
    public List<T> handle(ResultSet rs) throws Exception {
        return wrapper.list(rs, type);
    }

    public static void main(String[] args) {
        BeanListResultSetHadler<User> br = new BeanListResultSetHadler<User>(User.class);
        Class entityClass = (Class) ((ParameterizedType) br.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        System.out.println(entityClass);
    }
}
