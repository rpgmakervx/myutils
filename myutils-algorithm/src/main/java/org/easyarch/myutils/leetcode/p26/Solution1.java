package org.easyarch.myutils.leetcode.p26;

/**
 * Created by xingtianyu on 2018/9/4.
 * 原地去重数据
 */
public class Solution1 {

    public static void main(String[] args) {
        int []nums = new int[]{0,0,1,2,1,2,2,3,3,4};
        System.out.println("size:"+removeDuplicates(nums));
        for (int n:nums){
            System.out.println(n);
        }
    }

    public static int removeDuplicates(int[] nums) {
        int i = 0;
        for (int index = 1;index<nums.length;index++){
            if (nums[i] != nums[index]){
                i++;
                nums[i] = nums[index];
            }
        }
        return i+1;
    }
}
