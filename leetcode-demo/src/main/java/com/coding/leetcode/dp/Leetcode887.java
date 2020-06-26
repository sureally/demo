package com.coding.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

import javafx.util.Pair;

/** @Author shu wj @Date 2020/6/25 17:03 @Description */
public class Leetcode887 {
  // 时间复杂度为 O(KN*N)，不能满足题目要求。但是，需要理解这个动态规划，以此为基础优化得到 O(KNlogN) 的实现
  public static class Solution_01 {

    private Map<Pair<Integer, Integer>, Integer> dict = new HashMap<>();

    public int superEggDrop(int K, int N) {
      if (K == 1) return N;
      if (N == 0) return 0;
      dfs(K, N);
      return dict.get(new Pair<>(K, N));
    }

    private int dfs(int K, int N) {
      if (K == 1) return N;
      if (N == 0) return 0;

      if (dict.containsKey(new Pair<>(K, N))) {
        return dict.get(new Pair<>(K, N));
      }

      int res = Integer.MAX_VALUE;
      for (int i = 1; i <= N; i++) {
        res = Math.min(res, Math.max(dfs(K, N - i), dfs(K - 1, i - 1)) + 1);
      }

      dict.put(new Pair<>(K, N), res);

      return res;
    }
  }

  // TODO: 需要优化为 O(KNlogN)
  public static class Solution_02 {

  }

  public static void main(String[] args) {
    Solution_01 solution_01 = new Solution_01();
    int K = 1;
    int N = 2;
    System.out.println(solution_01.superEggDrop(K, N));
  }
}
