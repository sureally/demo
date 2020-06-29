package com.coding.leetcode.tree.binarysearchtree;

import com.coding.leetcode.tree.TreeNode;

/** @Author shu wj @Date 2020/6/29 01:23 @Description */
public class Leetcode450 {
  public static class Solution_01 {
    private int successor(TreeNode root) {
      root = root.right;
      while (root.left != null) root = root.left;
      return root.val;
    }

    private int predecessor(TreeNode root) {
      root = root.left;
      while (root.right != null) root = root.right;
      return root.val;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
      if (root == null) return null;

      if (key > root.val) root.right = deleteNode(root.right, key);
      else if (key < root.val) root.left = deleteNode(root.left, key);
      else {
        if (root.left == null && root.right == null) root = null;
        else if (root.right != null) {
          root.val = successor(root);
          root.right = deleteNode(root.right, root.val);
        } else {
          root.val = predecessor(root);
          root.left = deleteNode(root.left, root.val);
        }
      }
      return root;
    }
  }
}
