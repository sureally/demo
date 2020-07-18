package com.coding.leetcode.recursive;

import java.util.ArrayList;
import java.util.List;

/** @Author shu wj @Date 2020/7/18 21:01 @Description */
public class Leetcode544 {
  public static class Solution_01 {

    public String findContestMatch(int n) {
      List<String> ans = new ArrayList<>();
      for (int i = 0; i < n / 2; i++) {
        ans.add("(" + (i + 1) + "," + (n - i) + ")");
      }
      helper(ans);
      return ans.get(0);
    }

    // 递归，子问题，
    private void helper(List<String> mem) {
      if (mem.size() <= 1) return;
      List<String> newMem = new ArrayList<>();
      int size = mem.size();
      for (int i = 0; i < size / 2; i++) {
        newMem.add("(" + mem.get(i) + "," + mem.get(size - i - 1) + ")");
      }
      mem.clear();
      mem.addAll(newMem);

      helper(mem);
    }
  }

  public static void main(String[] args) {
    Solution_01 solution_01 = new Solution_01();
    System.out.println(solution_01.findContestMatch(2));
    System.out.println(solution_01.findContestMatch(4));
    System.out.println(solution_01.findContestMatch(8));
  }
}
