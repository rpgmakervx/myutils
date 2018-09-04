package org.easyarch.myutils.leetcode.p21;

import java.util.List;

/**
 * Created by xingtianyu on 2018/9/4.
 * 合并链表（不重复）
 */
public class Solution1 {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;

        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        node4.next = node5;
        node5.next = node6;

        ListNode result = mergeTwoLists(node1,node4);
        StringBuffer buffer = new StringBuffer();
        while (result != null){
            buffer.append(result.val).append("->");
            result = result.next;
        }
        System.out.println(buffer.toString());
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(0);
        ListNode n1 = l1;
        ListNode n2 = l2;
        ListNode cur = node;
        while (n1 != null|| n2 != null){
            Integer x = n1 != null?n1.val:null;
            Integer y = n2 != null?n2.val:null;
            if (x == null || y == null){
                if (x != null){
                    cur.next = new ListNode(x);
                    cur = cur.next;
                    n1 = n1.next;
                }
                if (y != null){
                    cur.next = new ListNode(y);
                    cur = cur.next;
                    n2 = n2.next;
                }
            }else{
                if (x > y){
                    ListNode next = new ListNode(y);
                    cur.next = next;
                    cur = cur.next;
                    n2 = n2.next;
                }else if (x < y){
                    ListNode next = new ListNode(x);
                    cur.next = next;
                    cur = cur.next;
                    n1 = n1.next;
                }else{
                    cur.next = new ListNode(x);
                    cur = cur.next;
                    n1 = n1.next;
                    n2 = n2.next;
                }

            }
        }
        return node.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
