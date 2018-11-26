package org.easyarch.myutils.leetcode.zijietiaodong.string;

import java.util.*;

/**
 * Created by xingtianyu on 2018/11/26.
 * 字符串的排列
 * s1 = "ab", s2 = "eidbaooo" -> true
 * s1 = "ab", s2 = "eidboaoo" -> false
 */
public class Solution3 {

    public static void main(String[] args) {
        System.out.println('z' - 'A');
//        System.out.println(checkInclusion2("abc","eidcbaooo"));
//        System.out.println(checkInclusion2("abc","eidcboaoo"));
//        System.out.println(checkInclusion2("adc","dcda"));
//        System.out.println(checkInclusion2("food","money"));
        System.out.println(checkInclusion2("ccc","cbac"));
    }

    public static boolean checkInclusion2(String s1, String s2) {
        if (s1.length() > s2.length()){
            return false;
        }
        int[] queue = new int[58];
        for (int index = 0;index < s1.length();index++){
            queue[s2.charAt(index) - 'A']--;
            queue[s1.charAt(index) - 'A']++;
        }
        if (allZero(queue)){
            return true;
        }

        for (int index = s1.length();index < s2.length();index++){
            queue[s2.charAt(index) - 'A']--;
            queue[s2.charAt(index - s1.length()) - 'A']++;
            if (allZero(queue)){
                return true;
            }
        }
        return false;
    }

    private static boolean allZero(int[] queue){
        for (int index = 0 ;index < queue.length;index++){
            if (queue[index] != 0){
                return false;
            }
        }
        return true;
    }

    public static boolean checkInclusion(String s1, String s2) {
        int start = 0;
        int end = 0;
        if (s1.length() > s2.length()){
            return false;
        }
        Map<Character,Integer> map = new HashMap<>();
        for (int index = 0;index < s1.length();index++){
            addCount(map,s1.charAt(index));
        }
        Map<Character,Integer> record = new HashMap<>();
        int threshold = 0;
        while (start < s2.length() && end < s2.length()){
            if (map.containsKey(s2.charAt(end))){
                addCount(record,s2.charAt(end));
                Integer recordCount = record.get(s2.charAt(end));
                Integer count = map.get(s2.charAt(end));
                threshold++;
                if (count < recordCount){
                    //s-e中字符出现次数超过s1则重置
                    start++;
                    end = start;
                    threshold = 0;
                    record.clear();
                    continue;
                }else if (threshold >= s1.length()){
                    return true;
                }
                end++;
            }else{
                end++;
                start++;
                threshold = 0;
                record.clear();
            }
        }
        return false;
    }

    public static boolean checkCommonPrefix(String s1, String s2){
        Map<Character,Integer> count1 = new HashMap<>();
        Map<Character,Integer> count2 = new HashMap<>();
        for (int index = 0;index < s1.length();index++){
//            addCount(count1,s1,index);
//            addCount(count2,s2,index);
        }
        boolean flag = true;
        for (Map.Entry<Character,Integer> entry:count1.entrySet()){
            if (entry.getValue() != count2.get(entry.getKey())){
                flag = false;
            }
        }
        return flag;
    }

    public static void addCount(Map<Character,Integer> countMap,Character character){
        Integer count = countMap.get(character);
        if (count != null){
            countMap.put(character,count + 1);
        }else{
            countMap.put(character,1);
        }
    }
}
