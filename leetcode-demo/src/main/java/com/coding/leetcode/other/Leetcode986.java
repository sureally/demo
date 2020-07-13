package com.coding.leetcode.other;

import java.util.LinkedList;

/** @Author shu wj @Date 2020/7/13 23:50 @Description */
public class Leetcode986 {
  public static class Solution_01 {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
      LinkedList<int[]> res = new LinkedList<>();

      int i = 0, j = 0;
      while (i < A.length && j < B.length) {
        int a1 = A[i][0], a2 = A[i][1];
        int b1 = B[j][0], b2 = B[j][1];
        if (b2 >= a1 && a2 >= b1) {
          res.addLast(new int[] {Math.max(a1, b1), Math.min(a2, b2)});
        }

        if (b2 < a2) {
          j += 1;
        } else {
          i += 1;
        }
      }
      int[][] ans = new int[res.size()][2];
      res.toArray(ans);

      return ans;
    }
  }
}
