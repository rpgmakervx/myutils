package org.easyarch.myutils.leetcode.p1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xingtianyu on 2018/9/2.
 */
public class Solution3 {

    public static void main(String[] args) {
        int[] nums = new int[]{12, 37, 16, 47};
        int target = 28;
        int[] result = twoSum(nums,target);
        System.out.println(Arrays.toString(result));
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> hashmap = new HashMap<>();
        for(int index = 0;index<nums.length;index++){
            hashmap.put(nums[index],index);
        }
        for(int index = 0;index<nums.length;index++){
            Integer i = hashmap.get(target - nums[index]);
            if(i!= null && i != index){
                int[] result = new int[2];
                result[0] = index;
                result[1] = i;
                return result;
            }
        }
        return new int[2];
    }
}
