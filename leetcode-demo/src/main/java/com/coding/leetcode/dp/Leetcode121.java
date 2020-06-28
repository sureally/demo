package com.coding.leetcode.dp;

/** @Author shu wj @Date 2020/6/27 21:43 @Description */
public class Leetcode121 {
  public static class Solution_01 {
    public int maxProfit(int[] prices) {
      if (prices == null || prices.length <= 1) return 0;

      // dp[i][k][0/1] 今天是第 i 天，现在手上未持有/持有股票着股票，至今最多进行 K 次交易。
      int n = prices.length;
      int[][] dp = new int[n + 1][2];

      dp[0][0] = 0;
      dp[0][1] = -prices[0];
      for (int i = 1; i <= n; i++) {
        dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i - 1]);
        dp[i][1] = Math.max(dp[i - 1][1], -prices[i - 1]);
      }
      return dp[n][0];
    }
  }
}
