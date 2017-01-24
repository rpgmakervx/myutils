package org.easyarch.myutils.orm.session.impl;

import org.easyarch.myutils.collection.CollectionUtils;
import org.easyarch.myutils.db.exec.SqlExecutor;
import org.easyarch.myutils.db.handler.BeanListResultSetHadler;
import org.easyarch.myutils.orm.cache.CacheFactory;
import org.easyarch.myutils.orm.cache.SqlMapCache;
import org.easyarch.myutils.orm.session.DBSession;

import java.util.List;

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
    public int update(String bind, Object... parameter) {
        SqlMapCache cache = factory.getSqlMapCache();
        String[] tokens = bind.split(SEPARATOR);
        String sql = cache.getSql(tokens[0], tokens[1]);
        return executor.alter(sql, parameter);
    }

    @Override
    public int update(Object bean) {
        return 0;
    }

    @Override
    public int delete(String bind, Object... parameter) {
        return 0;
    }

    @Override
    public int delete(Object bean) {
        return 0;
    }

    @Override
    public int insert(String bind, Object... parameter) {
        return 0;
    }

    @Override
    public int insert(Object bean) {
        return 0;
    }
}
