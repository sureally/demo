package com.coding.leetcode.tree;

import java.util.HashSet;
import java.util.Set;

/** @Author shu wj @Date 2020/6/17 00:46 @Description */
public class Leetcode510 {

  public static class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
  };

  // 中序节点
  // right 节点
  // 找到第一个 parent 的右边节点
  private Node res = null;
  private Set<Node> cover = new HashSet<>();
  public Node inorderSuccessor(Node node) {
    if (null == node) return null;
    cover.add(null);
    cover.add(node);
    Node parent = node.parent;
    // 找到第一个往右的父节点
    while (parent != null) {
      if (!cover.contains(parent.right) || parent.right == null) {
        dfs(parent);
      }
      cover.add(parent);
      parent = parent.parent;
    }

    dfs(node.right);

    return res;
  }

  private void dfs(Node node) {
    if (null == node || cover.contains(node)) {
      return;
    }

    if (res == null) {
      res = node;
    }

    cover.add(node);
    res = res.val > node.val ? node : res;
    dfs(node.left);
    dfs(node.right);
  }
}
