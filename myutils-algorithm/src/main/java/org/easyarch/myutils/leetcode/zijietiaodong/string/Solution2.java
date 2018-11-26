package org.easyarch.myutils.leetcode.zijietiaodong.string;

/**
 * Created by xingtianyu on 2018/11/26.
 * 最长公共前缀
 * ["flower","flow","floght"] -> "fl"
 */
public class Solution2 {

    public static void main(String[] args) {
        String[] strings = new String[]{"flower","flow","floght"};
//        String[] strings = new String[]{"aca","cba"};
        System.out.println(longestCommonPrefix(strings));
    }

    /**
     * 先获取最短的字符串.以该字符串长度遍历，
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null||strs.length == 0){
            return "";
        }
        int minLength = Integer.MAX_VALUE;
        for (int index = 0;index < strs.length;index++){
            minLength = Math.min(minLength,strs[index].length());
        }
        StringBuffer buffer = new StringBuffer();
        for (int index = 0;index < minLength;index++){
            char start = strs[0].charAt(index);
            boolean add = true;
            for (int j = 0;j < strs.length;j++){
                //获取第j个字符串的第index位字符
                if (strs[j].charAt(index) != start){
                    add = false;
                    break;
                }
            }
            if (!add){
                break;
            }
            buffer.append(start);
        }
        return buffer.toString();
    }
}
