package org.easyarch.myutils.algorithm.sort.selection;

import org.easyarch.myutils.algorithm.sort.AbstractSort;

import java.util.List;

/**
 * Description :
 * Created by xingtianyu on 17-1-8
 * 下午10:41
 * description:
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
