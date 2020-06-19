package com.coding.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author shu wj
 * @Date 2020/6/15 23:40
 * @Description
 */
public class Leetcode199 {

  private List<List<Integer>> levels = new ArrayList<>();
  // 第一思考，从右向左层序遍历后，取每层第一个也是可以的
  public List<Integer> rightSideView(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if (null == root) return res;
    dfs(root, 0);
    for (List<Integer> level : levels) {
      res.add(level.get(0));
    }
    return res;
  }

  private void dfs(TreeNode node, int depth) {
    if (null == node) return;

    if (levels.size() <= depth) {
      levels.add(new ArrayList<>());
    }

    levels.get(depth).add(node.val);
    dfs(node.right, depth + 1);
    dfs(node.left, depth + 1);
  }

}
