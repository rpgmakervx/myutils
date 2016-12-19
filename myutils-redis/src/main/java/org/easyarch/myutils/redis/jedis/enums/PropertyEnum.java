package org.easyarch.myutils.redis.jedis.enums;

/**
 * Description :
 * Created by xingtianyu on 16-12-19
 * 下午11:43
 */

public enum  PropertyEnum {

    MAXTOTAL("redis.pool.maxTotal",200),
    MAXIDLE("redis.pool.maxIdle",15),
    MAXWAITMILLS("redis.pool.maxWaitMillis",1000L),
    CLUSTER("redis.cluster","master:127.0.0.1:6379");


    public String key;
    public Object defVal;

    PropertyEnum(String key, Object defVal) {
        this.key = key;
        this.defVal = defVal;
    }
}
