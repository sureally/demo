package com.coding.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

import javafx.util.Pair;

/** @Author shu wj @Date 2020/6/26 22:50 @Description */
public class Leetcode10 {
  public static class Solution_01 {

    /**
     * def isMatch(s, p) -> bool : is_match = s is not empty and s[0] == p[0] return is_match and
     * isMatch(s[1:], p[1:])
     *
     * <p>for '*' def isMatch(s, p) -> bool : is_match = s is not empty and s[0] == p[0] if len(p)
     * >= 2 && p[1] == '*' return isMatch(s, p[2:]) and is_match and isMatch(s[1:], p)
     *
     * <p>for '.' def isMatch(s, p) -> bool : if p[0] == '.' return s is not empty and
     * isMatch(s[1:], p[1:0])
     *
     * 这种 dp 利用的是递归/穷举。靠备忘录降低运行时间。
     */
    public boolean isMatch(String s, String p) {
      if (s == null && p == null) return true;
      if (s == null || p == null) return false;
      // 状态 和 选择
      // dp[i][j]
      int n = s.length();
      int m = p.length();

      this.s = s;
      this.p = p;

      return dp(0, 0);
    }

    Map<Pair<Integer, Integer>, Boolean> mem = new HashMap<>();
    String s;
    String p;

    private boolean dp(int i, int j) {
      // 这个结束条件未想到。
      if (j == p.length()) return i == s.length();
      Boolean qf = mem.get(new Pair<>(i, j));
      if (null != qf) return qf;
      boolean first = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
      boolean res = false;
      if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
        res = dp(i, j + 2) || (first && dp(i + 1, j));
      } else {
        res = first && dp(i + 1, j + 1);
      }
      mem.put(new Pair<>(i, j), res);
      return res;
    }
  }
}
