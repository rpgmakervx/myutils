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

    private static int minInit;

    private static int maxWait;

    private static long keepAliveTime;

    public static void config(int maxPoolSize,int minInit,
                              int maxWait,int keepAliveTime){
        PoolConfig.maxPoolSize = maxPoolSize<=0?Integer.MAX_VALUE:maxPoolSize;
        PoolConfig.minInit = minInit<=0?0:minInit;
        PoolConfig.maxWait = maxWait<=0?Integer.MAX_VALUE:maxWait;
        PoolConfig.keepAliveTime = keepAliveTime<=0?60:keepAliveTime;
    }

    public static void config(Properties prop){
        int maxPoolSize = prop.getProperty("maxPoolSize")==null?
                Runtime.getRuntime().availableProcessors()*4:Integer.parseInt(prop.getProperty("maxPoolSize"));
        int minInit = prop.getProperty("maxPoolSize")==null?
                0:Integer.parseInt(prop.getProperty("maxPoolSize"));
        int maxWait = prop.getProperty("maxWait")==null?
                512:Integer.parseInt(prop.getProperty("maxWait"));
        int keepAliveTime = prop.getProperty("keepAliveTime")==null?
                60:Integer.parseInt(prop.getProperty("keepAliveTime"));
        PoolConfig.maxPoolSize = maxPoolSize<=0?Integer.MAX_VALUE:maxPoolSize;
        PoolConfig.minInit = minInit<=0?0:minInit;
        PoolConfig.maxWait = maxWait<=0?Integer.MAX_VALUE:maxWait;
        PoolConfig.keepAliveTime = keepAliveTime<=0?60:keepAliveTime;
    }

    public static int getMaxPoolSize() {
        return maxPoolSize;
    }

    public static int getMinInit() {
        return minInit;
    }

    public static int getMaxWait() {
        return maxWait;
    }

    public static long getKeepAliveTime() {
        return keepAliveTime;
    }
}
