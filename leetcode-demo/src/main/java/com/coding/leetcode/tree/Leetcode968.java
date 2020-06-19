package com.coding.leetcode.tree;

import java.util.HashSet;
import java.util.Set;

/** @Author shu wj @Date 2020/6/15 23:24 @Description */
public class Leetcode968 {

  private Set<TreeNode> covered = new HashSet<>();
  private int ans;
  // 贪心递归，关键是从下到上，从叶子节点向上判断。
  // 什么情况需要加摄像头：1）自己被覆盖，但是任意一个叶子节点没有被覆盖；2）自己没有被覆盖 且 父 为null
  public int minCameraCover(TreeNode root) {
    covered.add(null);

    helper(root, null);

    return ans;
  }

  private void helper(TreeNode node, TreeNode parent) {
    if (null == node) return;

    helper(node.left, node);
    helper(node.right, node);

    if ((!covered.contains(node.left) || !covered.contains(node.right))
        || (parent == null && !covered.contains(node))) {
      covered.add(node);
      covered.add(node.left);
      covered.add(node.right);
      covered.add(parent);
      ans++;
    }
  }

  public static void main(String[] args) {}
}
