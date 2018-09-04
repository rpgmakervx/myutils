package org.easyarch.myutils.leetcode.p53;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xingtianyu on 2018/9/5.
 */
public class Solution1 {

    public static void main(String[] args) {
        int []nums = new int[]{-1,-2,-5,1,4,-1,10,-4,-8};
        System.out.println(maxSubArray(nums));
    }

    public static int maxSubArray(int[] nums) {
        List<Integer> array = new ArrayList<>();
        for (int index = 0; index < nums.length;index++){
            int sum = nums[index];
            array.add(sum);
            for (int i = index + 1;i<nums.length;i++){
                sum += nums[i];
                array.add(sum);
            }
        }
        return Collections.max(array);
    }

}
