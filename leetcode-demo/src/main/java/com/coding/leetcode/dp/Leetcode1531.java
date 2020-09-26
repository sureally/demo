package com.coding.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shu wj
 */
public class Leetcode1531 {
  public static class Solution_01 {
    public String minWindow(String s, String t) {
      Map<Character, Integer> target = new HashMap<>();
      for (int i = 0; i < t.length(); i++) {
        target.compute(t.charAt(i), (k, v) -> v == null ? 1 : v + 1);
      }

      // slide window
      Map<Character, Integer> tempMap = new HashMap<>();
      int count = 0;
      int lo = 0, hi = 0;
      int ansLo = -1, len = s.length() + 1;
      while (lo < s.length()) {
        // hi 向右移动，直到符合target
        while (hi < s.length() && count < target.size()) {
          char ch = s.charAt(hi);
          if (target.containsKey(ch)) {
            int v = tempMap.compute(ch, (k, vv) -> vv == null ? 1 : vv + 1);
            if (v == target.get(ch)) {
              count++;
            }
          }

          hi++;
        }

        if (count == target.size() && hi - lo  < len) {
          ansLo = lo;
          len = hi - lo;
        }

        char ch = s.charAt(lo);
        if (target.containsKey(ch)) {
          int v = tempMap.compute(ch, (k, vv) -> vv - 1);
          if (v < target.get(ch)) {
            count--;
          }
        }

        // 然后 lo 再像右移动一位
        lo++;
      }
      String ans = "";
      if (ansLo >= 0) {
        ans = s.substring(ansLo, ansLo + len);
      }

      return ans;
    }
  }

  public static void main(String[] args) {
    Solution_01 solution_01 = new Solution_01();
    System.out.println(solution_01.minWindow("ADOBECODEBANC", "ABC"));
  }
}
