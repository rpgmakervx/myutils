package org.easyarch.myutils.leetcode.zijietiaodong.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xingtianyu on 2018/11/26.
 * 字符串的排列
 * s1 = "ab", s2 = "eidbaooo" -> true
 * s1 = "ab", s2 = "eidboaoo" -> false
 */
public class Solution3 {

    public static void main(String[] args) {
        System.out.println(checkInclusion("abc","aewcbyi"));
        System.out.println(checkInclusion("abc","aeac2bdjii"));
    }

    public static boolean checkInclusion(String s1, String s2) {
        if (checkCommonPrefix(s1,s2)){
            return true;
        }
        Map<Character,Integer> wordCount = new HashMap<>();
        for (int index = 0;index < s1.length();index++){
            Integer count = wordCount.get(s1.charAt(index));
            if (count == null){
                wordCount.put(s1.charAt(index),1);
            }else{
                count++;
                wordCount.put(s1.charAt(index),count);
            }
        }
        Map<Character,Integer> record = new HashMap<>();
        for (int index = 0;index < s2.length();index++){
            for (int j = index;j < s1.length();j++){
                Integer count = record.get(s1.charAt(j));
                if (count == null){
                    record.put(s1.charAt(j),1);
                }else{
                    count++;
                    record.put(s1.charAt(j),count);
                }
            }

            boolean flag = true;
            for (Map.Entry<Character,Integer> entry:wordCount.entrySet()){
                if (entry.getValue() != record.get(entry.getKey())){
                    flag = false;
                }
            }
            if (flag){
                return true;
            }
        }
        return false;
    }

    public static boolean checkCommonPrefix(String s1, String s2){
        Map<Character,Integer> count1 = new HashMap<>();
        Map<Character,Integer> count2 = new HashMap<>();
        for (int index = 0;index < s1.length();index++){
            addCount(count1,s1,index);
            addCount(count2,s2,index);
        }
        boolean flag = true;
        for (Map.Entry<Character,Integer> entry:count1.entrySet()){
            if (entry.getValue() != count2.get(entry.getKey())){
                flag = false;
            }
        }
        return flag;
    }

    public static void addCount(Map<Character,Integer> countMap,String str,int index){
        if (countMap.containsKey(str.charAt(index))){
            Integer count = countMap.get(str.charAt(index));
            count++;
            countMap.put(str.charAt(index),count);
        }else{
            countMap.put(str.charAt(index),1);
        }
    }
}
