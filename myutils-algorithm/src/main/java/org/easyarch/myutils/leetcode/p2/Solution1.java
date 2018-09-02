package org.easyarch.myutils.leetcode.p2;

/**
 * @author xingtianyu(code4j) Created on 2018-8-31.
 * 两数相加
 */
public class Solution1 {

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
        int a = 0;
        int b = 0;
        int level = 1;
        ListNode tmp = l1;
        while (tmp != null){
            a += level * tmp.val;
            tmp = tmp.next;
            level *= 10;
        }
        tmp = l2;
        level = 1;
        while (tmp != null){
            b += level * tmp.val;
            tmp = tmp.next;
            level *= 10;
        }
        int c = a + b;
        String data = String.valueOf(c);
        char[] chars = data.toCharArray();
        ListNode node = new ListNode(new Integer(chars[chars.length - 1]+""));
        for (int index = chars.length - 2;index>=0;index--){
            ListNode next = new ListNode(new Integer(chars[index]+""));
            tmp = node;
            while (true){
                if (tmp.next == null){
                    tmp.next = next;
                    break;
                }
                tmp = tmp.next;
            }
        }
        System.out.println(node.val + ","+node.next.val+","+node.next.next.val);
        return node;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
