package org.easyarch.myutils.leetcode.p14;

/**
 * @author xingtianyu(code4j) Created on 2018-9-3.
 *  最长公共前缀
 */
public class Solution1 {

    public static void main(String[] args) {
        String[] strings = new String[]{"done","dont","donate"};
        System.out.println(longestCommonPrefix(strings));;
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0){
            return "";
        }
        int minLength = strs[0].length();
        for (int index = 0;index < strs.length;index++){
            String str = strs[index];
            minLength = (minLength > str.length())?str.length():minLength;
        }
        StringBuffer buffer = new StringBuffer();
        for (int index = 0; index < minLength; index++){
            char ch = strs[0].charAt(index);
            boolean end = true;
            for (int count = 0;count < strs.length;count++){
                char c = strs[count].charAt(index);
                if (c == ch){
                    end = false;
                }else{
                    end = true;
                    break;
                }
            }
            if (end){
                break;
            }
            buffer.append(ch);
        }
        return buffer.toString();
    }
}
