package com.coding.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/** @Author shu wj @Date 2020/7/2 23:47 @Description */
public class Leetcode22 {
  public static class Solution_01 {
    private List<String> ans = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
      backtrack(n, n, new StringBuilder());
      return ans;
    }

    private void backtrack(int left, int right, StringBuilder track) {
      // 若左括号剩下的多，说明不合法
      if (right < left) return;
      // 数量小于 0 肯定是不合法的
      if (left < 0) return;
      // 当所有括号都恰好用完时，得到一个合法的括号组合
      if (left == 0 && right == 0) {
        ans.add(track.toString());
        return;
      }

      // 尝试放一个左括号
      track.append('('); // 选择
      backtrack(left - 1, right, track);
      track.deleteCharAt(track.length() - 1); // 撤消选择

      // 尝试放一个右括号
      track.append(')'); // 选择
      backtrack(left, right - 1, track);
      track.deleteCharAt(track.length() - 1); // 撤消选择
    }
  }
}
