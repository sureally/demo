package com.coding.leetcode.dp;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author shu wj
 * @Date 2020/6/27 21:06
 * @Description
 */
public class Leetcode435 {
  public static class Solution_01 {
    public int eraseOverlapIntervals(int[][] intervals) {
      if (null == intervals || intervals.length <= 1) return 0;
      Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

      int count = 1;
      int[] cur = intervals[0];
      for (int[] interval : intervals) {
        if (interval[0] >= cur[1]) {
          count++;
          cur = interval;
        }
      }

      return intervals.length - count;
    }
  }
}
