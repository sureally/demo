package com.coding.leetcode.tree;

/**
 * @Author shu wj
 * @Date 2020/6/20 14:36
 * @Description
 */
public class Leetcode572 {
  public boolean isSubtree(TreeNode s, TreeNode t) {
    if (s == null && t == null) {
      return true;
    }
    if (s == null || t == null) {
      return false;
    }

    if (s.val == t.val && check(s, t)) {
      return true;
    }

    return isSubtree(s.left, t) || isSubtree(s.right, t);
  }


  private boolean check(TreeNode node, TreeNode t) {
    if (node == null && t == null) {
      return true;
    }

    if (node == null || t == null) {
      return false;
    }

    if (node.val != t.val) return false;

    return check(node.left, t.left) && check(node.right, t.right);
  }
}
