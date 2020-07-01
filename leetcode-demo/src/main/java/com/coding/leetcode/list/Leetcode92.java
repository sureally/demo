package com.coding.leetcode.list;

/** @Author shu wj @Date 2020/6/29 22:51 @Description */
public class Leetcode92 {
  // 迭代实现
  public static class Solution_01 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
      // Empty list
      if (null == head) {
        return null;
      }

      // Move the two pointers until they reach the proper starting point
      // in the list.
      ListNode cur = head, prev = null;
      while (m > 1) {
        prev = cur;
        cur = cur.next;
        m--;
        n--;
      }

      // The two pointers that will fix the final connections.
      ListNode con = prev, tail = cur;
      // Iteratively reverse the nodes until n becomes 0.
      ListNode third = null;
      while (n > 0) {
        third = cur.next;
        cur.next = prev;
        prev = cur;
        cur = third;
        n--;
      }

      // Adjust the final connections as explained in the algorithm
      if (con != null) {
        con.next = prev;
      } else {
        head = prev;
      }

      tail.next = cur;
      return head;
    }
  }

  // 递归实现，相对而言递归实现可以作为练习使用，但是效率没有迭代来得高
  public static class Solution_02 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
      if (m > 1) {
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
      }
      return reverseN(head, n);
    }

    private ListNode tail = null;
    private ListNode reverseN(ListNode head, int n) {
      if (n == 1) {
        tail = head.next;
        return head;
      }
      ListNode last = reverseN(head.next, n - 1);
      head.next.next = head;
      head.next = tail;
      return last;
    }
  }
}

class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
  }
}
