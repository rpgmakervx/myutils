package org.easyarch.myutils.redis.jedis;

import redis.clients.jedis.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Description :
 * Created by xingtianyu on 16-12-14
 * 下午11:08
 */

public class JedisHelper {

    private ShardedJedisPool shardedJedisPool;

    public JedisHelper(int maxTotal,int maxIdle,long maxWaitMillis){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        shards.add(new JedisShardInfo("127.0.0.1", 6379, "master"));
        shardedJedisPool = new ShardedJedisPool(config, shards);
    }

    public JedisHelper(Properties prop){

    }

    public JedisHelper(String path){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JedisHelper() {
        this(100,15,1000l);
    }

    /**
     * 构建redis连接池
     * @return JedisPool
     */
    public ShardedJedis getShardedJedis() {
        return shardedJedisPool.getResource();
    }

    /**
     * 返还到连接池
     * @param redis
     */
    public static void recycle (ShardedJedis redis) {
        if (redis != null) {
            redis.close();
        }
    }
}
