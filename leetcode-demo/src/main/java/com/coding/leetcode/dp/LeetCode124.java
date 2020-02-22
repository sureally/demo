package com.coding.leetcode.dp;

/**
 * @Author shu wj
 * @Date 2020/2/22 17:41
 * @Description Hard;二叉树中的最大路径和
 */
public class LeetCode124 {

  static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  int max = Integer.MIN_VALUE;
    /**
     * 递归统计，O(n)
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
      maxPathSumRecursion(root);
      return max;
    }

    private int maxPathSumRecursion(TreeNode root) {
      if (null == root) {
        return 0;
      }

      // 初始值为0
      int leftMaxValue = Math.max(0, maxPathSumRecursion(root.left));
      int rightMaxValue = Math.max(0, maxPathSumRecursion(root.right));

    max = Math.max(max, leftMaxValue + rightMaxValue + root.val);

    return Math.max(leftMaxValue, rightMaxValue) + root.val;
  }
}
