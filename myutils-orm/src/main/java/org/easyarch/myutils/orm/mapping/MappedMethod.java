package org.easyarch.myutils.orm.mapping;

import org.easyarch.myutils.orm.annotation.sql.SqlParam;
import org.easyarch.myutils.orm.build.SqlBuilder;
import org.easyarch.myutils.orm.entity.SqlEntity;
import org.easyarch.myutils.orm.cache.CacheFactory;
import org.easyarch.myutils.orm.cache.SqlMapCache;
import org.easyarch.myutils.orm.session.Configuration;
import org.easyarch.myutils.orm.session.impl.MapperDBSession;
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

import static org.easyarch.myutils.orm.parser.Token.BIND_SEPARATOR;

/**
 * Description :
 * Created by xingtianyu on 17-1-22
 * 下午11:17
 * description:
 */

public class MappedMethod {
    private MapperDBSession session;

    private CacheFactory factory = CacheFactory.getInstance();

    public MappedMethod(MapperDBSession session) {
        this.session = session;
    }

//        String sql = "select * from t_user where id = $id$ and username like CONCAT('%',$username$,'%') and c > $age$";
    public Object delegateExecute(String interfaceName, Method method, Object[] args) {
        Configuration configuration = session.getConfiguration();
        SqlMapCache cache = factory.getSqlMapCache();
        ///检查缓存的sql
        SqlBuilder builder = new SqlBuilder();
        if (cache.isHit(interfaceName,method.getName())){
            SqlEntity entity = cache.getSqlEntity(interfaceName,method.getName());
            builder.buildEntity(entity);
        }else{
            String sql = configuration.getMappedSql(interfaceName, method.getName());
            //jsqlparser 在这一步，相对其他代码会慢一点
            builder.buildSql(sql);
            Parameter[] parameters = method.getParameters();
            String[] paramNames = ReflectUtils.getMethodParameter(method);
            int paramIndex = 0;
            for (int index=0;index<parameters.length;index++) {
                if (ReflectUtils.isBaseType(parameters[index].getType())) {
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
            SqlEntity entity = builder.buildEntity(interfaceName + BIND_SEPARATOR + method.getName());
            cache.addSqlEntity(entity);
        }
        switch (builder.getType()){
            case SELECT:
                Class returnType = method.getReturnType();
                if (Collection.class.isAssignableFrom(returnType)){
                    return session.selectList(builder.getPreparedSql(),
                            ReflectUtils.getReturnType(method),builder.getParameters());
                }else{
                    return session.selectOne(builder.getPreparedSql(),
                            ReflectUtils.getReturnType(method),builder.getParameters());
                }
            case INSERT:return session.insert(builder.getPreparedSql(),builder.getParameters());
            case UPDATE:return session.update(builder.getPreparedSql(),builder.getParameters());
            case DELETE:return session.delete(builder.getPreparedSql(),builder.getParameters());
        }
        System.out.println(builder);
        return null;
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
