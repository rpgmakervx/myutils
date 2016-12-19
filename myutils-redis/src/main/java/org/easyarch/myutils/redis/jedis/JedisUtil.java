package org.easyarch.myutils.redis.jedis;

import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Description :
 * Created by xingtianyu on 16-12-14
 * 下午11:08
 */

public class JedisUtil {

    private ShardedJedisPool shardedJedisPool;

    private void initialShardedPool(int maxTotal,int maxIdle,long maxWaitMillis){

    }

    private void initialShardedPool() {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(1000l);
        // slave链接
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        shards.add(new JedisShardInfo("127.0.0.1", 6379, "master"));
        // 构造池
        shardedJedisPool = new ShardedJedisPool(config, shards);
    }

    /**
     * 构建redis连接池
     *
     * @param ip
     * @param port
     * @return JedisPool
     */
    public ShardedJedis getShardedJedis() {
        return shardedJedisPool.getResource();
    }

    /**
     * 返还到连接池
     *
     * @param pool
     * @param redis
     */
    public static void recycle (ShardedJedis redis) {
        if (redis != null) {
            redis.close();
        }
    }
}
