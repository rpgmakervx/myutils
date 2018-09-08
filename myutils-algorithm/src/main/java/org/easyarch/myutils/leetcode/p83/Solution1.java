package org.easyarch.myutils.leetcode.p83;

import java.util.*;

/**
 * Created by xingtianyu on 2018/9/7.
 * 链表去重
 */
public class Solution1 {

  public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

    public static void main(String[] args) {

    }

    public static ListNode deleteDuplicates(ListNode head) {
        Map<Integer,Integer> map = new TreeMap<>();
        ListNode tmp = head;
        while (tmp != null){
            map.put(tmp.val,tmp.val);
            tmp = tmp.next;
        }
        ListNode result = new ListNode(0);
        ListNode node = result;
        for (Map.Entry<Integer,Integer> entry:map.entrySet()){
            node.next = new ListNode(entry.getKey());
            node = node.next;
        }
        return result.next;
    }
}
