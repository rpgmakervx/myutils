package org.easyarch.myutils.cp.cfg;/**
 * Description : 
 * Created by YangZH on 16-11-4
 *  下午3:57
 */

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Description :
 * Created by code4j on 16-11-4
 * 下午3:57
 */

public class PoolConfig {


    private static int maxPoolSize;

    private static int maxWait;

    private static long keepAliveTime;

    public static void config(int maxPoolSize,
                              int maxWait,int keepAliveTime){
        PoolConfig.maxPoolSize = maxPoolSize;
        PoolConfig.maxWait = maxWait;
        PoolConfig.keepAliveTime = keepAliveTime;
        Executors.newCachedThreadPool()
    }
}
