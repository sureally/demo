package com.coding.leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @Author shu wj
 * @Date 2020/6/16 00:06
 * @Description
 */
public class Leetcode426 {
  public static class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
      val = _val;
      left = _left;
      right = _right;
    }
  };

  public Node treeToDoublyList(Node root) {
    if (null == root) return null;

    List<Node> midorder = new ArrayList<>();
    // 中序遍历
    Deque<Node> stack = new ArrayDeque<>();

    while(!stack.isEmpty() || root != null) {
      while (root != null) {
        stack.push(root);
        root = root.left;
      }

      root = stack.pop();
      midorder.add(root);
      root = root.right;
    }

    for (int i = 0; i <= midorder.size(); i++) {
      Node pre = i == 0 ? midorder.get(midorder.size() - 1) : midorder.get(i - 1);
      Node cur = i == midorder.size() ? midorder.get(0) : midorder.get(i);
      pre.right = cur;
      cur.left = pre;
    }

    return midorder.get(0);
  }

  public static void main(String[] args) {
    Node root = new Node(4);
    root.left = new Node(2);
    root.right = new Node(5);
    root.left.left = new Node(1);
    root.left.right = new Node(3);

    Node list = new Leetcode426().treeToDoublyList(root);
    for (int i = 0; i < 5; i++) {
      System.out.print(list.val + ", ");
      list = list.right;
    }
  }
}
