package org.easyarch.myutils.orm.session.impl;

import org.easyarch.myutils.collection.CollectionUtils;
import org.easyarch.myutils.db.exec.SqlExecutor;
import org.easyarch.myutils.db.handler.BeanListResultSetHadler;
import org.easyarch.myutils.db.handler.MapResultHandler;
import org.easyarch.myutils.orm.cache.CacheFactory;
import org.easyarch.myutils.orm.cache.ProxyCache;
import org.easyarch.myutils.orm.cache.SqlMapCache;
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

    public DefaultDBSession(SqlExecutor executor) {
        this.executor = executor;
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
        return (T) proxyCache.get(clazz);
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
