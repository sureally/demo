package com.coding.leetcode.other;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * @Author shu wj
 * @Date 2020/7/13 23:19
 * @Description
 */
public class Leetcode56 {
  public static class Solution_01 {
    public int[][] merge(int[][] intervals) {
      if (null == intervals) return null;

      Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

      LinkedList<int[]> res = new LinkedList<>();

      for (int[] interval : intervals) {
        if (res.isEmpty()) {
          res.add(interval);
          continue;
        }

        int[] last = res.getLast();
        if (last[1] >= interval[0]) {
          last[1] = Math.max(interval[1], last[1]);
        } else {
          res.addLast(interval);
        }

      }

      int[][] ans = new int[res.size()][2];
      for (int i = 0; i < res.size(); i++) {
        ans[i] = res.get(i);
      }

      return ans;
    }
  }
}
