package org.easyarch.myutils.orm.mapping;

import org.easyarch.myutils.orm.cache.CacheFactory;
import org.easyarch.myutils.orm.cache.ProxyCache;
import org.easyarch.myutils.orm.session.Configuration;
import org.easyarch.myutils.orm.session.impl.DelegeateDBSession;

import java.lang.reflect.Proxy;

/**
 * Description :
 * Created by xingtianyu on 17-1-25
 * 下午3:02
 * description:
 */

public class MapperProxyFactory<T> {

    private Class<T> interfaceClass;

    private Configuration configuration;
    /**
     * 缓存代理实例，减轻动态代理带来的性能损耗
     */
    private static ProxyCache proxyCache = CacheFactory.getInstance().getProxyCache();

    public MapperProxyFactory(Configuration configuration,Class interfaceClass) {
        this.configuration = configuration;
        this.interfaceClass = interfaceClass;
    }

    public T newInstance(DelegeateDBSession session) {
        if (proxyCache.isHit(interfaceClass)){
            return (T) proxyCache.get(interfaceClass);
        }
        MapperProxy mapperProxy = new MapperProxy(session, interfaceClass);
        T interFace = (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(), new Class[]{interfaceClass}, mapperProxy);
        proxyCache.set(interfaceClass,interFace);
        return interFace;
    }

}
