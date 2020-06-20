package com.coding.leetcode.tree;

/**
 * @Author shu wj
 * @Date 2020/6/20 10:43
 * @Description
 */
public class Leetcode1339 {

  int rootValue = 0;
  long max = 0L;

  public int maxProduct(TreeNode root) {
    dfs(root);
    this.rootValue = root.val;
    dfs2(root);
    return (int) (max % 1000000007);
  }


  private int dfs(TreeNode node) {
    if (node == null) {
      return 0;
    }
    int left = dfs(node.left);
    int right = dfs(node.right);
    int mid = left + right + node.val;
    node.val = mid;
    return mid;
  }

  private void dfs2(TreeNode node) {
    if (node == null) {
      return;
    }
    // 这里的rootValue表示的原始整个树的和值
    this.max = Math.max(max, (long) (rootValue - node.val) * node.val);
    dfs2(node.left);
    dfs2(node.right);
  }


  public static void main(String[] args) {
    Leetcode1339 leetcode1339 = new Leetcode1339();
  }
}

