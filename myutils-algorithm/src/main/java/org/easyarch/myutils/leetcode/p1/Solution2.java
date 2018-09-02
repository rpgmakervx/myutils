package org.easyarch.myutils.leetcode.p1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xingtianyu(code4j) Created on 2018-8-31.
 * 两数之和--哈希改进
 */
public class Solution2 {

    public static void main(String[] args) {
        int[] nums = new int[]{12, 37, 16, 47};
        int target = 28;
        int[] result = twoSum(nums,target);
        System.out.println(Arrays.toString(result));
    }

    public static int[] twoSum(int[] nums, int target) {
        int []result = new int[2];
        Map<Integer,Integer> bucket = new HashMap<>();
        for (int index = 0;index<nums.length;index++){
            if (bucket.containsKey(target - nums[index])){
                result[0] = bucket.get(target - nums[index]);
                result[1] = index;
                return result;
            }
            //判断后插入表
            bucket.put(nums[index],index);
        }
        return result;
    }
}
