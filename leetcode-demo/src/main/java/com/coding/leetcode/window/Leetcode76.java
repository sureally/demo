package com.coding.leetcode.window;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author shu wj
 * @Date 2020/7/4 13:25
 * @Description
 */
public class Leetcode76 {

  public static class Solution_01 {
    public String minWindow(String s, String t) {
      String ans = "";
      if (s == null || t == null || "".equals(t)) return ans;
      Map<Character, Integer> targets = new HashMap<>();
      for (char ch : t.toCharArray()) {
        targets.compute(ch, (k, v) -> v == null ? 1 : v + 1);
      }
      Map<Character, Integer> window = new HashMap<>();
      int start = 0, len = Integer.MAX_VALUE;
      int valid = 0;
      int right = 0, left = 0;
      while (right < s.length()) {

        if (targets.containsKey(s.charAt(right))) {
          int num = window.compute(s.charAt(right), (k, v) -> v == null ? 1 : v + 1);
          if (num == targets.get(s.charAt(right))) {
            valid++;
          }
        }
        right++;

        // 收缩
        while (valid == targets.size()) {
          if (right - left < len) {
            start = left;
            len = right - left;
          }
          char d = s.charAt(left);
          left++;

          if (targets.containsKey(d)) {
            if (Objects.equals(window.get(d), targets.get(d))) {
              valid--;
            }
            window.compute(d, (k, v) -> v - 1);
          }
        }


      }

      return len == Integer.MAX_VALUE ? "" : s.substring(start, len + start);
    }
  }
}
