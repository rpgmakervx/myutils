package org.easyarch.myutils.concurrent;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xingtianyu(code4j) Created on 2018-8-20.
 */
abstract public class RetryTask implements Runnable{

    private Future future;

    private AtomicInteger retryTimes;

    private int retrys;

    private long interval;

    private TimeUnit unit;

    public RetryTask(int retryTimes, long interval, TimeUnit unit){
        this.retrys = retryTimes;
        this.retryTimes = new AtomicInteger(retryTimes);
        this.interval = interval;
        this.unit = unit;
    }

    private int countDown(){
        return retryTimes.decrementAndGet();
    }

    public long getInterval(){
        return unit.toMillis(this.interval);
    }

    public void addFuture(Future future){
        this.future = future;
    }

    private void stop(){
        future.cancel(true);
        shutdown(retryTimes.get() != 0);
        return;
    }

    abstract public void retry(int times) throws StopException;

    abstract public void shutdown(boolean stopped);

    @Override
    public void run() {
        try {
            retry(this.retrys - retryTimes.get() + 1);
        } catch (StopException e) {
            stop();
            return ;
        }
        int count = countDown();
        if (retryTimes.get() == 0){
            stop();
            return ;
        }
    }

    public static void main(String[] args) {
        System.out.println(TimeUnit.MINUTES.toMillis(2));
    }
}
