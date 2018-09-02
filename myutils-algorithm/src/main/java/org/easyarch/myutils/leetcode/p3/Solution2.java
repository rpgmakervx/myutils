package org.easyarch.myutils.leetcode.p3;


/**
 * Created by xingtianyu on 2018/9/2.
 */
public class Solution2 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;

        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(6);
        ListNode node6 = new ListNode(4);
        node4.next = node5;
        node5.next = node6;

        ListNode n = addTwoNumbers(node1,node4);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode n1 = l1;
        ListNode n2 = l2;
        int carry = 0;

        ListNode result = new ListNode(0);
        ListNode current = result;
        while (n1 != null || n2 != null){
            int a = (n1 == null)?0:n1.val;
            int b = (n2 == null)?0:n2.val;
            int sum = a + b + carry;
            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
            if (n1 != null){
                n1 = n1.next;
            }
            if (n2 != null){
                n2 = n2.next;
            }
        }
        if (carry > 0){
            current.next = new ListNode(carry);
        }
        return result.next;
    }

}
