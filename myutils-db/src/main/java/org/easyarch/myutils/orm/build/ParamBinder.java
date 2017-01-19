package org.easyarch.myutils.orm.build;

import org.easyarch.myutils.orm.annotation.entity.Column;
import org.easyarch.myutils.orm.annotation.entity.Table;
import org.easyarch.myutils.test.User;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Description :
 * Created by xingtianyu on 17-1-19
 * 下午7:42
 * description:
 */

public class ParamBinder {

    public Map<String,Object> reflect(Object[] obj,String[] name){
        Map<String,Object> reflectMap = new HashMap<>();
        for (int index=0;index<obj.length;index++){
            reflectMap.put(name[index],obj[index]);
        }
        return reflectMap;
    }

    public <T> Map<String,Object> reflect(Object obj,Class<T> clazz){
        try {
            Map<String,Object> reflectMap = new HashMap<>();
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

    public static void main(String[] args) {
        User user = new User();
        ParamBinder binder = new ParamBinder();
        user.setUsername("xingtianyu");
        user.setAge(20);
        user.setPassword("123456");
        user.setId(100001);
        user.setPhone("13652179825");
        System.out.println(binder.reflect(user,User.class));;
    }
}
