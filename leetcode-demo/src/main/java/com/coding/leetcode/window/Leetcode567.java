package com.coding.leetcode.window;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/** @Author shu wj @Date 2020/7/4 13:26 @Description */
public class Leetcode567 {

  // 如果用数组存储，速度会更快。
  public static class Solution_01 {
    public boolean checkInclusion(String s1, String s2) {
      if (s1 == null || s2 == null) return false;
      if (s1.length() > s2.length()) return false;

      Map<Character, Integer> targets = new HashMap<>();
      for (char ch : s1.toCharArray()) {
        targets.compute(ch, (k, v) -> v == null ? 1 : v + 1);
      }

      int left = 0, right = 0;
      int valid = 0;
      Map<Character, Integer> window = new HashMap<>();

      while (right < s2.length()) {
        char ch = s2.charAt(right++);

        if (targets.containsKey(ch)) {
          if (Objects.equals(
              window.compute(ch, (k, v) -> v == null ? 1 : v + 1), targets.get(ch))) {
            valid++;
          }
        }

        while (left < right && valid == targets.size()) {
          System.out.println(s2.substring(left, right));
          if (right - left == s1.length()) {
            return true;
          }

          char ich = s2.charAt(left++);
          if (targets.containsKey(ich)) {
            if (window.compute(ich, (k, v) -> v - 1) < targets.get(ich)) {
              valid--;
            }
          }
        }
      }

      return false;
    }
  }

  public static void main(String[] args) {
    Solution_01 solution_01 = new Solution_01();
    System.out.println(solution_01.checkInclusion("abc", "bbbca"));
  }
}
