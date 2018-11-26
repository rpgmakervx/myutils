package org.easyarch.myutils.leetcode.p78;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xingtianyu on 2018/11/25.
 */
public class Solution {

    public static void main(String[] args) {
//        String str = "中华人民共和国";
//        System.out.println(subSet(str));
//        int[] nums = new int[]{1,2,3};
//        System.out.println(subSet2(nums));
        System.out.println((1 << 3)&3);
    }

    /**
     * 000
     * 001
     * 010
     * 011
     * 100
     * 101
     * 110
     * 111
     * @param nums
     * @return
     */
    public static List<List<Integer>> subSet2(int[] nums){
        List<List<Integer>> subSet = new ArrayList<>();
        int markEnd = 1 << nums.length;
        for (int mark = 0;mark < markEnd;mark++){
            List<Integer> set = new ArrayList<>();
            for (int index = 0;index < nums.length;index++){
                if (((1 << index) & mark) != 0){
                    set.add(nums[index]);
                }
            }
            subSet.add(set);
        }
        return subSet;
    }

    public static List<List<Integer>> subSet(int[] nums){
        List<List<Integer>> subSet = new ArrayList<>();
        for (int index = 0;index < nums.length;index++){
            List<Integer> set = new ArrayList<>();
            for (int i = index;i < nums.length;i++){
                set.add(nums[i]);
            }
            subSet.add(set);
        }
        return subSet;
    }

    public static List<String> subSet(String string){
        List<String> subSet = new ArrayList<>();
        char[] chars = new char[string.length()];
        for (int index = 0;index<chars.length;index++){
            for (int i = index + 1;i <= chars.length;i++){
                subSet.add(string.substring(index,i));
            }
        }
        return subSet;
    }

}
