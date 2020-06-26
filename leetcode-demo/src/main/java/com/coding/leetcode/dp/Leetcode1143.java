package com.coding.leetcode.dp;

/**
 * @Author shu wj
 * @Date 2020/6/26 14:37
 * @Description
 */
public class Leetcode1143 {
  public static class Solution_01 {
    public int longestCommonSubsequence(String text1, String text2) {
      if (null == text1 || null == text2) return 0;
      // 明确状态和选择
      // 状态：s1中的索引 和 s2中的索引
      // dp[i][j] 表示以 s1 第i个元素和 s2 第j个元素结尾的最长公共子串的最长长度
      int n = text1.length();
      int m = text2.length();

      int[][] dp = new int[n + 1][m + 1];
      for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
          if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
            dp[i][j] = dp[i - 1][j - 1] + 1;
          } else {
            dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
          }
        }
      }


      return dp[n][m];
    }
  }
}
