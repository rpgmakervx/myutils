package org.easyarch.myutils.algorithm.sort.selection;

import org.easyarch.myutils.algorithm.sort.quick.QuickSort;
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
        Integer[] integers = new Integer[]{56,88,123,5,12,18,21,22,50,47,39,77,79,81};
//        Integer[] integers = new Integer[]{7,9,3,1,8,10,2,0,4};
        QuickSort sort = new QuickSort();
        sort.sort(integers,false);
        ArrayUtils.foreach(integers, new LoopHandler<Integer>() {
            public void loop(Integer elem, int index) {
                System.out.println(elem);
            }
        });
    }
}
