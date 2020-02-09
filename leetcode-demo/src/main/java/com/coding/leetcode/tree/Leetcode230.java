package com.coding.leetcode.tree;

/**
 * @Author shu wj
 * @Date 2019/12/12 22:37
 * @Description
 */
public class Leetcode230 {
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public int kthSmallest(TreeNode root, int k) {
    // left -> n
    int leftPartNum = count(root.left);
    if (leftPartNum == k - 1) {
      return root.val;
    } else if (leftPartNum > k - 1) {
      return kthSmallest(root.left, k);
    } else {
      return kthSmallest(root.right, k - leftPartNum - 1);
    }
  }

  private int count(TreeNode node) {
    if (null == node) {
      return 0;
    }
    return 1 + count(node.left) + count(node.right);
  }
}
