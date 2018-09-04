package org.easyarch.myutils.leetcode.p66;

import java.util.Stack;

/**
 * Created by xingtianyu on 2018/9/5.
 */
public class Solution1 {

    public static void main(String[] args) {
        int []num = new int[]{3,2,9};

        int []plus = plusOne(num);
        for (int i:plus){
            System.out.println(i);
        }
//        long i = 9876543210L + 1;
//        System.out.println(i);
    }

    public static int[] plusOne(int[] digits) {
        Stack<Integer> stack = new Stack<>();
        int carray = (digits[digits.length - 1] + 1) / 10;
        stack.push((digits[digits.length - 1] + 1) % 10);
        for (int index = digits.length - 2;index>=0;index--){
            stack.push((digits[index] + carray) % 10);
            carray = (digits[index] + carray) / 10;
        }
        if (carray != 0){
            stack.push(carray);
        }
        int []result = new int[stack.size()];
        int index = 0;
        while (!stack.isEmpty()){
            result[index] = stack.pop();
            index++;
        }
        return result;
    }

}
