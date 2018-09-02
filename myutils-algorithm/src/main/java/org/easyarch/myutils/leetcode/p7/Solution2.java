package org.easyarch.myutils.leetcode.p7;

/**
 * Created by xingtianyu on 2018/9/2.
 */
public class Solution2 {

    public static void main(String[] args) {
        System.out.println(reverse(Integer.MAX_VALUE));
    }

    public static int reverse(int x) {
        int result = 0;
        while (x != 0){
            int remainder = x % 10;
            x /= 10;
            if (result > Integer.MAX_VALUE/10 || result == Integer.MAX_VALUE/10 && remainder == 7){
                return 0;
            }
            if (result < Integer.MIN_VALUE/10 || result == Integer.MIN_VALUE/10 && remainder == 8){
                return 0;
            }
            result = result * 10 + remainder;
        }
        return result;
    }
}
