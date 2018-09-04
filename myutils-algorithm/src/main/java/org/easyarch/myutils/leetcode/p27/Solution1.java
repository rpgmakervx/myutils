package org.easyarch.myutils.leetcode.p27;

/**
 * Created by xingtianyu on 2018/9/4.
 */
public class Solution1 {

    public static void main(String[] args) {
        int []nums = new int[]{0,1,1,2,2,5,2,3,3,2};
        System.out.println("size:"+removeElement(nums,2));
        for (int n:nums){
            System.out.println(n);
        }
    }
    public static int removeElement(int[] nums, int val) {
        int i = 0;
        for (int index = 0;index<nums.length;index++){
            if (nums[index] != val){
                nums[i] = nums[index];
                i++;
            }
        }
        return i + 1;
    }
}
