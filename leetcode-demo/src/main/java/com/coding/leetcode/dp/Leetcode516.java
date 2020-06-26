package com.coding.leetcode.dp;

/**
 * @Author shu wj
 * @Date 2020/6/26 21:47
 * @Description
 *
 * 找状态转移需要归纳思维，说白了就是如何从已知的结果推出未知的部分
 */
public class Leetcode516 {
  public static class Solution_01 {
    public int longestPalindromeSubseq(String s) {
      if (null == s || s.length() == 0) return 0;
      int n = s.length();
      int[][] dp = new int[n][n];
      for (int i = 0; i < n; i++) {
        dp[i][i] = 1;
      }
      for (int i = n - 1; i >= 0; i--) {
        for (int j = i + 1; j < n; j++) {
          if (s.charAt(i) == s.charAt(j)) {
            dp[i][j] = dp[i + 1][j - 1] + 2;
          } else {
            dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
          }
        }
      }

      return dp[0][n - 1];
    }
  }

  public static void main(String[] args) {
    Solution_01 solution_01 = new Solution_01();
    String s = "bbbab";

    System.out.println(solution_01.longestPalindromeSubseq(s));
  }
}
