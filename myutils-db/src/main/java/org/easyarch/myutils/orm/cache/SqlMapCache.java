package org.easyarch.myutils.orm.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description :
 * Created by xingtianyu on 17-1-25
 * 上午12:10
 * description:
 */

public class SqlMapCache implements Cache<String,Map<String,String>> {

    private volatile Map<String,Map<String,String>> sqlMap = new ConcurrentHashMap<>();

    public String getSql(String namespace,String id){
        Map<String,String> statement = get(namespace);
        return statement.get(id);
    }

    public void addSql(String namespace,String id,String sql){
        if (sqlMap.containsKey(namespace)){
            Map<String,String> statement = get(namespace);
            statement.put(id,sql);
            return;
        }
        Map<String,String> statement = new ConcurrentHashMap<>();
        statement.put(id,sql);
        sqlMap.put(namespace,statement);
    }

    @Override
    public Map<String,String> get(String key) {
        return sqlMap.get(key);
    }

    @Override
    public void set(String key, Map<String,String> value) {
        sqlMap.put(key,value);
    }

    @Override
    public Map<String,String> remove(String key) {
        return sqlMap.remove(key);
    }


    @Override
    public void clear() {
        sqlMap.clear();
    }
}
