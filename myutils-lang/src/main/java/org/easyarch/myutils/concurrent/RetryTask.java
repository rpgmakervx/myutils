package org.easyarch.myutils.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xingtianyu(code4j) Created on 2018-8-20.
 */
abstract public class RetryTask<E> implements Runnable{

    private Future future;

    private AtomicInteger retryTimes;

    private int retrys;

    private long interval;

    private TimeUnit unit;

    private E result;

    private CountDownLatch latch;

    private Exception exception;

    public RetryTask(int retryTimes, long interval, TimeUnit unit){
        this.retrys = retryTimes;
        this.retryTimes = new AtomicInteger(retryTimes);
        this.interval = interval;
        this.unit = unit;
        this.latch = new CountDownLatch(1);
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

    public void addException(Exception e){
        this.exception = e;
    }

    private void stop(){
        future.cancel(true);
        shutdown(retryTimes.get() != 0);
        return;
    }

    public E execResult(){
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.result;
    }

    abstract public E exec() throws Exception;

    protected void doExec() throws Exception{
        try {
            this.result = exec();
        }finally {
            latch.countDown();
        }
    }

    abstract public void retry(Exception exception,int times) throws StopException;

    abstract public void shutdown(boolean stopped);

    @Override
    public void run() {
        try {
            retry(this.exception,this.retrys - retryTimes.get() + 1);
        } catch (StopException e) {
            stop();
            return ;
        }catch (Exception exception){
            this.exception = exception;
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
