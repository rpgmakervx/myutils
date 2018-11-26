package org.easyarch.myutils.algorithm.struct.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xingtianyu on 2018/9/27.
 */
public class Heap<E> {

    private List<Comparable<E>> entities = new ArrayList<>();

    public static void main(String[] args) {
        List<Integer> entities = new ArrayList<>();
        entities.add(7);
        entities.add(1);
        entities.add(3);
        entities.add(10);
        entities.add(5);
        entities.add(2);
        entities.add(8);
        entities.add(9);
        entities.add(6);


    }

    public void adjustHeap(List<Comparable<E>> entities,int start,int length){

    }
}
