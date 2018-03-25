package org.easyarch.myutils.algorithm.sort.insert;

import org.easyarch.myutils.array.ArrayUtils;
import org.easyarch.myutils.array.handler.LoopHandler;
import org.easyarch.myutils.collection.CollectionUtils;
import org.junit.Test;

import java.util.List;

/**
 * Description :
 * Created by xingtianyu on 17-1-5
 * 下午1:34
 * description:
 */

public class TestInsertSort {

    @Test
    public void testInsertion(){
        InsertionSort sort = new InsertionSort();
        Integer[] integers = new Integer[]{8, 3, 1, 5, 6, 0, 4, 7, 2, 9};
//        sort.sort(integers);
        ArrayUtils.foreach(integers, new LoopHandler<Integer>() {
            public void loop(Integer elem, int index) {
                System.out.println(elem);
            }
        });
    }
    @Test
    public void testInsertionList(){
        InsertionSort sort = new InsertionSort();
//        List<Integer> integerList = CollectionUtils.newArrayList(8,4,9,7,2,5,6,1,0,3);
        List<Integer> integerList = CollectionUtils.newArrayList(8,7,6,5,4,3,2,0,0);
        sort.sort(integerList,false);
        for (Integer i:integerList){
            System.out.println(i);
        }
    }
}
