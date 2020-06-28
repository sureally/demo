package com.coding.leetcode.dp;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/** @Author shu wj @Date 2020/6/27 12:27 @Description */
public class Leetcode651 {
  // 超时
  public static class Solution_01 {
    public static class Tuple<T, U, V> {
      T t;
      U u;
      V v;

      @Override
      public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple<?, ?, ?> tuple = (Tuple<?, ?, ?>) o;
        return t.equals(tuple.t) && u.equals(tuple.u) && v.equals(tuple.v);
      }

      @Override
      public int hashCode() {
        return Objects.hash(t, u, v);
      }

      public Tuple(T t, U u, V v) {
        this.t = t;
        this.u = u;
        this.v = v;
      }
    }

    private final Map<Tuple<Integer, Integer, Integer>, Integer> mem = new HashMap<>();

    public int maxA(int N) {
      return dp(N, 0, 0);
    }

    private int dp(int N, int show, int cache) {
      if (N <= 0) return show;
      Integer tmp = mem.get(new Tuple<>(N, show, cache));
      if (null != tmp) return tmp;
      int res = Math.max(dp(N - 1, show + 1, cache), dp(N - 1, show + cache, cache));
      res = Math.max(res, dp(N - 2, show, show));

      mem.put(new Tuple<>(N, show, cache), res);
      return res;
    }
  }

  public static class Solution_02 {
    public int maxA(int N) {
      int[] dp = new int[N + 1];
      dp[0] = 0;
      for (int i = 1; i <= N; i++) {
        // 按 A 键
        dp[i] = dp[i - 1] + 1;
        for (int j = 2; j < i; j++) {
          // 全选 & 复制 dp[j-2]，连续粘贴 i - j 次
          // 屏幕上共 dp[j - 2] * (i - j + 1) 个 A
          dp[i] = Math.max(dp[i], dp[j - 2] * (i - j + 1));
        }
      }
      // N 次按键之后最多有几个 A？
      return dp[N];
    }
  }

  public static void main(String[] args) {
    Solution_01 solution_01 = new Solution_01();
    int N = 7;
    System.out.println(solution_01.maxA(N));
  }
}
