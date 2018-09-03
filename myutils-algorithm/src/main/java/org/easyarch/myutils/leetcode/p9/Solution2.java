package org.easyarch.myutils.leetcode.p9;

/**
 * Created by xingtianyu on 2018/9/3.
 */
public class Solution2 {

    public static void main(String[] args) {
        System.out.println(isPalindrome(1000000001));
    }

    public static boolean isPalindrome(int x) {
        if (x < 0){
            return false;
        }
        int num = x;
        int result = 0;
        int remainder = 0;
        while (num != 0){
            remainder = num % 10;
            num = num / 10;
            if (num > Integer.MAX_VALUE / 10 || num == Integer.MAX_VALUE/10 && remainder == 7){
                return false;
            }
            result = result * 10 + remainder;
        }
        return result == x;
    }
}
