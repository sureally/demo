package com.coding.leetcode.tree;

import java.util.LinkedList;
import java.util.List;

/** @Author shu wj @Date 2020/6/18 00:42 @Description */
public class LeetcodeInterview0409 {

  List<List<Integer>> res = new LinkedList<>();

  public List<List<Integer>> BSTSequences(TreeNode root) {
    if (root == null) {
      res.add(new LinkedList<>());
      return res;
    }

    LinkedList<Integer> path = new LinkedList<>();
    path.add(root.val);

    helper(root, new LinkedList<>(), path);

    return res;
  }

  /**
   * 使用一个queue存储下个所有可能的节点
   * 然后选择其中一个作为path的下一个元素
   * 递归直到queue元素为空 将对应的path加入结果中
   * 由于二叉搜索树没有重复元素,而且每次递归的使用元素的顺序都不一样, 所以自动做到了去重

   */
  public void helper(TreeNode root, LinkedList<TreeNode> queue, LinkedList<Integer> path) {
    if (root == null) return;

    if (root.left != null) queue.add(root.left);
    if (root.right != null) queue.add(root.right);

    if (queue.isEmpty()) {
      res.add(new LinkedList<>(path));
      return;
    }

    int lens = queue.size();
    for (int i = 0; i < lens; i++){
      TreeNode cur = queue.get(i);
      queue.remove(i);
      path.add(cur.val);

      helper(cur, new LinkedList<>(queue), path);

      queue.add(i, cur);
      path.removeLast();
    }
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(2);
    root.left = new TreeNode(1);
    root.right = new TreeNode(3);

    List<List<Integer>> res = new LeetcodeInterview0409().BSTSequences(root);

    System.out.println(res);
  }
}
