package com.coding.leetcode.tree.binarysearchtree;

import com.coding.leetcode.tree.TreeNode;

/**
 * @Author shu wj
 * @Date 2020/6/29 01:24
 * @Description
 */
public class Leetcode701 {
  public static class Solution_01 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
      if (null == root) return new TreeNode(val);

      if (root.val > val) {
        root.left = insertIntoBST(root.left, val);
      } else if (root.val < val) {
        root.right = insertIntoBST(root.right, val);
      } else {
        // 不应该存在
      }

      return root;
    }
  }
}
