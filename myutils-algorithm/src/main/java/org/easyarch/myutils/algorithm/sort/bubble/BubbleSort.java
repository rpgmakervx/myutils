package org.easyarch.myutils.algorithm.sort.bubble;

import org.easyarch.myutils.algorithm.sort.Sort;

import java.util.List;

/**
 * Description :
 * Created by xingtianyu on 17-1-5
 * 下午5:41
 * description:
 */

public class BubbleSort implements Sort {
    public void sort(List<Integer> list, boolean desc) {
        int length = list.size();
        for (int outIndex = 0; outIndex < length; outIndex++) {
            boolean swaped = false;
            for (int inIndex = 1; inIndex < length; inIndex++) {
                System.out.println("(" + list.get(inIndex) + " < " + list.get(inIndex - 1) + "):" + (list.get(inIndex) < list.get(inIndex - 1)));
                if (list.get(inIndex) < list.get(inIndex - 1)) {
                    Integer tmp = list.get(inIndex);
                    list.set(inIndex, list.get(inIndex - 1));
                    list.set(inIndex - 1, tmp);
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
    public void sort(Integer[] list, boolean desc) {
        int length = list.length;
        for (int outIndex = 0; outIndex < length; outIndex++) {
            boolean swaped = false;
            for (int inIndex = 1; inIndex < length; inIndex++) {
                System.out.println("(" + list[inIndex] + " < " + list[inIndex - 1] + "):" + (list[inIndex] < list[inIndex - 1]));
                if (list[inIndex] < list[inIndex - 1]) {
                    Integer tmp = list[inIndex];
                    list[inIndex] = list[inIndex - 1];
                    list[inIndex - 1] = tmp;
                    swaped = true;
                }
            }
            if (!swaped) {
                break;
            }
        }
    }

}
