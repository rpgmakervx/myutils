package org.easyarch.myutils.db.wrapper;/**
 * Description : 
 * Created by YangZH on 16-11-3
 *  上午12:19
 */

import org.easyarch.myutils.reflect.ReflectUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Description :
 * Created by code4j on 16-11-3
 * 上午12:19
 */

public class BeanWrapper<T> extends WrapperAdapter<T> {

    @Override
    public List<T> list(ResultSet rs, Class<T> type) {
        List<T> list = new CopyOnWriteArrayList<T>();
        try {
            ResultSetMetaData meta = rs.getMetaData();
            while (rs.next()) {
                list.add(createBean(rs,meta,type));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public T bean(ResultSet rs, Class<T> type) {
        try {
            ResultSetMetaData meta = rs.getMetaData();
            if (rs.next()) {
                return createBean(rs,meta,type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * bean生成模块抽取
     * @param rs
     * @param meta
     * @param type
     * @param <T>
     * @return
     */
    private T createBean(ResultSet rs, ResultSetMetaData meta,Class<T> type) {
        Object object = ReflectUtils.newInstance(type);
        try {
            int count = meta.getColumnCount();
            for (int i = 0; i < count; i++) {
                Object value = rs.getObject(i + 1);
                ReflectUtils.setter(object, meta.getColumnName(i + 1), value);
            }
            return (T) object;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
