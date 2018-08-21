package org.easyarch.myutils.concurrent;

import java.util.concurrent.*;

/**
 * @author xingtianyu(code4j) Created on 2018-8-20.
 */
public class RetryScheduler<T extends RetryTask> {

    private ScheduledExecutorService scheduledExecutorService;

    private BlockingQueue<T> taskQueue;

    private ExecutorService threadPool;

    public RetryScheduler(int workerSize, int queueSize) {
        threadPool = Executors.newFixedThreadPool(workerSize);
        taskQueue = new ArrayBlockingQueue<T>(queueSize);
        scheduledExecutorService = Executors.newScheduledThreadPool(queueSize);
    }

    public void addTask(T task){
        taskQueue.add(task);
    }

    public void start(){
        threadPool.submit(new RetryTaskHandler());
    }

    final class RetryTaskHandler implements Runnable{

        @Override
        public void run() {
            while (taskQueue.size() != 0){
                T task = taskQueue.poll();
                long interval = task.getInterval();
                Future future = scheduledExecutorService.scheduleAtFixedRate(task,0,interval,TimeUnit.MILLISECONDS);
                task.addFuture(future);
            }
        }
    }


    public static void main(String[] args) {
        RetryScheduler scheduler = new RetryScheduler(2,10);
        scheduler.addTask(new RetryTask(3,1,TimeUnit.SECONDS) {
            @Override
            public void retry(int times) {
                System.out.println("任务1重试第"+times+"次");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shutdown() {
                System.out.println("任务1停止");
            }
        });
        scheduler.start();
        System.out.println("下一步");
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
//        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("run");
//            }
//        },0,1000,TimeUnit.MILLISECONDS);

    }
}
