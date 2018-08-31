package org.easyarch.myutils.leetcode.p1;

import java.util.Arrays;

/**
 * @author xingtianyu(code4j) Created on 2018-8-31.
 *  两数之和--暴力流
 */
public class Solution1 {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum(nums,target);
        System.out.println(Arrays.toString(result));
    }

    public static int[] twoSum(int[] nums, int target) {
        int []result = new int[2];
        for (int index = 0;index<nums.length;index++){
            for (int inner = index;inner<nums.length;inner++){
                if (nums[index] + nums[inner] == target){
                    result[0] = index;
                    result[1] = inner;
                    return result;
                }
            }
        }
        return result;
    }
}
