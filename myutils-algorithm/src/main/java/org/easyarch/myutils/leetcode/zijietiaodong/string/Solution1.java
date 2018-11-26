package org.easyarch.myutils.leetcode.zijietiaodong.string;

import java.util.HashSet;

/**
 * Created by xingtianyu on 2018/11/26.
 * 无重复字符的最长子串
 * abcdacs -> 4
 * bbbbb -> 4
 * pwwkew -> 3
 */
public class Solution1 {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcdacs"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    /**
     * 窗口法，start到end组成一个窗口，end滑动获取当前最大长度子串，start开始下一轮查找
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int length = s.length();
        int start = 0;
        int end = 0;
        //记录已存在的字符
        HashSet<Character> set = new HashSet<>();
        while (start < length && end < length){
            if (!set.contains(s.charAt(end))){
                set.add(s.charAt(end));
                end++;
                maxLength = Math.max(maxLength,end - start);
            }else{
                //出现重复字符，开始下一轮查找.保证连续所以start要一位一位挪动
                set.remove(s.charAt(start));
                start++;
            }
        }
        return maxLength;
    }

}
