package com.coding.leetcode.list;

/** @Author shu wj @Date 2020/7/3 22:45 @Description */
public class Leetcode141 {
  public static class Solution_01 {
    public boolean hasCycle(ListNode head) {
      ListNode fast = head;
      ListNode slow = head;

      while (fast != null && fast.next != null) {
        fast = fast.next.next;
        slow = slow.next;

        if (fast == slow) {
          return true;
        }
      }

      return false;
    }
  }
}
