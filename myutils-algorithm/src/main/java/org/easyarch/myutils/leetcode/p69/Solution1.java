package org.easyarch.myutils.leetcode.p69;

/**
 * Created by xingtianyu on 2018/9/6.
 * 平方根，分治法
 */
public class Solution1 {
    public static void main(String[] args) {
        System.out.println(mySqrt(2147395599));
    }

    public static int mySqrt(int x) {
        long low = 0;
        long high = x / 2 + 1;
        long mid = 1;
        while (low <= high){
            mid = (low + high) / 2;
            long sqrt = mid * mid;
            System.out.println("mid("+mid+")*mid:("+mid+"):"+sqrt);
            if (sqrt == x){
                return (int)mid;
            }else if (sqrt > x){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return (int)high;
    }
}
