package org.easyarch.myutils.orm.cache;

/**
 * Description :
 * Created by xingtianyu on 17-1-25
 * 上午12:30
 * description:
 */

public class CacheFactory {

    private static CacheFactory factory;

    private SqlMapCache sqlMapCache;

    private CacheFactory(){}

    public static CacheFactory getInstance(){
        synchronized (CacheFactory.class){
            if (factory == null){
                synchronized (CacheFactory.class){
                    factory = new CacheFactory();
                }
            }
        }
        return factory;
    }

    public SqlMapCache getSqlMapCache(){
        if (sqlMapCache == null){
            sqlMapCache = new SqlMapCache();
        }
        return sqlMapCache;
    }
}
