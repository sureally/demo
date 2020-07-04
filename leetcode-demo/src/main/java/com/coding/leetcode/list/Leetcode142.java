package com.coding.leetcode.list;

/** @Author shu wj @Date 2020/7/3 22:45 @Description */
public class Leetcode142 {
  public static class Solution_01 {
    ListNode detectCycle(ListNode head) {
      ListNode fast, slow;
      fast = slow = head;
      while (fast != null && fast.next != null) {
        fast = fast.next.next;
        slow = slow.next;
        if (fast == slow) break;
      }
      if (fast == null || fast.next == null) return null;
      // 上面的代码类似 hasCycle 函数
      slow = head;
      while (slow != fast) {
        fast = fast.next;
        slow = slow.next;
      }
      return slow;
    }
  }
}
