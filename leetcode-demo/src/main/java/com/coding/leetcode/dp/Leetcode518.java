package com.coding.leetcode.dp;

/** @Author shu wj @Date 2020/6/25 15:42 @Description */
public class Leetcode518 {

  public static class Solution_01 {
    public int change(int amount, int[] coins) {
      // 明确状态和选择
      if (coins == null) return 0;
      int n = coins.length;
      int[][] dp = new int[n + 1][amount + 1];

      for (int i = 0; i <= n; i++) {
        dp[i][0] = 1;
      }
      for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= amount; j++) {
          if (j < coins[i - 1]) {
            dp[i][j] = dp[i - 1][j];
          } else {
            dp[i][j] = dp[i][j - coins[i - 1]] + dp[i - 1][j];
          }
        }
      }

      return dp[n][amount];
    }
  }

  public static class Solution_02 {
    public int change(int amount, int[] coins) {
      // 明确状态和选择
      if (coins == null) return 0;
      int n = coins.length;

      int[] dp = new int[amount + 1];
      dp[0] = 1;
      for (int coin : coins) {
        for (int j = 1; j <= amount; j++) {
          if (j >= coin) {
            dp[j] = dp[j] + dp[j - coin];
          }
        }
      }

      return dp[amount];
    }
  }

  public static void main(String[] args) {
    Solution_01 solution_01 = new Solution_01();
    int amount = 5;
    int[] coins = {1, 2, 5};

    System.out.println(solution_01.change(amount, coins));
  }
}
