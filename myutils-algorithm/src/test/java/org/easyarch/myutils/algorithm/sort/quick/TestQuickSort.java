package org.easyarch.myutils.algorithm.sort.quick;

import org.easyarch.myutils.algorithm.sort.selection.SelectionSort;
import org.easyarch.myutils.array.ArrayUtils;
import org.easyarch.myutils.array.handler.LoopHandler;

/**
 * @author xingtianyu(code4j) Created on 2018-3-25.
 */
public class TestQuickSort {

    public static void main(String[] args) {
//        Integer[] integers = new Integer[]{6,1,2,7,9,3,4,5,10,8};
//        Integer[] integers = new Integer[]{7,9,3,1,8,10,2,0,4};
//        Integer[] integers = new Integer[]{8, 3, 1, 5, 6, 0, 4, 7, 2, 9};
        Integer[] integers = new Integer[]{56,88,123,5,10,11,12,18,21,22,50,47,39,77,79,81};
        QuickSort sort = new QuickSort();
        sort.sort(integers,false);
        ArrayUtils.foreach(integers, new LoopHandler<Integer>() {
            public void loop(Integer elem, int index) {
                System.out.println(elem);
            }
        });
    }

}
