package com.coding.leetcode.tree;

/** @Author shu wj @Date 2020/6/16 23:56 @Description */
public class Leetcode250 {

  private int count = 0;

  private boolean is_uni(TreeNode node) {
    if (null == node) return true;
    // 叶子节点是 同值子树

    boolean left = is_uni(node.left);
    boolean right = is_uni(node.right);

    if (node.left == null && node.right == null) {
      count++;
      return true;
    }

    if (node.left != null && node.val != node.left.val) {
      return false;
    }

    if (node.right != null && node.val != node.right.val) {
      return false;
    }

    if (left && right) {
      count++;
    }

    return true;
  }

  public int countUnivalSubtrees(TreeNode root) {
    is_uni(root);
    return count;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(5);
    root.left = new TreeNode(1);
    root.right = new TreeNode(5);
    root.right.right = new TreeNode(5);
    root.left.left = new TreeNode(5);
    root.left.right = new TreeNode(5);

    System.out.println(new Leetcode250().countUnivalSubtrees(root));
  }
}
