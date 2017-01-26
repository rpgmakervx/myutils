package org.easyarch.myutils.orm.mapping;

import org.easyarch.myutils.orm.annotation.sql.SqlParam;
import org.easyarch.myutils.orm.session.Configuration;
import org.easyarch.myutils.orm.build.SqlBuilder;
import org.easyarch.myutils.orm.cache.CacheFactory;
import org.easyarch.myutils.orm.cache.SqlMapCache;
import org.easyarch.myutils.orm.session.DBSession;
import org.easyarch.myutils.reflection.ReflectUtils;
import org.easyarch.myutils.test.User;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.easyarch.myutils.orm.parser.Token.SEPARATOR;

/**
 * Description :
 * Created by xingtianyu on 17-1-22
 * 下午11:17
 * description:
 */

public class MappedMethod {
    private DBSession session;
    private CacheFactory factory = CacheFactory.getInstance();
    private Configuration configuration;
    public MappedMethod(DBSession session,Configuration configuration) {
        this.session = session;
        this.configuration = configuration;
    }


//        String sql = "select * from t_user where id = $id$ and username like CONCAT('%',$username$,'%') and c > $age$";
    public Object delegateExecute(String interfaceName, Method method, Object[] args) {
        String sql = configuration.getMappedSql(interfaceName, method.getName());
        SqlMapCache cache = factory.getSqlMapCache();
        SqlBuilder builder = new SqlBuilder();
        ///检查缓存的sql
        if (cache.isHit(interfaceName,method.getName())){
            builder.setPreparedSql(cache.getSql(interfaceName,method.getName()));
            builder.setType(cache.getType(interfaceName,method.getName()));
        }else{
            //jsqlparser 在这一步，相对其他代码会慢一点
            builder.buildSql(sql);
        }
        Parameter[] parameters = method.getParameters();
        String[] paramNames = ReflectUtils.getMethodParameter(method);
        int paramIndex = 0;
        for (int index=0;index<parameters.length;index++) {
            if (isBaseType(parameters[index].getType())) {
                SqlParam sqlParam = parameters[index].getAnnotation(SqlParam.class);
                if (sqlParam == null) {
                    builder.buildParams(args[index],paramNames[paramIndex]);
                    paramIndex++;
                }else{
                    builder.buildParams(args[index],sqlParam.name());
                }
                continue;
            }
            if (args[index] instanceof Map) {
                builder.buildParams((Map<String,Object>)args[index]);
                continue;
            }
            builder.buildParams(args[index]);
        }
        builder.buildEntity();
        cache.addSql(interfaceName,method.getName(),builder.getPreparedSql());
        switch (builder.getType()){
            case SELECT:
                Class returnType = method.getReturnType();
                if (Collection.class.isAssignableFrom(returnType)){
                    return session.selectList(interfaceName+SEPARATOR+method.getName(),
                            ReflectUtils.getReturnType(method),builder.getParameters());
                }else{
                    return session.selectOne(interfaceName+SEPARATOR+method.getName(),
                            ReflectUtils.getReturnType(method),builder.getParameters());
                }
            case INSERT:return session.insert(
                    interfaceName+SEPARATOR+method.getName(),builder.getParameters());
            case UPDATE:return session.update(
                    interfaceName+SEPARATOR+method.getName(),builder.getParameters());
            case DELETE:return session.delete(
                    interfaceName+SEPARATOR+method.getName(),builder.getParameters());
        }
        System.out.println(builder);
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


    public void method(@SqlParam(name = "b")  String b, @SqlParam(name = "a") String a,@SqlParam(name = "c") String c) {
    }

    public void insert(User user){}
    public List<String> query(Map<String,Object> map){
        return new ArrayList<>();
    }

    public static void main(String[] args) throws NoSuchMethodException {
//        List<String> list = new ArrayList();
//        Class clazz = list.getClass();
//        System.out.println(Collection.class.isAssignableFrom(clazz));
        Method method = MappedMethod.class.getDeclaredMethod("query", Map.class);
        Type returnType = method.getGenericReturnType();// 返回类型
        System.out.println("  " + returnType);
        if (returnType instanceof ParameterizedType)/**//* 如果是泛型类型 */{
            Type[] types = ((ParameterizedType) returnType)
                    .getActualTypeArguments();// 泛型类型列表
            System.out.println("  TypeArgument: ");
            for (Type type : types) {
                System.out.println("   " + type);
            }
        }
    }

}
