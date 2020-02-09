package com.coding.leetcode.list;

/**
 * @Author shu wj
 * @Date 2019/12/4 23:36
 * @Description
 */
public class LeetCode138 {

  // Definition for a Node.
  class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {
    }

    public Node(int _val, Node _next, Node _random) {
      val = _val;
      next = _next;
      random = _random;
    }
  }


  public Node copyRandomList(Node head) {
    // copy first, then move new random pointer to init random pointer next pos(if not null), finally split into two list
    if (null == head) {
      return head;
    }
    // A -> B -> null
    // copy
    Node tmp = head;
    while (tmp != null) {
      tmp.next = new Node(tmp.val, tmp.next, tmp.random);
      tmp = tmp.next.next;
    }

    // new random pointer move to right position
    tmp = head;
    while (tmp != null) {
      if (null != tmp.random) {
        tmp.next.random = tmp.random.next;
      }
      tmp = tmp.next.next;
    }
    // A -> A -> B -> B
    // split
    Node newHead = new Node();
    Node oldHead = head;
    tmp = newHead;
    while (oldHead != null) {
      tmp.next = oldHead.next;
      oldHead.next = oldHead.next.next;
      oldHead = oldHead.next;
      tmp = tmp.next;
    }

    return newHead.next;
  }

}
