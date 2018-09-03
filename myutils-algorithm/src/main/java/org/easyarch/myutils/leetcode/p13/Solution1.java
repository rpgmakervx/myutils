package org.easyarch.myutils.leetcode.p13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xingtianyu on 2018/9/3.
 *   I， V， X， L，C，D 和 M
 */
public class Solution1 {
    private static final Map<Character,Integer> KEYWORDS = new HashMap<>();
    static {
        KEYWORDS.put('I',1);
        KEYWORDS.put('V',5);
        KEYWORDS.put('X',10);
        KEYWORDS.put('L',50);
        KEYWORDS.put('C',100);
        KEYWORDS.put('D',500);
        KEYWORDS.put('M',1000);
    }
    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
    }

    public static int romanToInt(String s) {
        if (KEYWORDS.containsKey(s)){
            return -1;
        }
        char[] chars = s.toCharArray();
        int front = 0;
        int result = 0;
        for (int index=0;index<chars.length;index++){
            Integer num = KEYWORDS.get(chars[chars.length - 1 - index]);
            //比上一个大做减法，否则做加法
            result = (num < front)?result - num:result + num;
            front = num;
        }
        return result;
    }
}
