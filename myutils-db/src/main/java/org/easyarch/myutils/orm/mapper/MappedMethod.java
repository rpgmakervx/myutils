package org.easyarch.myutils.orm.mapper;

import org.easyarch.myutils.orm.annotation.sql.SqlParam;
import org.easyarch.myutils.orm.build.SqlBuilder;
import org.easyarch.myutils.orm.session.Configuration;
import org.easyarch.myutils.orm.session.DBSession;
import org.easyarch.myutils.reflection.ReflectUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * Description :
 * Created by xingtianyu on 17-1-22
 * 下午11:17
 * description:
 */

public class MappedMethod {
    private DBSession session;
    private SqlType type;

    public MappedMethod(DBSession session, SqlType type) {
        this.session = session;
        this.type = type;
    }


    public Object delegateExecute(String interfaceName, Method method, Object[] args) {
        String sql = Configuration.getInstance().getMappedSql(interfaceName, method.getName());
        SqlBuilder builder = new SqlBuilder();
        builder.buildSql(sql);
        Class[] classes = method.getParameterTypes();
//        Annotation[][] annotations = method.getParameterAnnotations();
        Parameter[] parameters = method.getParameters();
//        for (Class clazz:classes) {
//            if (isBaseType(clazz)) {
//
//            }
//            if (clazz == Map.class) {
//
//            }
//        }
        String[] paramNames = ReflectUtils.getMethodParameter(method);
        int index = 0;
        for (Parameter param : parameters) {
            if (isBaseType(param.getClass())) {
                SqlParam sqlParam = param.getAnnotation(SqlParam.class);
                if (sqlParam == null) {
                    builder.buildParams(args[index],paramNames[index]);
                }else{
                    builder.buildParams(args[index],sqlParam.name());
                }
            }
            if (Map.class.equals(param.getClass())) {

            }
            index++;
        }
        return null;
    }

    private boolean isBaseType(Class clazz) {
        if (clazz == String.class) {
            return true;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return true;
        }
        if (clazz == float.class || clazz == Float.class) {
            return true;
        }
        if (clazz == long.class || clazz == Long.class) {
            return true;
        }
        if (clazz == double.class || clazz == Double.class) {
            return true;
        }
        if (clazz == short.class || clazz == Short.class) {
            return true;
        }
        if (clazz == byte.class || clazz == Byte.class) {
            return true;
        }
        if (clazz == boolean.class || clazz == Boolean.class) {
            return true;
        }
        if (clazz == char.class || clazz == Character.class) {
            return true;
        }
        return false;
    }


    public void method(@SqlParam(name = "a") String a, String b, @SqlParam(name = "c") String c) {

    }

    public static void main(String[] args) throws NoSuchMethodException {
        Method method = MappedMethod.class.getMethod("method", String.class, String.class, String.class);
        MappedMethod mm = new MappedMethod(null, null);
        Annotation[][] annotations = method.getParameterAnnotations();
        Parameter[] param = method.getParameters();
        for (Parameter p : param) {
            System.out.println(p.isNamePresent());
        }
//        for (int index = 0;index<annotations.length;index++){
//            for (int i=0;i<annotations[index].length;i++){
//                SqlParam sqlParam = (SqlParam) annotations[index][i];
//                System.out.println(sqlParam.name());
//            }
//        }
//        Annotation[][] anns = method.getParameterAnnotations();
//        for (Annotation[] aa : anns) {
//            for (Annotation a : aa) {
//                System.out.println(a.annotationType());
//            }
//        }
    }
}
