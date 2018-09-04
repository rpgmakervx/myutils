package org.easyarch.myutils.leetcode.p53;

/**
 * Created by xingtianyu on 2018/9/5.
 * 分治法,分左右计算最大连续子数组，再求左边最大子数组+右边最大子数组得到的中间子数组，再求三者之和
 */
public class Solution2 {

    public static void main(String[] args) {
        int []nums = new int[]{-1,-2,-5,1,4,-1,10,-4,-8};
        System.out.println(maxSubArray(nums));
    }

    public static int maxSubArray(int[] nums) {
        return maxSubArray(nums,0,nums.length - 1);
    }

    public static int maxSubArray(int[] nums,int left,int right){
        if (left >= right){
            return nums[left];
        }
        int mid = (left + right) / 2;
        int lmax = maxSubArray(nums,left,mid -1);
        int rmax = maxSubArray(nums,mid + 1,right);
        int mmax = nums[mid];
        int sum = mmax;
        for (int index = mid - 1;index >= left;index--){
            sum += nums[index];
            mmax = Math.max(sum,mmax);
        }
        sum = mmax;
        for (int index = mid + 1;index <= right; index++ ){
            sum += nums[index];
            mmax = Math.max(sum,mmax);

        }
        return Math.max(mmax,Math.max(lmax,rmax));
    }

}
