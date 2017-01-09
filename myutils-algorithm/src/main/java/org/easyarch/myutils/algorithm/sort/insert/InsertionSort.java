package org.easyarch.myutils.algorithm.sort.insert;

import org.easyarch.myutils.algorithm.sort.AbstractSort;

import java.util.List;

/**
 * Description :
 * Created by xingtianyu on 17-1-5
 * 下午1:06
 * description:
 */

public class InsertionSort extends AbstractSort {

    /**
     *
     * @param list
     * @param desc 排序顺序，比较方式不同
     */
    public void sort(List<Integer> list,boolean desc) {
        int length = list.size();
        for (int outIndex = 1; outIndex < length; outIndex++) {
            Integer tmp = list.get(outIndex);
            int inIndex = outIndex - 1;
            while (inIndex >= 0 && (desc?list.get(inIndex) < tmp:list.get(inIndex) > tmp)) {
                list.set(inIndex + 1,list.get(inIndex));
                inIndex--;
            }
            list.set(inIndex + 1,tmp);
        }
    }

    /**
     *
     * @param list
     * @param desc 排序顺序，比较方式不同
     */
    public void sort(Integer[] list,boolean desc) {
        int length = list.length;
        for (int outIndex = 1; outIndex < length; outIndex++) {
            Integer tmp = list[outIndex];
            int inIndex = outIndex - 1;
            while (inIndex >= 0 && (desc?list[inIndex] < tmp:list[inIndex] > tmp)) {
                list[inIndex + 1] = list[inIndex];
                inIndex--;
            }
            list[inIndex + 1] = tmp;
        }
    }

    public static void main(String[] args) {

    }
}
