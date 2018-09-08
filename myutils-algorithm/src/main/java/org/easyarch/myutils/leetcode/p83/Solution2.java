package org.easyarch.myutils.leetcode.p83;

/**
 * Created by xingtianyu on 2018/9/7.
 */
public class Solution2 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {

    }

    public static ListNode deleteDuplicates(ListNode head) {
        if(head == null||head.next == null){
            return head;
        }
        ListNode pre = head;
        ListNode next = head.next;
        int tmp = head.val;
        while (next != null){
            if (tmp != next.val){
                tmp = next.val;
                pre.next = next;
                pre = next;
            }
            next = next.next;
        }
        pre.next = null;
        return head;
    }
}
