package com.coding.leetcode.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @Author shu wj
 * @Date 2020/6/19 23:44
 * @Description
 */
public class Leetcode662 {
  // 这里的思路就是，position，子节点分别是 position * 2, position * 2 + 1
  public int widthOfBinaryTree(TreeNode root) {
    if (root == null) return 0;
    int res = 0;
    Queue<TreeNode> queue = new LinkedList<>();
    root.val = 1;
    queue.add(root);
    while (!queue.isEmpty()) {
      int size = queue.size();
      int left = Integer.MAX_VALUE;
      int right = Integer.MIN_VALUE;
      for (int i = 0; i < size; i++) {
        TreeNode cur = queue.poll();
        left = Math.min(left, cur.val);
        right = Math.max(right, cur.val);
        if (cur.left != null) {
          cur.left.val = cur.val * 2;
          queue.add(cur.left);
        }
        if (cur.right != null) {
          cur.right.val = cur.val * 2 + 1;
          queue.add(cur.right);
        }
      }
      res = Math.max(right - left + 1, res);
    }
    return res;
  }

  public int solution_2(TreeNode root) {
    dfs(root, 1, 1);
    return res;
  }

  private int res = 0;
  private Map<Integer, Integer> left = new HashMap<>();
  private void dfs(TreeNode node, int depth, int pos) {
    if (null == node) return;

    left.computeIfAbsent(depth, (k) -> pos);
    dfs(node.left, depth + 1, pos * 2);
    dfs(node.right, depth + 1, pos * 2 + 1);

    res = Math.max(res, pos - left.get(depth) + 1);
  }

//  public int widthOfBinaryTree(TreeNode root) {
//    Queue<AnnotatedNode> queue = new LinkedList();
//    queue.add(new AnnotatedNode(root, 0, 0));
//    int curDepth = 0, left = 0, ans = 0;
//    while (!queue.isEmpty()) {
//      AnnotatedNode a = queue.poll();
//      if (a.node != null) {
//        queue.add(new AnnotatedNode(a.node.left, a.depth + 1, a.pos * 2));
//        queue.add(new AnnotatedNode(a.node.right, a.depth + 1, a.pos * 2 + 1));
//        if (curDepth != a.depth) {
//          curDepth = a.depth;
//          left = a.pos;
//        }
//        ans = Math.max(ans, a.pos - left + 1);
//      }
//    }
//    return ans;
//  }

}
