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
        KEYWORDS.put('V',2);
        KEYWORDS.put('X',3);
        KEYWORDS.put('L',4);
        KEYWORDS.put('C',5);
        KEYWORDS.put('D',6);
        KEYWORDS.put('M',7);
    }
    public static void main(String[] args) {

    }

    public static int romanToInt(String s) {
        if (KEYWORDS.containsKey(s)){
            return -1;
        }
        char[] chars = s.toCharArray();
        for (int index=0;index<chars.length;index++){
            KEYWORDS.get(chars[chars.length - 1]);
        }
        return 1;
    }
}
