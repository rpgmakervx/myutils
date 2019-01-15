package org.easyarch.myutils.leetcode.zijietiaodong.array;

/**
 * Created by xingtianyu on 2018/12/5.
 */
public class Solution5 {

    public static void main(String[] args) {
        int[] num = new int[]{9,7,3,1,0,2,6,4,8};
        sort(num,0,num.length - 1);
        for (int i:num){
            System.out.println(i);
        }
    }

    public static void sort(int[] num,int low,int high){
        int l = low;
        int h = high;
        if (l >= h){
            return;
        }
        int tmp = num[l];
        while (l < h){
            while (h > l && num[h] >= tmp){
                h--;
            }
            num[l] = num[h];
            while (h > l && tmp >= num[l]){
                l++;
            }

            num[h] = num[l];
        }
        num[l] = tmp;
        sort(num,low,h - 1);
        sort(num,l + 1,high);
    }
}
