package org.easyarch.myutils.leetcode.p70;

/**
 * Created by xingtianyu on 2018/9/6.
 * 动态规划，爬第n个台阶，可能是走1步上来或者2步上来，则爬第n个的方法数等于第n-1次数加第n-2
 */
public class Solution1 {
    public static void main(String[] args) {
        System.out.println(climbStairs(5));
    }

    public static int climbStairs(int n) {
        if(n < 2){
            return n;
        }
        int[] steps = new int[n];
        steps[0] = 1;
        steps[1] = 2;
        for (int index = 2;index < n;index++){
            steps[index] = steps[index - 1] + steps[index - 2];
        }
        return steps[n - 1];
    }
}
