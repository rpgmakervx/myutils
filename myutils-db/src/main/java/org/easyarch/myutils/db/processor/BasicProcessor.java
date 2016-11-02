package org.easyarch.myutils.db.processor;/**
 * Description : 
 * Created by YangZH on 16-11-3
 *  上午12:19
 */

import org.easyarch.myutils.array.ArrayUtils;
import org.easyarch.myutils.reflect.ReflectUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Description :
 * Created by code4j on 16-11-3
 * 上午12:19
 */

public class BasicProcessor implements Processor{

    @Override
    public<T> List<T> list(ResultSet rs, Class<T> type) {
        List<T> list = new CopyOnWriteArrayList<T>();
        try {
            while (rs.next()){
                Object object = ReflectUtils.newInstance(type);
                ResultSetMetaData meta = rs.getMetaData();
                int index = meta.getColumnCount();
                for (int i = 0;i<index;i++){
                    Object value = rs.getObject(i + 1);
                    ReflectUtils.setter(object,meta.getColumnName(i + 1),value);
                }
//                System.out.println("index:"+index+",column:"+meta.getColumnName(index)+",value:"+value+",length:"+meta.getColumnCount());
                list.add((T) object);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public<T> Set<T> set(ResultSet rs, Class<T> type) {
        Set<T> set = new HashSet<T>();
        try {
            Object object = type.getClass().newInstance();
            while (rs.next()){
                ResultSetMetaData meta = rs.getMetaData();
                int index = rs.getRow();
                Object value = rs.getObject(index);
                ReflectUtils.setter(object,meta.getColumnName(index),value);
                set.add((T) object);
            }
            return set;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object[] array(ResultSet rs, Class type) {
        Object[] array = null;
        try {
            ResultSetMetaData meta = rs.getMetaData();
            array = ArrayUtils.newArray(type, meta.getColumnCount());
            Object object = type.getClass().newInstance();
            while (rs.next()){
                int index = rs.getRow();
                Object value = rs.getObject(index);
                ReflectUtils.setter(object,meta.getColumnName(index),value);
                array[index] = object;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    @Override
    public<T> T bean(ResultSet rs, Class<T> type) {
        Object object = null;
        try {
            ResultSetMetaData meta = rs.getMetaData();
            object = ReflectUtils.newInstance(type);
            while (rs.next()){
                int index = rs.getRow();
                ReflectUtils.setter(object, meta.getColumnName(index), rs.getObject(index));
            }
            return (T) object;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public<T>  Map<String, T> map(ResultSet rs, Class<T> type) {
        Map<String,T> resultMap = new ConcurrentHashMap<String, T>();
        try {
            Object object = type.getClass().newInstance();
            while (rs.next()){
                ResultSetMetaData meta = rs.getMetaData();
                int index = rs.getRow();
                Object value = rs.getObject(index);
                String columnName = meta.getColumnName(index);
                ReflectUtils.setter(object,columnName,value);
                resultMap.put(columnName,(T) object);
            }
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
