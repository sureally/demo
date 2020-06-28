package com.coding.leetcode.dp;

/**
 * @Author shu wj
 * @Date 2020/6/26 22:20
 * @Description
 */
public class Leetcode877 {
  public static class Solution_01 {
    public boolean stoneGame(int[] piles) {
      if (null == piles || piles.length == 0) return true;
      int n = piles.length;

      int[][] dp = new int[n][n];
      for (int i = n - 1; i >= 0; i--) {
        for (int j = i; j < n; j++) {
          if (i == j) {
            dp[i][j] = piles[i];
            continue;
          }
          dp[i][j] = Math.max(dp[i + 1][j] + piles[i], dp[i][j - 1] + piles[j]);
        }
      }

      int sum = 0;
      for (int pile : piles) {
        sum += pile;
      }

      return sum < dp[0][n - 1] * 2;
    }
  }
}
