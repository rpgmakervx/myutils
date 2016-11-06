package org.easyarch.myutils.cp.factory;/**
 * Description : 
 * Created by YangZH on 16-11-4
 *  上午2:00
 */


import org.easyarch.myutils.cp.cfg.PoolConfig;
import org.easyarch.myutils.cp.ds.DBCPool;
import org.easyarch.myutils.cp.queue.MyTransferQueue;

import javax.sql.DataSource;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * Description :
 * Created by code4j on 16-11-4
 * 上午2:00
 */

public class ConnectionFactory {

    public static DataSource newFixedDBCPool(int maxPoolSize) {
        return new DBCPool(maxPoolSize, maxPoolSize >> 2, maxPoolSize >> 3,
                1000 * 60L, new LinkedBlockingQueue(maxPoolSize));
    }

    public static DataSource newCachedDBCPool() {
        return new DBCPool(Integer.MAX_VALUE, 1, 0,
                1000 * 60L, new MyTransferQueue());
    }
    public static DataSource newConfigedDBCPool() {
        return new DBCPool(new LinkedBlockingQueue(PoolConfig.getMaxIdle()));
    }


    public static void main(String[] args) throws InterruptedException {
        final TransferQueue<String> queue = new LinkedTransferQueue<String>();
//        final LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
//        final SynchronousQueue<String> queue = new SynchronousQueue<String>();
//        final ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int index = 0;
                while (true) {
                    try {
                        queue.add("xingtianyu-" + index);
                        System.out.println(queue.size());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " transfered a data");
                    index++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

//        Thread.sleep(1000);
//        queue.take();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true){
//                    try {
//                        System.out.println(queue.take());
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
    }
}
