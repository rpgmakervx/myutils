package org.easyarch.myutils.algorithm.sort;

import java.util.List;

/**
 * Description :
 * Created by xingtianyu on 17-1-8
 * 下午10:48
 * description:
 */

public abstract class AbstractSort implements Sort {

    protected void swap(Integer[] list, int left, int right) {
        int tmp = list[left];
        list[left] = list[right];
        list[right] = tmp;
    }

    protected void swap(List<Integer> list, int left, int right) {
        int tmp = list.get(left);
        list.set(left,list.get(right));
        list.set(right,tmp);
    }
}
