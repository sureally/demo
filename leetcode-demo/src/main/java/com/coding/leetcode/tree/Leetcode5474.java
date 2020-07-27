package com.coding.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shu wj
 */
public class Leetcode5474 {
  public static class Solution_01 {
    /**
     * 需要从下往上遍历.
     * @param root
     * @param distance
     * @return
     */
    private int ans = 0;
    public int countPairs(TreeNode root, int distance) {
      dfs(root, distance);
      return ans;
    }

    private List<Integer> dfs(TreeNode root, int distance)  {
      List<Integer> ret = new ArrayList<>();
      if (null == root) return ret;
      if (root.left == null && root.right == null) {
        ret.add(1);
        return ret;
      }

      List<Integer> left = dfs(root.left, distance);
      for (int e : left)  {
        if (++e > distance) {
          continue;
        }
        ret.add(e);
      }

      List<Integer> right = dfs(root.right, distance);
      for (int e : right)  {
        if (++e > distance) {
          continue;
        }
        ret.add(e);
      }

      for (int l : left) {
        for (int r : right) {
          ans += (l + r <= distance) ? 1 : 0;
        }
      }

      return ret;
    }
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.right.left = new TreeNode(6);
    root.right.right = new TreeNode(7);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(5);
    Solution_01 solution_01 = new Solution_01();
    System.out.println(solution_01.countPairs(root, 2));
  }
}
