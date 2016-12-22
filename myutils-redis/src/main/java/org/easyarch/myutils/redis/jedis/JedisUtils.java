package org.easyarch.myutils.redis.jedis;

import redis.clients.jedis.ShardedJedis;

import java.io.InputStream;
import java.util.Properties;

/**
 * Description :
 * Created by xingtianyu on 16-12-20
 * 上午12:05
 * 提供三种初始化方式
 */

public class JedisUtils {

    private static JedisHelper helper;

    private JedisUtils(){}

    public static void init(String path){
        helper = new JedisHelper(path);
    }

    public static void init(Properties properties){
        helper = new JedisHelper(properties);
    }

    public static void init(InputStream stream){
        helper = new JedisHelper(stream);
    }

    public static void set(String key,String value){
        ShardedJedis jedis = helper.getShardedJedis();
        jedis.set(key,value);
        JedisHelper.recycle(jedis);
    }

    public static void setBytes(byte[] key,byte[] value){
        ShardedJedis jedis = helper.getShardedJedis();
        jedis.set(key,value);
        JedisHelper.recycle(jedis);
    }

    public static void setExp(String key,String value,int expire){
        ShardedJedis jedis = helper.getShardedJedis();
        jedis.set(key,value);
        jedis.expire(key,expire);
        JedisHelper.recycle(jedis);
    }
    public static void setBytesExp(byte[] key,byte[] value,int expire){
        ShardedJedis jedis = helper.getShardedJedis();
        jedis.set(key,value);
        jedis.expire(key,expire);
        JedisHelper.recycle(jedis);
    }
    public static void setnx(String key,String values){
        ShardedJedis jedis = helper.getShardedJedis();
        jedis.setnx(key,values);
        JedisHelper.recycle(jedis);
    }

    public static String get(String key){
        ShardedJedis jedis = helper.getShardedJedis();
        String value = jedis.get(key);
        JedisHelper.recycle(jedis);
        return value;
    }
    public static byte[] getBytes(byte[] key){
        ShardedJedis jedis = helper.getShardedJedis();
        byte[] value = jedis.get(key);
        JedisHelper.recycle(jedis);
        return value;
    }


    public static long del(String key){
        ShardedJedis jedis = helper.getShardedJedis();
        long count = jedis.del(key);
        JedisHelper.recycle(jedis);
        return count;
    }

    public static long append(String key,String value){

    }

    public static void main(String[] args) {
        ShardedJedis jedis = helper.getShardedJedis();
        jedis.append()
    }
}
