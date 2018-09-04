package org.easyarch.myutils.leetcode.p53;

/**
 * Created by xingtianyu on 2018/9/5.
 * 动态规划版。动态规划的思路就是每次求解问题n，使用n-1的结果即可，在问题开始时就算出1 到n-1的结果
 */
public class Solution3 {

    public static void main(String[] args) {
        int []nums = new int[]{-3,9,-2,3,1,4};
        System.out.println(maxSubArray(nums));
    }

    public static int maxSubArray(int[] nums) {
        if (nums == null){
            return 0;
        }
        if (nums.length == 0){
            return nums[0];
        }
        int sum = nums[0];
        int max = sum;
        for (int index = 1;index<nums.length;index++){
            sum = (sum + nums[index]) > nums[index]?(sum + nums[index]):nums[index];
            max = sum > max?sum:max;
        }
        return max;
    }
}
