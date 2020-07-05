package com.coding.leetcode.window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/** @Author shu wj @Date 2020/7/4 13:26 @Description */
public class Leetcode438 {
  public static class Solution_01 {
    public List<Integer> findAnagrams(String s, String p) {
      if (s == null || p == null) return null;
      Map<Character, Integer> targets = new HashMap<>();
      for (char ch : p.toCharArray()) {
        targets.compute(ch, (k, v) -> v == null ? 1 : v + 1);
      }

      List<Integer> ans = new ArrayList<>();

      Map<Character, Integer> window = new HashMap<>();
      int left = 0, right = 0;
      int valid = 0;
      while (right < s.length()) {

        char ch = s.charAt(right++);
        // 扩容
        if (Objects.equals(
            window.compute(ch, (k, v) -> v == null ? 1 : v + 1), targets.getOrDefault(ch, -1))) {
          if (targets.containsKey(ch)) {
            valid++;
          }
        }

        while (left < right && valid == targets.size()) {
          char chh = s.charAt(left);
          if (targets.size() == window.size() && (right - left) == p.length()) {
            ans.add(left);
          }

          if (window.compute(chh, (k, v) -> v - 1) < targets.getOrDefault(chh, -1)) {
            if (targets.containsKey(chh)) {
              valid--;
            }
          }
          if (window.get(chh) == 0) {
            window.remove(chh);
          }

          left++;
        }
      }

      return ans;
    }
  }

  public static void main(String[] args) {
    Solution_01 solution_01 = new Solution_01();
    System.out.println(solution_01.findAnagrams("abab", "ab"));
  }
}
