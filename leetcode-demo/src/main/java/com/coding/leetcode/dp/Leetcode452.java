package com.coding.leetcode.dp;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author shu wj
 * @Date 2020/6/27 21:07
 * @Description
 */
public class Leetcode452 {
  public static class Solution_01 {
    public int findMinArrowShots(int[][] points) {
      if (null == points) return 0;
      else if (points.length <= 1) return points.length;

      Arrays.sort(points, Comparator.comparing(a -> a[1]));

      int count = 1;
      int[] cur = points[0];
      for (int[] interval : points) {
        if (interval[0] > cur[1]) {
          count++;
          cur = interval;
        }
      }

      return count;
    }

  }
}
