package org.easyarch.myutils.leetcode.zijietiaodong.array;

import java.util.*;

/**
 * Created by xingtianyu on 2018/12/6.
 */
public class Solution6 {

    public static void main(String[] args) {
        int[] nums = new int[]{8,100,4,200,1,3,2};
        System.out.println(longestConsecutive(nums));;
    }

    public static int longestConsecutive(int[] nums){
        int max = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for (int i: nums){
            if (map.getOrDefault(i,0) == 0){
                int left = map.getOrDefault(i - 1,0);
                int right = map.getOrDefault(i + 1,0);
                map.put(i,right+left+1);
                System.out.println("i:"+i+",right:"+right+".left:"+left);
                if (left != 0){
                    System.out.println("i - left:"+(i - left));
                    map.put(i - left,right + left + 1);
                }
                if (right != 0){
                    System.out.println("i + right:"+(i + right));
                    map.put(i + right,right + left + 1);
                }
                System.out.println(map);
                System.out.println("==============");
                max = Math.max(max,right + left + 1);
            }
        }
        return max;
    }

    public static int longestConsecutive1(int[] nums){

        List<Integer> list = new ArrayList<>();
        int length = max(nums);
        System.out.println(length);
        list.add(-1);
        for (int index = 0;index <= length;index++){
            list.add(null);
        }
        for (int i:nums){
            list.set(i,1);
        }
        System.out.println(list);
        int count = 0;
        int max = 0;
        for (int index = 0;index < list.size();index++){
            if (list.get(index) == null){
                max = Math.max(max,count);
                count = 0;
                continue;
            }
            count++;
        }
        return max;
    }

    public static int max(int[] num){
        int max = 0;
        for (int i:num){
            max = Math.max(i,max);
        }
        return max;
    }
}
