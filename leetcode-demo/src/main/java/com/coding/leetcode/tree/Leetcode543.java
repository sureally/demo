package com.coding.leetcode.tree;

/**
 * @Author shu wj
 * @Date 2020/6/20 14:47
 * @Description
 */
public class Leetcode543 {
  private int res = 0;
  public int diameterOfBinaryTree(TreeNode root) {
    dfs(root);
    return res == 0 ? 0 : res - 1;
  }

  private int dfs(TreeNode node) {
    // 后序遍历
    if (null == node) return 0;

    int left = dfs(node.left);
    int right = dfs(node.right);

    res = Math.max(res, left + right + 1);
    return Math.max(left + 1, right + 1);
  }
}
