package org.easyarch.myutils.leetcode.p20;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by xingtianyu on 2018/9/4.
 */
public class Solution1 {

    private static final Character LEFT1= '(';
    private static final Character LEFT2= '[';
    private static final Character LEFT3= '{';

    private static final Character RIGHT1= ')';
    private static final Character RIGHT2= ']';
    private static final Character RIGHT3= '}';
    private static final Map<Character,Character> KEYWORDS = new HashMap<>();

    static {
        KEYWORDS.put(RIGHT1,LEFT1);
        KEYWORDS.put(RIGHT2,LEFT2);
        KEYWORDS.put(RIGHT3,LEFT3);
    }

    public static void main(String[] args) {
        System.out.println(isValid("({[{}{}(){}]})"));
    }
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int index = 0;index<s.length();index++){
            char signal = s.charAt(index);
            if (signal == LEFT1||signal == LEFT2||signal == LEFT3){
                stack.push(signal);
            }else{
                Character ch = KEYWORDS.get(signal);
                if (stack.size() <= 0){
                    return false;
                }
                Character st = stack.pop();
                if (!ch.equals(st)){
                    return false;
                }
            }
        }
        return stack.size() == 0;
    }
}
