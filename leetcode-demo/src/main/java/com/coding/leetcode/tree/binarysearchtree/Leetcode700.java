package com.coding.leetcode.tree.binarysearchtree;

import com.coding.leetcode.tree.TreeNode;

/**
 * @Author shu wj
 * @Date 2020/6/29 01:23
 * @Description
 */
public class Leetcode700 {
  public static class Solution_01 {
    public TreeNode searchBST(TreeNode root, int val) {
      if (null == root) return null;

      if (root.val == val) return root;
      else if (root.val > val) return searchBST(root.left, val);
      else return searchBST(root.right, val);
    }
  }
}
