package com.coding.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author shu wj
 * @Date 2020/4/4 20:22
 * @Description
 */
public class Leetcode102 {
  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (null == root) {
      return result;
    }

    // 层序使用队列作为缓存
    Queue<TreeNode> layerNodes = new LinkedList<>();
    layerNodes.offer(root);

    while (!layerNodes.isEmpty()) {
      int size = layerNodes.size();
      List<Integer> layerResult = new ArrayList<>();
      for (int i = 0; i < size; i++) {
        TreeNode node = layerNodes.poll();
        layerResult.add(node.val);
        if (null != node.left) {
          layerNodes.offer(node.left);
        }
        if (null != node.right) {
          layerNodes.offer(node.right);
        }
      }
      result.add(layerResult);
    }

    return result;
  }
}
