package org.easyarch.myutils.leetcode.p69;

/**
 * Created by xingtianyu on 2018/9/6.
 * 平方根，动态规划
 */
public class Solution2 {

    public static void main(String[] args) {
        System.out.println(mySqrt(100000));
    }

    public static int mySqrt(int x) {
        if (x < 2){
            return x;
        }
        int carray = 1;
        while (carray <= x / carray){
            carray++;
        }
        return carray - 1;
    }
}
