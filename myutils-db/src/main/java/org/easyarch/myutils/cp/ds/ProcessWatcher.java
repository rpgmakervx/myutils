package org.easyarch.myutils.cp.ds;/**
 * Description : 
 * Created by YangZH on 16-11-7
 *  下午8:23
 */

import java.sql.Connection;

/**
 * Description :
 * Created by code4j on 16-11-7
 * 下午8:23
 */

public class ProcessWatcher implements Monitor{
    static Runtime rt = Runtime.getRuntime();
    static{
        System.out.println("watcher is ready.");
        rt.addShutdownHook(new Thread() {
            public void run() {
                System.out.println("programme exit,ready to close all dbcpool connections ... ");
                System.out.println("connections count is :"+ DBCPool.realconns.size());
                DBCPool.kill();
            }
        });

    }

    @Override
    public void onBroken(Connection connection) {

    }

    @Override
    public void onCreate(Connection connection) {

    }
}
