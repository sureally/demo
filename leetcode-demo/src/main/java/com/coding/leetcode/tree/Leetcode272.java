package com.coding.leetcode.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author shu wj
 * @Date 2020/6/20 14:56
 * @Description
 */
public class Leetcode272 {
  // 基本思路：遍历找到左右端点？ 即 less(right) - less(left) + 1 = k
  private final LinkedList<Integer> res = new LinkedList<>();
  private double target;
  private int k;

  public List<Integer> closestKValues(TreeNode root, double target, int k) {
    this.target = target;
    this.k = k;
    dfs(root);
    return res;
  }

  private void dfs(TreeNode node) {
    if (node == null) return;

    dfs(node.left);
    if (res.size() < k) {
      res.add(node.val);
    } else {
      int first = res.getFirst();
      if (Math.abs(first - target) >= Math.abs(node.val - target)) {
        res.removeFirst();
        res.add(node.val);
      }
    }
    dfs(node.right);
  }
}
