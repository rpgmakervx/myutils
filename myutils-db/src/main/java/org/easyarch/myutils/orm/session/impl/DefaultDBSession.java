package org.easyarch.myutils.orm.session.impl;

import org.easyarch.myutils.collection.CollectionUtils;
import org.easyarch.myutils.jdbc.exec.SqlExecutor;
import org.easyarch.myutils.jdbc.handler.BeanListResultSetHadler;
import org.easyarch.myutils.jdbc.handler.MapResultHandler;
import org.easyarch.myutils.orm.cache.CacheFactory;
import org.easyarch.myutils.orm.cache.ProxyCache;
import org.easyarch.myutils.orm.cache.SqlMapCache;
import org.easyarch.myutils.orm.mapping.MapperProxyFactory;
import org.easyarch.myutils.orm.session.Configuration;
import org.easyarch.myutils.orm.session.DBSession;

import java.util.List;
import java.util.Map;

import static org.easyarch.myutils.orm.parser.Token.SEPARATOR;

/**
 * Description :
 * Created by xingtianyu on 17-1-25
 * 上午12:38
 * description:
 */

public class DefaultDBSession implements DBSession {

    private CacheFactory factory = CacheFactory.getInstance();

    private SqlExecutor executor;

    private Configuration configuration;

    public DefaultDBSession(Configuration configuration,SqlExecutor executor) {
        this.executor = executor;
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String bind, Class<T> clazz, Object... parameters) {
        List<T> list = this.selectList(bind, clazz, parameters);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public <E> List<E> selectList(String bind, Class<E> clazz, Object... parameters) {
        SqlMapCache cache = factory.getSqlMapCache();
        String[] tokens = bind.split(SEPARATOR);
        String sql = cache.getSql(tokens[0], tokens[1]);
        List<E> list = executor.query(sql, new BeanListResultSetHadler<>(clazz), parameters);
        return list;
    }

    @Override
    public List<Map<String, Object>> selectMap(String bind, Object... parameters) {
        SqlMapCache cache = factory.getSqlMapCache();
        String[] tokens = bind.split(SEPARATOR);
        String sql = cache.getSql(tokens[0], tokens[1]);
        List<Map<String, Object>> list = executor.query(sql, new MapResultHandler(), parameters);
        return list;
    }

    @Override
    public int update(String bind, Object... parameter) {
        SqlMapCache cache = factory.getSqlMapCache();
        String[] tokens = bind.split(SEPARATOR);
        String sql = cache.getSql(tokens[0], tokens[1]);
        return executor.alter(sql, parameter);
    }

    @Override
    public int delete(String bind, Object... parameter) {
        return update(bind,parameter);
    }

    @Override
    public int insert(String bind, Object... parameter) {
        return update(bind,parameter);
    }

    @Override
    public <T> T getMapper(Class<T> clazz) {
        ProxyCache proxyCache = factory.getProxyCache();
        if (proxyCache.isHit(clazz)){
            return (T) proxyCache.get(clazz);
        }
        MapperProxyFactory<T> mapperProxyFactory = new MapperProxyFactory(configuration,clazz);
        return mapperProxyFactory.newInstance(this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public void close() {
        executor.close();
    }

    @Override
    public void rollback() {
        executor.rollback();
    }
}
