package com.coding.leetcode.dp;

/**
 * @Author shu wj
 * @Date 2020/6/25 16:30
 * @Description
 */
public class Leetcode72 {
  public static class Solution_02 {
    public int minDistance(String word1, String word2) {

      // 明确状态和选择
      int m = word1.length(), n = word2.length();
      int[][] dp = new int[m + 1][n + 1];

      for (int i = 1; i <= m; i++) {
        dp[i][0] = i;
      }
      for (int j = 1; j <= n; j++) {
        dp[0][j] = j;
      }

      for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
          if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
            dp[i][j] = dp[i - 1][j - 1];
          } else {
            // 删除，新增，替换
            dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
          }
        }
      }

      return dp[m][n];
    }
  }
}
