package org.easyarch.myutils.leetcode.p28;

/**
 * @author xingtianyu(code4j) Created on 2018-9-4.
 */
public class Solution1 {
    public static void main(String[] args) {
        System.out.println(strStr("mississippi","issippi"));
//        System.out.println(strStr("helloworld","world"));
    }

    public static int strStr(String haystack, String needle) {
        if (haystack == null||needle == null){
            return -1;
        }
        if (haystack.equals(needle)||needle.length() == 0){
            return 0;
        }
        if (haystack.length() < needle.length()){
            return -1;
        }
        for (int index = 0;index<haystack.length();index++){
             int position = index;
             boolean complete = true;
             for (int i = 0;i < needle.length() ;i++){
                 if (position >= haystack.length()){
                     return -1;
                 }
                 char n = needle.charAt(i);
                 char h = haystack.charAt(position++);
                 if (n != h){
                     complete = false;
                     break;
                 }
             }
             if (complete){
                 return position - needle.length();
             }
        }
        return -1;
    }
}
