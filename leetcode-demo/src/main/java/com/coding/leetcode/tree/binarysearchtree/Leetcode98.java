package com.coding.leetcode.tree.binarysearchtree;

import com.coding.leetcode.tree.TreeNode;

/**
 * @Author shu wj
 * @Date 2020/6/29 01:23
 * @Description
 */
public class Leetcode98 {
  public static class Solution_01 {
    private TreeNode prev;
    public boolean isValidBST(TreeNode root) {
      if (null == root) return true;
      // 中序遍历，从左到右

      boolean left = isValidBST(root.left);
      if (prev != null && prev.val >= root.val) {
        return false;
      }
      prev = root;

      boolean right = isValidBST(root.right);

      return left && right;
    }
  }
}
