package org.easyarch.myutils.algorithm.sort.selection;

import org.easyarch.myutils.array.ArrayUtils;
import org.easyarch.myutils.array.handler.LoopHandler;
import org.junit.Test;
/**
 * Description :
 * Created by xingtianyu on 17-1-8
 * 下午10:53
 * description:
 */

public class TestSelectionSort {

    @Test
    public void testSelection(){
        Integer[] integers = new Integer[]{8, 3, 1, 5, 6, 0, 4, 7, 2, 9};
        SelectionSort sort = new SelectionSort();
        sort.sort(integers,false);
        ArrayUtils.foreach(integers, new LoopHandler<Integer>() {
            public void loop(Integer elem, int index) {
                System.out.println(elem);
            }
        });
    }
}
