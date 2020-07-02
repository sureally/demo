package com.coding.leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author shu wj
 * @Date 2020/7/1 23:25
 * @Description
 */
public class Leetcode77 {
  public static class Solution_01 {
    private List<List<Integer>> ans;
    private int n;
    private int k;

    public List<List<Integer>> combine(int n, int k) {
      ans = new ArrayList<>();
      this.n = n;
      this.k = k;
      backtrack(1, new LinkedList<>());
      return ans;
    }

    private void backtrack(int first, LinkedList<Integer> track) {
      if (k == track.size()) {
        ans.add(new ArrayList<>(track));
        return;
      }

      // 思考，就是 不会存在 【3，1】，这种情况，因为 first的限制
      for (int i = first; i <= n; i++) {
        if (track.contains(i)) continue;
        track.addLast(i);
        backtrack(i + 1, track);
        track.removeLast();
      }
    }
  }
}
