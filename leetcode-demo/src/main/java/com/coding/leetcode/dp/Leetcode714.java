package com.coding.leetcode.dp;

/** @Author shu wj @Date 2020/6/27 21:44 @Description */
public class Leetcode714 {
  public static class Solution_01 {
    public int maxProfit(int[] prices, int fee) {
      if (null == prices || prices.length <= 1) return 0;

      int n = prices.length;
      int[][] dp = new int[n + 1][2];

      dp[0][1] = Integer.MIN_VALUE;
      for (int i = 1; i <= n; i++) {
        dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i - 1]);
        dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i - 1]);
      }

      return dp[n][0];
    }
  }
}
