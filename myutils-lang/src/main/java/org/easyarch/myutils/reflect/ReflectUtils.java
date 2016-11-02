package org.easyarch.myutils.reflect;/**
 * Description : 
 * Created by YangZH on 16-11-3
 *  上午12:36
 */

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
    public static Object violentlyNewInstance(Class<?> clazz){
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            return clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object violentlyNewInstance(Class<?> clazz, Class[] parameterTypes, Object[] initargs) {
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor(parameterTypes);
            constructor.setAccessible(true);
            return constructor.newInstance(initargs);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }


    /**
     * 暴力反射获取字段值
     *
     * @param fieldName 属性名
     * @param obj       实例对象
     * @return 属性值
     */
    public static Object getFieldValue(Object obj, String fieldName) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    /**
     * 设置字段值
     *
     * @param propertyName 字段名
     * @param obj          实例对象
     * @param value        新的字段值
     * @return
     */
    public static void setFieldValue(Object obj, String propertyName, Object value) {
        try {
            Field field = obj.getClass().getDeclaredField(propertyName);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    /**
     * 暴力反射获取字段值
     *
     * @param propertyName 属性名
     * @param object       实例对象
     * @return 字段值
     */
    public static Object getter(Object object, String propertyName) {
        try {
            PropertyDescriptor pd = new PropertyDescriptor(propertyName, object.getClass());
            Method method = pd.getReadMethod();
            return method.invoke(object);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    /**
     * 设置字段值
     *
     * @param propertyName 属性名
     * @param value        新的字段值
     * @return
     */
    public static void setter(Object object, String propertyName, Object value){
        try{
            PropertyDescriptor pd = new PropertyDescriptor(propertyName, object.getClass());
            Method methodSet = pd.getWriteMethod();
            methodSet.invoke(object, value);
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
    @SuppressWarnings("rawtypes")
    public static Object methodInvoke(String className, String methodName, Class[] parameterTypes, Object[] values, Object object) {
        try {
            Method method = Class.forName(className).getDeclaredMethod(methodName, parameterTypes);
            method.setAccessible(true);
            return method.invoke(object, values);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

}
