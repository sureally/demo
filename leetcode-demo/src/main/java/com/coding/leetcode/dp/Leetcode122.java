package com.coding.leetcode.dp;

/** @Author shu wj @Date 2020/6/27 21:43 @Description */
public class Leetcode122 {
  public static class Solution_01 {
    /** */
    public int maxProfit(int[] prices) {
      if (null == prices || prices.length <= 0) return 0;
      int n = prices.length;
      int K = 2; // 交易次数
      int[][][] dp = new int[n + 1][K + 1][2];

      // dp[i][k][j]
      for (int k = 1; k <= 2; k++) {
        dp[0][k][0] = 0;
        dp[0][k][1] = Integer.MIN_VALUE;
      }

      // 当买入股票的时候，交易次数 k 加 1
      for (int i = 1; i <= n; i++) {
        for (int k = 1; k <= 2; k++) {
          dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i - 1]);
          dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i - 1]);
        }
      }

      return dp[n][K][0];
    }
  }
}
