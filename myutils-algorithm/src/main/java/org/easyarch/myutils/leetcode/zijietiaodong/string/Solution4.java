package org.easyarch.myutils.leetcode.zijietiaodong.string;

import java.util.Arrays;

/**
 * Created by xingtianyu on 2018/11/28.
 * 字符串相乘
 * "90" "80" -》 "7200"
 */
public class Solution4 {

    public static void main(String[] args) {
        //12 186 984
        System.out.println(multiply("0","0"));;
    }

    public static String multiply(String num1, String num2) {
        int[] result = new int[num1.length() + num2.length()];
        for (int index1 = num1.length() - 1;index1 >= 0;index1--){
            int carray = 0;
            for (int index2 = num2.length() - 1;index2 >= 0;index2--){
                int n1 = num1.charAt(index1) - '0';
                int n2 = num2.charAt(index2) - '0';
                int multiplyNum = n1 * n2;
                int stay = multiplyNum % 10;
                int sum = result[index1 + index2 + 1] + stay;
                result[index1 + index2 + 1] = sum % 10;
                carray = multiplyNum / 10 + sum / 10;
                result[index1 + index2] += carray;
            }
        }
        System.out.println(Arrays.toString(result));
        StringBuffer buffer = new StringBuffer();
        boolean meetZero = true;
        for (int index = 0;index < num1.length() + num2.length();index++){
            if (result[index] == 0 && meetZero){
                if (index == num1.length() + num2.length() - 1){
                    buffer.append(0);
                }
                continue;
            }
            meetZero = false;
            buffer.append(result[index]);
        }
        return buffer.toString();
    }
}
