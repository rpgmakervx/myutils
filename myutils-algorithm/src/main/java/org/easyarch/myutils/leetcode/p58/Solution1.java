package org.easyarch.myutils.leetcode.p58;

/**
 * Created by xingtianyu on 2018/9/5.
 */
public class Solution1 {

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord(" "));
    }

    public static int lengthOfLastWord(String s) {
        if (s == null){
            return 0;
        }
        if (s.trim().length() == 0){
            return 0;
        }
        String[] substrings = s.split(" ");
        String end = substrings[substrings.length - 1];
        if (end.trim().isEmpty()){
            return 0;
        }
        return end.length();
    }
}
