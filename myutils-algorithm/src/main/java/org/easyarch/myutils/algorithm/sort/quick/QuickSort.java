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
        this.desc = desc;
        quickSort(list,0,list.length - 1);
    }

    /**
     * 7,9,3,1,8,10,2,0,4
     * @param list
     * @param begin
     * @param end
     */
    private void quickSort(Integer[] list,int begin,int end){
        int lower = begin;
        int upper = end;
        if (lower >= upper){
            return;
        }
        Integer base = list[begin];
        while (lower < upper){
            while (list[upper] >= base && upper > lower){
                upper--;
            }
            list[lower] = list[upper];
            while (list[lower] <= base && upper > lower){
                lower++;
            }
            list[upper] = list[lower];
        }
        list[upper] = base;
        System.out.println(Arrays.asList(list));
        quickSort(list,begin,lower-1);
        quickSort(list,upper+1,end);

    }
}
