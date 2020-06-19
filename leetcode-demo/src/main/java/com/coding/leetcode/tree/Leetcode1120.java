package com.coding.leetcode.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author shu wj
 * @Date 2020/6/15 23:53
 * @Description
 */
public class Leetcode1120 {

  private double ans = Double.MIN_VALUE;
  private Map<TreeNode, Double> averages = new HashMap<TreeNode, Double>() {
    {
      put(null, 0.0);
    }
  };
  // 直觉：一个map保存所有节点的平均值，从下向上递归，然后其子树的个数
  public double maximumAverageSubtree(TreeNode root) {
    if (null == root) return 0.0;

    dfs(root);

    return ans;
  }

  private int dfs(TreeNode node) {
    if (null == node) return 0;

    int leftNum = dfs(node.left);
    int rightNum = dfs(node.right);

    double avg = (averages.get(node.left) * leftNum + averages.get(node.right) * rightNum + node.val) / (leftNum + rightNum + 1);
    ans = Math.max(avg, ans);
    averages.put(node, avg);
    return leftNum + rightNum + 1;
  }
}
