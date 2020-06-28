package com.coding.leetcode.dp;

/** @Author shu wj @Date 2020/6/27 21:43 @Description */
public class Leetcode188 {
  public static class Solution_01 {
    public int maxProfit(int K, int[] prices) {
      if (null == prices || prices.length <= 0) return 0;
      if (K <= 0) return 0;

      int n = prices.length;

      // 优化 trick: K <= n / 2
      K = Math.max(K, n / 2);
      int[][] dp = new int[K + 1][2];

      // dp[i][k][j]
      for (int k = 1; k <= K; k++) {
        dp[k][0] = 0;
        dp[k][1] = Integer.MIN_VALUE;
      }

      for (int i = 1; i <= n; i++) {
        for (int k = K; k >= 1; k--) {
          dp[k][0] = Math.max(dp[k][0], dp[k][1] + prices[i - 1]);
          dp[k][1] = Math.max(dp[k][1], dp[k - 1][0] - prices[i - 1]);
        }
      }

      return dp[K][0];
    }
  }
}
