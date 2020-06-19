package com.coding.leetcode.tree;

/** @Author shu wj @Date 2020/5/25 23:37 @Description */
public class Leetcode297 {
  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    StringBuilder sb = new StringBuilder();
    dfsSerialize(root, sb);
    return sb.toString();
  }

  private void dfsSerialize(TreeNode root, StringBuilder sb) {
    if (null == root) {
      sb.append("#,");
      return;
    }
    sb.append(root.val).append(",");
    dfsSerialize(root.left, sb);
    dfsSerialize(root.right, sb);
  }


  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    String[] arr = data.substring(0, data.length() - 1).split("\\s*,\\s*");
    TreeNode root = new TreeNode(0);
    dfsDes(root, arr);
    return root;
  }

  private Integer index = 0;
  private TreeNode dfsDes(TreeNode root, String[] arr) {
    if ("#".equals(arr[index])) {
      index++;
      return null;
    }
    root.val = Integer.valueOf(arr[index++]);

    root.left = dfsDes(new TreeNode(0), arr);
    root.right = dfsDes(new TreeNode(0), arr);
    return root;
  }
  // Your Codec object will be instantiated and called as such:
  // Codec codec = new Codec();
  // codec.deserialize(codec.serialize(root));

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.right.left = new TreeNode(4);
    root.right.right = new TreeNode(5);

    Leetcode297 leetcode297 = new Leetcode297();
    String s = leetcode297.serialize(root);
    System.out.println(s);

    TreeNode newRoot = leetcode297.deserialize(s);
    String ss = leetcode297.serialize(newRoot);
    System.out.println(ss);
  }
}
