package org.easyarch.myutils.redis.jedis;

import java.io.InputStream;
import java.util.Properties;

/**
 * Description :
 * Created by xingtianyu on 16-12-20
 * 上午12:05
 * 提供三种初始化方式
 */

public class JedisUtil {

    private static JedisHelper helper;

    private JedisUtil(){}

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
        helper.getShardedJedis().set(key,value);
    }
}
