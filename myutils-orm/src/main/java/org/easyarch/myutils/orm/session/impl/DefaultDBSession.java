package org.easyarch.myutils.orm.session.impl;

import org.easyarch.myutils.collection.CollectionUtils;
import org.easyarch.myutils.orm.jdbc.exec.SqlExecutor;
import org.easyarch.myutils.orm.jdbc.handler.BeanListResultSetHadler;
import org.easyarch.myutils.orm.jdbc.handler.MapResultHandler;
import org.easyarch.myutils.orm.entity.SqlEntity;
import org.easyarch.myutils.orm.cache.CacheFactory;
import org.easyarch.myutils.orm.cache.SqlMapCache;
import org.easyarch.myutils.orm.session.Configuration;
import org.easyarch.myutils.orm.session.DBSessionAdapter;

import java.util.List;
import java.util.Map;

import static org.easyarch.myutils.orm.parser.Token.BIND_SEPARATOR;

/**
 * Description :
 * Created by xingtianyu on 17-1-25
 * 上午12:38
 * description:
 */

public class DefaultDBSession extends DBSessionAdapter {

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
        String[] tokens = bind.split(BIND_SEPARATOR);
        SqlEntity entity = cache.getSqlEntity(tokens[0], tokens[1]);
        String sql = entity.getPreparedSql();
        List<E> list = executor.query(sql, new BeanListResultSetHadler<>(clazz), parameters);
        return list;
    }

    @Override
    public List<Map<String, Object>> selectMap(String bind, Object... parameters) {
        SqlMapCache cache = factory.getSqlMapCache();
        String[] tokens = bind.split(BIND_SEPARATOR);
        SqlEntity entity = cache.getSqlEntity(tokens[0], tokens[1]);
        String sql = entity.getPreparedSql();
        List<Map<String, Object>> list = executor.query(sql, new MapResultHandler(), parameters);
        return list;
    }

    @Override
    public int update(String bind, Object... parameter) {
        SqlMapCache cache = factory.getSqlMapCache();
        String[] tokens = bind.split(BIND_SEPARATOR);
        SqlEntity entity = cache.getSqlEntity(tokens[0], tokens[1]);
        String sql = entity.getPreparedSql();
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
