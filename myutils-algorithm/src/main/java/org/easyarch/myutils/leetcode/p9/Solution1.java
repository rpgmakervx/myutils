package org.easyarch.myutils.leetcode.p9;

/**
 * Created by xingtianyu on 2018/9/2.
 * 回文数
 */
public class Solution1 {

    public static void main(String[] args) {
        System.out.println(isPalindrome(1001));
    }

    public static boolean isPalindrome(int x) {
        char[] chars = String.valueOf(x).toCharArray();
        for (int index = 0;index<chars.length / 2;index++){
            if (chars[index] != chars[chars.length - index - 1]){
                return false;
            }
        }
        return true;
    }
}
