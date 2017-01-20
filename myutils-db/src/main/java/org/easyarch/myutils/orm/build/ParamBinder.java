package org.easyarch.myutils.orm.build;

import org.easyarch.myutils.orm.annotation.entity.Column;
import org.easyarch.myutils.orm.annotation.entity.Table;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description :
 * Created by xingtianyu on 17-1-19
 * 下午7:42
 * description:
 */

public class ParamBinder {

    private Map<String,Object> reflectMap;

    public ParamBinder(){
        reflectMap = new HashMap<>();
    }

    public Map<String,Object> reflect(List obj, List<String> name){
        for (int index=0;index<obj.size();index++){
            reflectMap.put(name.get(index),obj.get(index));
        }
        return reflectMap;
    }

    public <T> Map<String,Object> reflect(Object obj,Class<T> clazz){
        try {
            Field[] fields = clazz.getDeclaredFields();
            Table table = clazz.getAnnotation(Table.class);
            if (table == null){
                for (Field field:fields){
                    field.setAccessible(true);
                    reflectMap.put(field.getName(),field.get(obj));
                }
            }else{
                for (Field field:fields){
                    field.setAccessible(true);
                    Column column = field.getAnnotation(Column.class);
                    if (column == null){
                        reflectMap.put(field.getName(),field.get(obj));
                    }else{
                        reflectMap.put(column.name(),field.get(obj));
                    }
                }
            }
            return reflectMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String,Object> reflect(Integer val,String name){
        reflectMap.put(name,val);
        return reflectMap;
    }
    public Map<String,Object> reflect(Float val,String name){
        reflectMap.put(name,val);
        return reflectMap;
    }
    public Map<String,Object> reflect(Long val,String name){
        reflectMap.put(name,val);
        return reflectMap;
    }
    public Map<String,Object> reflect(Double val,String name){
        reflectMap.put(name,val);
        return reflectMap;
    }
    public Map<String,Object> reflect(Short val,String name){
        reflectMap.put(name,val);
        return reflectMap;
    }
    public Map<String,Object> reflect(Character val,String name){
        reflectMap.put(name,val);
        return reflectMap;
    }
    public Map<String,Object> reflect(Boolean val,String name){
        reflectMap.put(name,val);
        return reflectMap;
    }
    public Map<String,Object> reflect(Byte val,String name){
        reflectMap.put(name,val);
        return reflectMap;
    }
    public Map<String,Object> reflect(BigDecimal val, String name){
        reflectMap.put(name,val);
        return reflectMap;
    }
    public Map<String,Object> reflect(Date val, String name){
        reflectMap.put(name,val);
        return reflectMap;
    }
    public Map<String,Object> reflect(String val, String name){
        reflectMap.put(name,val);
        return reflectMap;
    }

}
