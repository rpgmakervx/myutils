package org.easyarch.myutils.leetcode.p7;

/**
 * Created by xingtianyu on 2018/9/2.
 */
public class Solution1 {

    public static void main(String[] args) {
        System.out.println(reverse(-2147483647));
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(12 % 10);
    }

    public static int reverse(int x) {
        int carry = x;
        int result = 0;
        int digit = 0;
        if (carry == 0){
            return x;
        }
        while (carry != 0){
            carry = carry / 10;
            digit++;
        }
        int []nums = new int[digit];
        digit = 0;
        carry = x;
        int remainder = 0;
        while (true){
            remainder = carry % 10;
            carry = carry / 10;
            nums[digit] = remainder;
            digit++;
            if (carry == 0){
                break;
            }
        }
        for (int index = 0;index<nums.length;index++){
            result += nums[index] * Math.pow(10,nums.length - index - 1);
        }
        if (result >= Integer.MAX_VALUE || result <= Integer.MIN_VALUE){
            return 0;
        }
        return result;
    }
}
