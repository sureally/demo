package com.coding.leetcode.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Author shu wj
 * @Date 2020/1/5 16:44
 * @Description
 */
public class Leetcode218 {
  public List<List<Integer>> solution2(int[][] buildings) {
   throw new RuntimeException();
  }


  public List<List<Integer>> solution(int[][] buildings) {
    List<List<Integer>> results = new ArrayList<>(16);
    List<int[]> pointsQueue = new ArrayList<>(16);
    if (null == buildings || buildings.length == 0) {
      return results;
    }

    for (int[] building : buildings) {
      pointsQueue.add(new int[] {building[0], building[2]}); // 左端点，高度为正
      pointsQueue.add(new int[] {building[1], -building[2]}); // 右端点，高度为负数
    }

    // 总体来说，就是 左边左优先
    pointsQueue.sort((a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);

    PriorityQueue<Integer> heightHeap = new PriorityQueue<>(Comparator.reverseOrder());
    heightHeap.offer(0);
    int prevHeight = 0;

    for (int[] point : pointsQueue) {
      int height = point[1];
      if (height > 0) {
        // 左端点加入
        heightHeap.offer(height);
      } else {
        // 右端点 移除
        // remove 的操作的时间复杂度是 o(n)
        heightHeap.remove(-height);
      }

      Integer curHeight = heightHeap.peek();

      if (curHeight != null && prevHeight != curHeight) {
        List<Integer> oneLinePoint = new ArrayList<>(2);
        oneLinePoint.add(point[0]);
        // 当前最高的位置
        oneLinePoint.add(curHeight);
        results.add(oneLinePoint);
        prevHeight = curHeight;
      }
    }

    return results;
  }

  public static void main(String[] args) {
    int[][] buildings = {{2,9,10}, {3,7,15}, {5,12,12}, {15,20,10}, {19,24,8}};

    System.out.println(new Leetcode218().solution(buildings));
  }
}

