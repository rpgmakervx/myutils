package org.easyarch.myutils.algorithm.sort.selection;

import org.easyarch.myutils.algorithm.sort.AbstractSort;

import java.util.List;

/**
 * Description :
 * Created by xingtianyu on 17-1-8
 * 下午10:41
 * description:
 * 选择排序的基本原则是给当前位置寻找合适元素。本质上就是从第一个位置开始找最小或最大元素
 * 每一趟遍历都为当前位置找出最小元素
 *
 * 不稳定排序，因为在找到合适元素的时候，如果合适元素在相同元素之后，就破坏了稳定性：5 8 3 5 2，第一个5在选择后会出现在第二个5之后。
 */

public class SelectionSort extends AbstractSort {

    public void sort(List<Integer> list, boolean desc) {
        int length = list.size();
        for (int outIndex = 0; outIndex < length; outIndex++) {
            Integer min = list.get(outIndex);
            int minIndex = outIndex;
            for (int inIndex = outIndex; inIndex < length; inIndex++){
                if (min > list.get(inIndex)){
                    min = list.get(inIndex);
                    minIndex = inIndex;
                }
            }
            swap(list,minIndex,outIndex);
        }
    }

    public void sort(Integer[] list, boolean desc) {
        int length = list.length;
        for (int outIndex = 0; outIndex < length; outIndex++) {
            Integer min = list[outIndex];
            int minIndex = outIndex;
            for (int inIndex = outIndex; inIndex < length; inIndex++){
                if (min > list[inIndex]){
                    min = list[inIndex];
                    minIndex = inIndex;
                }
            }
            swap(list,minIndex,outIndex);
        }
    }
}
