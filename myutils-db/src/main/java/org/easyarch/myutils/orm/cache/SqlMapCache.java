package org.easyarch.myutils.orm.cache;

import org.easyarch.myutils.orm.build.SqlBuilder;
import org.easyarch.myutils.orm.mapping.SqlType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description :
 * Created by xingtianyu on 17-1-25
 * 上午12:10
 * description:保存sql解析和参数构造的结果
 */

public class SqlMapCache implements Cache<String,Map<String,SqlBuilder>> {

    private volatile Map<String,Map<String,SqlBuilder>> sqlMap = new ConcurrentHashMap<>();

    public SqlBuilder getSqlBuilder(String namespace, String id){
        Map<String,SqlBuilder> statement = get(namespace);
        return statement.get(id);
    }

    public void addSqlBuilder(String namespace, String id, SqlBuilder sqlBuilder){
        if (sqlMap.containsKey(namespace)){
            Map<String,SqlBuilder> statement = get(namespace);
            statement.put(id,sqlBuilder);
            return;
        }
        Map<String,SqlBuilder> statement = new ConcurrentHashMap<>();
        statement.put(id,sqlBuilder);
        sqlMap.put(namespace,statement);
    }

    public String getSql(String namespace,String id){
        if(!isHit(namespace,id)){
            return null;
        }
        Map<String,SqlBuilder> statement = sqlMap.get(namespace);
        return statement.get(id).getPreparedSql();
    }

    public Object[] getParams(String namespace,String id){
        if(!isHit(namespace,id)){
            return null;
        }
        Map<String,SqlBuilder> statement = sqlMap.get(namespace);
        return statement.get(id).getParameters();
    }

    public SqlType getType(String namespace,String id){
        if(!isHit(namespace,id)){
            return null;
        }
        Map<String,SqlBuilder> statement = sqlMap.get(namespace);
        return statement.get(id).getType();
    }

    public boolean isHit(String namespace,String id){
        Map<String,SqlBuilder> map = get(namespace);
        if (map != null && map.containsKey(id)){
            return true;
        }
        return false;
    }

    @Override
    public Map<String,SqlBuilder> get(String key) {
        return sqlMap.get(key);
    }

    @Override
    public void set(String key, Map<String,SqlBuilder> value) {
        sqlMap.put(key,value);
    }

    @Override
    public Map<String,SqlBuilder> remove(String key) {
        return sqlMap.remove(key);
    }

    @Override
    public boolean isHit(String key) {
        return sqlMap.containsKey(key);
    }


    @Override
    public void clear() {
        sqlMap.clear();
    }
}
