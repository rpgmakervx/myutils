package org.easyarch.myutils.leetcode.p35;

/**
 * Created by xingtianyu on 2018/9/4.
 * 搜索插入位置
 */
public class Solution1 {
    public static void main(String[] args) {
        int []nums = new int[]{};
        System.out.println(searchInsert(nums,0));
    }

    public static int searchInsert(int[] nums, int target) {
        int right = nums.length - 1;
        int left = 0;
        int position = (right + left) / 2;
        while (right >= left){
            int mid = (right + left) / 2;
            System.out.println("right("+right+")+left("+left+")/2 = "+"mid("+mid+")");
            if(nums[mid] == target){
                return mid;
            }else if (nums[mid] > target){
                right = mid - 1;
                position = mid ;
            }else{
                left = mid + 1;
                position = mid + 1;
            }
        }
        return position;
    }
}
