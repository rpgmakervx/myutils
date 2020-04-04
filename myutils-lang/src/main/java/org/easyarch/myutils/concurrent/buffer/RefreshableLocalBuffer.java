package org.easyarch.myutils.concurrent.buffer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

/**
 * @author code4j <xingtianyu03@kuaishou.com>
 * Created on 2020-04-04
 * 可刷新的本地缓冲区。
 * 刷新条件：
 * 1. 缓冲区大小超过batchSize
 * 2. 固定时长刷新一次
 */
public class RefreshableLocalBuffer<E extends Serializable> implements LocalBuffer<E> {

    private static final int DEFAULT_BUFFER_SIZE = 100;
    private static final long INTERVAL = 1000L;
    private static final int SCHEDULER_POOL_SIZE = 1;

    private static final String SCHEDULER_THREAD_NAME = "local-buffer-consume-scheduler";

    protected BlockingQueue<E> internalQueue;

    /**
     * 调度
     */
    protected ScheduledExecutorService scheduler;

    /**
     * 消费者执行函数
     */
    protected Consumer<Collection<E>> consumer;

    /**
     * 判断任务是否在执行
     */
    private volatile boolean running;

    /**
     * 缓冲区大小
     */
    protected int batchSize;
    /**
     * refresh任务执行周期
     */
    protected long interval;

    protected ReentrantLock lock = new ReentrantLock();

    /**
     * 当前队列长度
     */
    protected AtomicInteger size = new AtomicInteger(0);


    public RefreshableLocalBuffer(int batchSize, Consumer<Collection<E>> consumer) {
        this(batchSize, DEFAULT_BUFFER_SIZE, INTERVAL, consumer);
    }

    public RefreshableLocalBuffer(int batchSize, int bufferSize, Consumer<Collection<E>> consumer) {
        this(batchSize, bufferSize, INTERVAL, consumer);
    }

    public RefreshableLocalBuffer(BlockingQueue<E> internalQueue,
            ScheduledExecutorService scheduler, Consumer<Collection<E>> consumer) {
        this.internalQueue = internalQueue;
        this.scheduler = scheduler;
        this.consumer = consumer;
    }

    public RefreshableLocalBuffer(int batchSize, int bufferSize, long interval, Consumer<Collection<E>> consumer) {
        if (bufferSize <= 0) {
            throw new IllegalArgumentException("buffer size should be positive");
        }
        if (batchSize <= 0) {
            throw new IllegalArgumentException("batch size should be positive");
        }
        this.batchSize = batchSize;
        this.interval = interval;
        this.internalQueue = new ArrayBlockingQueue(bufferSize);
        this.consumer = consumer;
        this.scheduler = Executors.newScheduledThreadPool(SCHEDULER_POOL_SIZE, newTheadFactory());
        this.scheduler.scheduleAtFixedRate(new BatchConsumeTask(), interval, interval, TimeUnit.MILLISECONDS);
    }


    @Override
    public void enqueue(E element) {
        try {
            internalQueue.put(element);
            size.incrementAndGet();
            tryRefresh();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public int length() {
        return size.get();
    }


    /**
     * 检查队列是否已满足刷新条件
     * 满足则进行一次刷新
     * @return
     */
    private void tryRefresh() {
        lock.lock();
        try {
            if (size.get() >= this.batchSize) {
                List<E> dataList = new ArrayList<>();
                internalQueue.drainTo(dataList, this.batchSize);
                size.addAndGet(-this.batchSize);
                this.consumer.accept(dataList);
            }
        } finally {
            lock.unlock();
        }
    }

    private ThreadFactory newTheadFactory() {
        return r -> {
            Thread thread = new Thread(r, SCHEDULER_THREAD_NAME);
            thread.setDaemon(true);
            return thread;
        };
    }

    final class BatchConsumeTask implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                int consumedSize = 0;
                while (true) {
                    List<E> dataList = new ArrayList<>(batchSize);
                    internalQueue.drainTo(dataList, batchSize);
                    if (dataList.isEmpty()) {
                        break;
                    }
                    consumer.accept(dataList);
                    size.addAndGet(-dataList.size());
                }
            } finally {
                lock.unlock();
            }
        }
    }


}
