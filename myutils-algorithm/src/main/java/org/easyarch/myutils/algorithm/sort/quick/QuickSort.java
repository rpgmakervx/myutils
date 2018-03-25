package org.easyarch.myutils.algorithm.sort.quick;

import org.easyarch.myutils.algorithm.sort.AbstractSort;
import sun.misc.OSEnvironment;

import java.util.Arrays;
import java.util.List;

/**
 * @author xingtianyu(code4j) Created on 2018-3-25.
 * description:
 * 选取一个基准值，从两端开始依次遍历（大端开始），大端小于基准值则覆盖当前小端，小端大于基准值则覆盖当前大端，
 * 若当前大小端相遇，则将基准值放置于此。然后两边形成 大端都大于基准值，小端都小于基准值的模型，大小端再分别递归
 */
public class QuickSort extends AbstractSort{

    private boolean desc;

    @Override
    public void sort(List<Integer> list, boolean desc) {
    }

    @Override
    public void sort(Integer[] list, boolean desc) {
        sort(list,0,list.length - 1);
    }

    /**
     * 7,9,3,1,8,10,2,0,4
     * @param list
     * @param begin
     * @param end
     */
    private int partition(Integer[] list,int begin,int end){
        int lower = begin;
        int upper = end;
        Integer base = list[begin];
        while (lower < upper){
            while (list[upper] >= base && lower < upper){
                upper--;
            }
            list[lower] = list[upper];
            while (list[lower] <= base && lower < upper){
                lower++;
            }
            list[upper] = list[lower];
        }
        list[upper] = base;
        return upper;
    }

    private void sort(Integer[] list,int begin,int end){
        if (begin > end){
            return;
        }
        int position = partition(list,begin,end);
        sort(list,begin,position - 1);
        sort(list,position + 1,end);
    }

}
