package org.easyarch.myutils.redis.jedis;

import org.easyarch.myutils.redis.jedis.enums.PropertyEnum;
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
        String[] vals = String.valueOf(PropertyEnum.CLUSTER.defVal).split(":");
        String name = vals[0];
        String ip = vals[1];
        Integer port = Integer.valueOf(vals[2]);
        shards.add(new JedisShardInfo(ip, port, name));
        shardedJedisPool = new ShardedJedisPool(config, shards);
    }

    public JedisHelper(Properties prop){
        init(prop);
    }

    public JedisHelper(String path){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        init(properties);
    }

    public JedisHelper() {
        this(100,15,1000l);
    }

    private void init(Properties prop){
        JedisPoolConfig config = new JedisPoolConfig();
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        int maxTotal = Integer.valueOf(
                prop.getProperty(PropertyEnum.MAXTOTAL.key));
        int maxIdle = Integer.valueOf(
                prop.getProperty(PropertyEnum.MAXIDLE.key));
        long maxWaitMillis = Long.valueOf(
                prop.getProperty(PropertyEnum.MAXWAITMILLS.key));
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        String line = prop.getProperty(PropertyEnum.CLUSTER.key);
        String[] clusters = line.split(",");
        for (String point:clusters){
            String[] param = point.split(":");
            String name = param[0];
            String ip = param[1];
            Integer port = Integer.valueOf(param[2]);
            JedisShardInfo info = new JedisShardInfo(ip,port,name);
            shards.add(info);
        }
        shardedJedisPool = new ShardedJedisPool(config, shards);
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
