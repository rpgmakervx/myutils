package org.easyarch.myutils.orm.mapping;

import org.easyarch.myutils.orm.annotation.sql.Mapper;
import org.easyarch.myutils.orm.cache.CacheFactory;
import org.easyarch.myutils.orm.cache.InterfaceCache;
import org.easyarch.myutils.orm.session.Configuration;
import org.easyarch.myutils.orm.session.DBSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Description :
 * Created by xingtianyu on 17-1-24
 * 下午8:17
 * description:
 */

public class MapperProxy implements InvocationHandler {
//
//    private static final String EQUALS = "equals";
//    public static final String HASHCODE = "hashCode";
//    public static final String TOSTRING = "toString";
//    public static final String GETCLASS = "getClass";
//    public static final String CLONE = "clone";
//    public static final String WAIT = "wait";
//    public static final String NOTIFY = "notify";
//    public static final String NOTIFYALL = "notifyAll";

    private DBSession session;

    private Configuration configuration;

    private Class<?> interfaceClass;

    private InterfaceCache interfaceCache = CacheFactory.getInstance().getInterfaceCache();

    public MapperProxy(DBSession session,Configuration configuration,Class interfaceClass){
        this.session = session;
        this.configuration = configuration;
        this.interfaceClass = interfaceClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MappedMethod mappedMethod = new MappedMethod(session,configuration);
        String methodName = method.getName();
        if (DefaultMethod.contains(methodName)){
            return method.invoke(proxy,args);
        }
        ClassItem item = interfaceCache.get(interfaceClass);
        if (item != null){
            return mappedMethod.delegateExecute(item.getItemName(),method,args);
        }
        Mapper mapper = interfaceClass.getAnnotation(Mapper.class);
        String namespace = interfaceClass.getName();
        if (mapper != null && !mapper.namespace().isEmpty()){
            namespace = mapper.namespace();
        }
        ClassItem classItem = new ClassItem(namespace,interfaceClass,interfaceClass.getDeclaredMethods());
        interfaceCache.set(interfaceClass,classItem);
        return mappedMethod.delegateExecute(namespace,method,args);
    }
}
