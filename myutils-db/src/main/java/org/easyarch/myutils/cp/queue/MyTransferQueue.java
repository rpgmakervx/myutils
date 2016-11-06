package org.easyarch.myutils.cp.queue;/**
 * Description : 
 * Created by YangZH on 16-11-6
 *  下午2:58
 */

/**
 * Description :
 * Created by code4j on 16-11-6
 * 下午2:58
 */

public class MyTransferQueue extends java.util.concurrent.LinkedTransferQueue {

    @Override
    public boolean offer(Object o) {
        try {
            transfer(o);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
