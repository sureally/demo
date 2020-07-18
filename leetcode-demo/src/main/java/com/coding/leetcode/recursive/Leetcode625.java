package com.coding.leetcode.recursive;

import java.util.LinkedList;

/** @Author shu wj @Date 2020/7/18 21:26 @Description */
public class Leetcode625 {
  public static class Solution_01 {
    public int smallestFactorization(int a) {
      LinkedList<Integer> ans = new LinkedList<>();
      if (!helper(a, ans)) return 0;
      ans.sort(Integer::compareTo);
      StringBuilder sb = new StringBuilder();
      for (Integer s : ans) {
        sb.append(s);
      }
      long res = Long.parseLong(sb.toString());
      if (res > Integer.MAX_VALUE) return 0;

      return (int) res;
    }

    private boolean helper(int a, LinkedList<Integer> ans) {
      if (a < 1) return false;
      if (a == 1) {
        if (ans.isEmpty()) {
          ans.add(a);
        }
        return true;
      }

      for (int i = 9; i >= 2; i--) {
        if (a % i != 0) {
          continue;
        }
        ans.add(i);
        if (helper(a / i, ans)) return true;
        ans.removeLast();
      }

      return false;
    }
  }

  public static class Solution_02 {
    public int smallestFactorization(int a) {
      if (a < 10) {
        return a;
      }
      long res = 0;
      long mul = 1;
      for (int i = 9; i >= 2; i--) {
        while (a % i == 0) {
          a /= i;
          res += mul * i;
          mul *= 10;
        }
      }
      return (a < 2 && res <= Integer.MAX_VALUE) ? (int) res : 0;
    }
  }

  public static void main(String[] args) {
    Solution_01 solution_01 = new Solution_01();
    System.out.println(solution_01.smallestFactorization(15));
  }
}
