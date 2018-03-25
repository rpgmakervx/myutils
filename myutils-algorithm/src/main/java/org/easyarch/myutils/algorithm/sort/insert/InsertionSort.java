package org.easyarch.myutils.algorithm.sort.insert;

import org.easyarch.myutils.algorithm.sort.AbstractSort;

import java.util.List;

/**
 * Description :
 * Created by xingtianyu on 17-1-5
 * 下午1:06
 * description:
 * 插入排序记从低到高进行比对，正序排序，则遇到前者大于后者的情况，前者占用后者的位置,
 * 后者一步步向前占用前者剩下的位置，直到被插入的位置的元素找到了小于他的数，则停止占用，将这个数放入当前位置。
 *
 */

public class InsertionSort extends AbstractSort {

    /**
     *
     * @param list
     * @param desc 排序顺序，比较方式不同
     */
    @Override
    public void sort(List<Integer> list, boolean desc) {
        int length = list.size();
        for (int outIndex = 1; outIndex < length; outIndex++) {
            Integer tmp = list.get(outIndex);
            int inIndex = outIndex - 1;
            System.out.println("(" + list.get(inIndex) + " > " + tmp + "):" + (list.get(inIndex) > tmp));
            boolean insert = false;
            while (inIndex >= 0 && (desc?list.get(inIndex) < tmp:list.get(inIndex) > tmp)) {
                list.set(inIndex + 1,list.get(inIndex));
                inIndex--;
                insert = true;
                System.out.println(list);
            }
            if (insert){
                list.set(inIndex + 1,tmp);
                System.out.println("--"+list);
            }
        }
    }

    /**
     *
     * @param list
     * @param desc 排序顺序，比较方式不同
     */
    @Override
    public void sort(Integer[] list, boolean desc) {
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
