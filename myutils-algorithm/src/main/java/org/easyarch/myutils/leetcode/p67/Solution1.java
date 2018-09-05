package org.easyarch.myutils.leetcode.p67;

import java.util.Stack;

/**
 * Created by xingtianyu on 2018/9/6.
 * 二进制求和
 */
public class Solution1 {

    public static void main(String[] args) {
        String x = "1001";
        String y = "110";
        System.out.println(addBinary(x,y));
    }

    public static String addBinary(String a, String b) {
        String longer = a;
        String shorter = b;
        StringBuffer buffer = new StringBuffer();
        if (a.length() < b.length()){
            longer = b;
            shorter = a;
        }
        int i = shorter.length() - 1;
        int carray = 0;
        String result = "";
        for (int index = longer.length() - 1;index >= 0;index--){
            Integer x = longer.charAt(index) - '0';
            Integer y = i < 0?0:shorter.charAt(i) - '0';
            i--;
            result = (x + y + carray) % 2 + result;
            carray = (x + y + carray) / 2;
        }
        if (carray > 0){
            result = 1 + result;
        }
        return result;
    }
}
