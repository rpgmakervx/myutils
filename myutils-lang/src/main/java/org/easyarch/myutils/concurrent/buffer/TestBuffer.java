package org.easyarch.myutils.concurrent.buffer;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * @author code4j <xingtianyu03@kuaishou.com>
 * Created on 2020-04-05
 */
public class TestBuffer {

    public static void main(String[] args) {
        RefreshableLocalBuffer<Integer> buffer = new RefreshableLocalBuffer(10, 100,
                (Consumer<List<Integer>>) list -> {
                    System.out.println("batch consume size:" + list);
                });
        new Thread(() -> {
            AtomicInteger count = new AtomicInteger(0);
            while (true) {
                buffer.enqueue(count.incrementAndGet());
                try {
                    Thread.sleep(98);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

}
