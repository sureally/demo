package com.coding.leetcode.tree;

/**
 * @Author shu wj
 * @Date 2020/6/18 00:19
 * @Description
 */
public class Leetcode124 {

  private int res = Integer.MIN_VALUE;
  // 递归，从下往上，每个节点都能够知道自己和左树以及和右树组成的最大值，或者 自己与左右子树形成的最大路径
  public int maxPathSum(TreeNode root) {
    dfs(root);
    return res;
  }

  private int dfs(TreeNode root) {
    if (null == root) return 0;

    int left = dfs(root.left);
    int right = dfs(root.right);


    res = Math.max(left + root.val, res);
    res = Math.max(right + root.val, res);
    res = Math.max(left + root.val + right, res);

    int curMax = Math.max(root.val, Math.max(left + root.val, right + root.val));
    return Math.max(curMax, 0);
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);

    System.out.println(new Leetcode124().maxPathSum(root));


  }

}
