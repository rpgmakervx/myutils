package org.easyarch.myutils.algorithm.sort.bubble;

import org.easyarch.myutils.array.ArrayUtils;
import org.easyarch.myutils.array.handler.LoopHandler;
import org.junit.Test;

/**
 * Description :
 * Created by xingtianyu on 17-1-5
 * 下午8:36
 * description:
 */

public class TestBubbleSort {

    @Test
    public void testBubble(){
        Integer[] integers = new Integer[]{8, 3, 1, 5, 6, 0, 4, 7, 2, 9};
        BubbleSort sort = new BubbleSort();
        sort.sort(integers,false);
        ArrayUtils.foreach(integers, new LoopHandler<Integer>() {
            public void loop(Integer elem, int index) {
                System.out.println(elem);
            }
        });
    }
}
