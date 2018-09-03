package org.easyarch.myutils.leetcode.p9;

/**
 * Created by xingtianyu on 2018/9/3.
 * 回文数字
 */
public class Solution2 {

    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
    }

    /**
     * 翻转整数，判断相等
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        if (x < 0){
            return false;
        }
        int old = x;
        int reverse = 0;
        for (;x != 0;x /= 10){
            reverse = reverse * 10 + x % 10;
        }
        if (reverse > Integer.MAX_VALUE || reverse < Integer.MIN_VALUE){
            return false;
        }
        return reverse == old;
    }
}
