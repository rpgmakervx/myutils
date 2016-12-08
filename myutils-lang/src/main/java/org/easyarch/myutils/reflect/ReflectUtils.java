package org.easyarch.myutils.reflect;/**
 * Description : 
 * Created by YangZH on 16-11-3
 *  上午12:36
 */

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Description :
 * Created by code4j on 16-11-3
 * 上午12:36
 */

public class ReflectUtils {

    public static Object newInstance(Class<?> clazz){
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Object newInstance(Class<?> clazz, Class[] parameterTypes, Object[] initargs){
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor(parameterTypes);
            return constructor.newInstance(initargs);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Object newInstanceViolently(Class<?> clazz){
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            return clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object newInstanceViolently(Class<?> clazz, Class[] parameterTypes, Object[] initargs) {
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor(parameterTypes);
            constructor.setAccessible(true);
            return constructor.newInstance(initargs);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    public static Object getFieldValue(Object obj, String fieldName) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    public static void setFieldValue(Object obj, String propertyName, Object value) {
        try {
            Field field = obj.getClass().getDeclaredField(propertyName);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    public static Object getter(Object object, String propertyName) {
        try {
            PropertyDescriptor pd = new PropertyDescriptor(propertyName, object.getClass());
            Method method = pd.getReadMethod();
            if (method == null){
                throw new IllegalArgumentException("method may not exists");
            }
            return method.invoke(object);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    public static void setter(Object object, String propertyName, Object value){
        try{
            PropertyDescriptor pd = new PropertyDescriptor(propertyName, object.getClass());
            Method method = pd.getWriteMethod();
            if (method == null){
                throw new IllegalArgumentException("method may not exists");
            }
            method.invoke(object, value);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 设置字段值
     *
     * @param className      类的全路径名称
     * @param methodName     调用方法名
     * @param parameterTypes 参数类型
     * @param values         参数值
     * @param object         实例对象
     * @return
     */
    public static Object methodInvoke(String className, String methodName, Class[] parameterTypes, Object[] values, Object object) {
        try {
            Method method = Class.forName(className).getDeclaredMethod(methodName, parameterTypes);
            method.setAccessible(true);
            return method.invoke(object, values);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    public static PropertyDescriptor[] propertyDescriptors(Class<?> c){
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(c);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }

        return beanInfo.getPropertyDescriptors();
    }
}
