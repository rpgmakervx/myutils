package org.easyarch.myutils.algorithm.sort.bubble;

import org.easyarch.myutils.algorithm.sort.AbstractSort;

import java.util.List;

/**
 * Description :
 * Created by xingtianyu on 17-1-5
 * 下午5:41
 * description:
 * 相邻两组数值进行对比，正序排序，则后者小于前者时交换元素顺序。
 * 如果在一轮冒泡中没有产生一次交换，则说明已经有序，排序停止。
 */

public class BubbleSort extends AbstractSort {

    @Override
    public void sort(List<Integer> list, boolean desc) {
        int length = list.size();
        for (int outIndex = 0; outIndex < length; outIndex++) {
            boolean swaped = false;
            for (int inIndex = 1; inIndex < length; inIndex++) {
                System.out.println("(" + list.get(inIndex) + " < " + list.get(inIndex - 1) + "):" + (list.get(inIndex) < list.get(inIndex - 1)));
                if (list.get(inIndex) < list.get(inIndex - 1)) {
                    swap(list,inIndex,inIndex - 1);
                    swaped = true;
                }
            }
            if (!swaped) {
                break;
            }
        }
    }

    /**
     * 8, 3, 1, 5, 6, 0, 4, 7, 2, 9
     *
     * @param list
     * @param desc
     */
    @Override
    public void sort(Integer[] list, boolean desc) {
        int length = list.length;
        for (int outIndex = 0; outIndex < length; outIndex++) {
            boolean swaped = false;
            for (int inIndex = 1; inIndex < length; inIndex++) {
                System.out.println("(" + list[inIndex] + " < " + list[inIndex - 1] + "):" + (list[inIndex] < list[inIndex - 1]));
                if (list[inIndex] < list[inIndex - 1]) {
                    swap(list,inIndex,inIndex - 1);
                    swaped = true;
                }
            }
            if (!swaped) {
                break;
            }
        }
    }

}
