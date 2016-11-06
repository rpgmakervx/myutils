package org.easyarch.myutils.cp.cfg;/**
 * Description : 
 * Created by YangZH on 16-11-4
 *  下午3:57
 */

import java.util.Properties;

/**
 * Description :
 * Created by code4j on 16-11-4
 * 下午3:57
 */

public class PoolConfig {


    private static int maxPoolSize;

    private static int minIdle;

    private static int maxIdle;

    private static long keepAliveTime;

    public static void config(int maxPoolSize,int minIdle,
                              int maxIdle,long keepAliveTime){
        PoolConfig.maxPoolSize = maxPoolSize<=0?Integer.MAX_VALUE:maxPoolSize;
        PoolConfig.minIdle = minIdle<=0?0:minIdle;
        PoolConfig.maxIdle = maxIdle<=0?Integer.MAX_VALUE:maxIdle;
        PoolConfig.keepAliveTime = keepAliveTime<=0?60:keepAliveTime;
    }

    public static void config(Properties prop){
        int maxPoolSize = prop.getProperty("maxPoolSize")==null?
                Runtime.getRuntime().availableProcessors()*4:Integer.parseInt(prop.getProperty("maxPoolSize"));
        int minIdle = prop.getProperty("minIdle")==null?
                0:Integer.parseInt(prop.getProperty("minIdle"));
        int maxIdle = prop.getProperty("maxIdle")==null?
                512:Integer.parseInt(prop.getProperty("maxIdle"));
        int keepAliveTime = prop.getProperty("keepAliveTime")==null?
                60:Integer.parseInt(prop.getProperty("keepAliveTime"));
        PoolConfig.maxPoolSize = maxPoolSize<=0?Integer.MAX_VALUE:maxPoolSize;
        PoolConfig.minIdle = minIdle<=0?0:minIdle;
        PoolConfig.maxIdle = maxIdle<=0?Integer.MAX_VALUE:maxIdle;
        PoolConfig.keepAliveTime = keepAliveTime<=0?60:keepAliveTime;
    }

    public static int getMaxPoolSize() {
        return maxPoolSize;
    }

    public static int getMinIdle() {
        return minIdle;
    }

    public static int getMaxIdle() {
        return maxIdle;
    }

    public static long getKeepAliveTime() {
        return keepAliveTime;
    }
}
