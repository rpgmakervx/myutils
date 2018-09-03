package org.easyarch.myutils.leetcode.p7;

/**
 * @author xingtianyu(code4j) Created on 2018-9-3.
 */
public class Solution3 {


    public static void main(String[] args) {
        System.out.println(reverse(159));
    }
    public static int reverse(int x) {
        long res = 0;
        for(;x!=0;x/=10){
            res = res*10 + x%10;
        }
        return res>Integer.MAX_VALUE || res<Integer.MIN_VALUE ? 0 : (int) res;

    }
}
