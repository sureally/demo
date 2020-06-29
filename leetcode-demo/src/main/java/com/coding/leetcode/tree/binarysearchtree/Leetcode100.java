package com.coding.leetcode.tree.binarysearchtree;

import com.coding.leetcode.tree.TreeNode;

/** @Author shu wj @Date 2020/6/29 01:23 @Description */
public class Leetcode100 {
  public static class Solution_01 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
      if (p == null && q == null) return true;
      if (p == null || q == null) return false;

      if (p.val != q.val) return false;

      return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
  }
}
