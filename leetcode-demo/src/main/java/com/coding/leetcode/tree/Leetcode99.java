package com.coding.leetcode.tree;

import java.util.ArrayDeque;
import java.util.Deque;

/** @Author shu wj @Date 2020/6/20 15:43 @Description */
public class Leetcode99 {

  // 基本思路：
  // 在这里，我们通过迭代构造中序遍历，并在一次遍历中找到交换的节点。
  // 迭代顺序很简单：尽可能的向左走，然后向右走一步，重复一直到结束。
  // 若要找到交换的节点，就记录中序遍历中的最后一个节点 pred（即当前节点的前置节点），并与当前节点的值进行比较。如果当前节点的值小于前置节点 pred 的值，说明该节点是交换节点之一。
  // 交换的节点只有两个，因此在确定了第二个交换节点以后，可以终止遍历。
  public static class Solution_1 {
    public void swap(TreeNode a, TreeNode b) {
      int tmp = a.val;
      a.val = b.val;
      b.val = tmp;
    }

    public void recoverTree(TreeNode root) {
      Deque<TreeNode> stack = new ArrayDeque<>();
      TreeNode x = null, y = null, pred = null;

      while (!stack.isEmpty() || root != null) {
        while (root != null) {
          stack.push(root);
          root = root.left;
        }
        // pop() 是移除第一个元素
        root = stack.pop();
        if (pred != null && root.val < pred.val) {
          y = root;
          if (x == null) x = pred;
          else break;
        }
        pred = root;
        root = root.right;
      }
      if (null == x) {
        throw new RuntimeException();
      }
      swap(x, y);
    }
  }

  public static class Solution_2 {
    TreeNode x = null, y = null, pred = null;

    public void swap(TreeNode a, TreeNode b) {
      int tmp = a.val;
      a.val = b.val;
      b.val = tmp;
    }

    public void findTwoSwapped(TreeNode root) {
      if (root == null) return;
      findTwoSwapped(root.left);
      if (pred != null && root.val < pred.val) {
        y = root;
        if (x == null) x = pred;
        else return;
      }
      pred = root;
      findTwoSwapped(root.right);
    }

    public void recoverTree(TreeNode root) {
      findTwoSwapped(root);
      swap(x, y);
    }
  }

  // Morris 的遍历思想很简单：只遍历树而不是用空间。
  // 基本思路：省去额外的栈空间了。利用叶子节点的右子树这个特点，将其重新赋予指向关系 ，就是莫里斯遍历的核心了。
  public static class Solution_3 {
    TreeNode x = null, y = null, pred = null, predecessor = null;

    private void swap(TreeNode a, TreeNode b) {
      int tmp = a.val;
      a.val = b.val;
      b.val = tmp;
    }

    private TreeNode deal(TreeNode root) {
      if (pred != null && root.val < pred.val) {
        y = root;
        if (x == null) x = pred;
      }
      pred = root;
      
      return root.right;
    }


    public void recoverTree(TreeNode root) {
      while (root != null) {
        if (root.left != null) {
          predecessor = root.left;
          while (predecessor.right != null && predecessor.right != root) {
            predecessor = predecessor.right;
          }
          if (predecessor.right == null) {
            predecessor.right = root;
            root = root.left;
          } else {
            root = deal(root);
            // 断开连接，恢复原状，不然就形成一个图了，而不是原来的树了。
            predecessor.right = null;
          }
        } else {
          root = deal(root);
        }
      }
      if (null == x) {
        throw new RuntimeException();
      }
      swap(x, y);
    }
  }
}
