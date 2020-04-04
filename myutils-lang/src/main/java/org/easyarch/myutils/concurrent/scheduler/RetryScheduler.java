package org.easyarch.myutils.concurrent.scheduler;

import java.util.concurrent.*;

/**
 * @author xingtianyu(code4j) Created on 2018-8-20.
 */
public class RetryScheduler<T extends RetryTask<E>,E> {

    private ScheduledExecutorService scheduledExecutorService;

    private BlockingQueue<T> taskQueue;

    private ExecutorService threadPool;

    private int workerSize;

    private int queueSize;

    public RetryScheduler(int workerSize, int queueSize) {
        this.workerSize = workerSize;
        this.queueSize = queueSize;
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

    public void restart(){
        threadPool = Executors.newFixedThreadPool(workerSize);
        taskQueue = new ArrayBlockingQueue<T>(queueSize);
        scheduledExecutorService = Executors.newScheduledThreadPool(queueSize);
        start();
    }

    public void shutdown(){
        threadPool.shutdown();
        taskQueue.clear();
        scheduledExecutorService.shutdown();
    }

    final class RetryTaskHandler implements Runnable{

        @Override
        public void run() {
            while (taskQueue.size() != 0){
                T task = taskQueue.poll();
                try {
                    task.doExec();
                } catch (StopException e) {
                    task.shutdown(true);
                    continue;
                }catch (Exception e){
                    task.addException(e);
                    long interval = task.getInterval();
                    Future future = scheduledExecutorService.scheduleAtFixedRate(task,0,interval,TimeUnit.MILLISECONDS);
                    task.addFuture(future);
                }
            }
        }
    }


    public static void main(String[] args) {
        RetryScheduler scheduler = new RetryScheduler(2,10);
        RetryTask task = new RetryTask<String>(3,1,TimeUnit.SECONDS) {

            @Override
            public String exec() throws StopException, InterruptedException {
                System.out.println("执行任务");
                Thread.sleep(2000);
                throw new NullPointerException();
//                return "hello world";
            }

            @Override
            public void retry(Exception exception,int times) throws StopException {
                System.out.println("任务1重试第"+times+"次,异常内容："+exception);
            }

            @Override
            public void shutdown(boolean stopped) {
                System.out.println("任务1停止");
            }
        };
        scheduler.addTask(task);
        scheduler.start();

        System.out.println("下一步");
        System.out.println("获取执行结果："+task.execResult());
//        scheduler.shutdown();
//        scheduler.restart();
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
//        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("run");
//            }
//        },0,1000,TimeUnit.MILLISECONDS);

    }
}
