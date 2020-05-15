package com.coding.leetcode.window;

import java.util.HashMap;
import java.util.Map;

import javafx.util.Pair;

/**
 * @Author shu wj
 * @Date 2020/4/5 22:00
 * @Description
 */
public class Leetcode72 {
  /**
   * 在滑动窗口类型的问题中都会有两个指针。一个用于延伸现有窗口的 rightright指针，和一个用于收缩窗口的leftleft 指针。
   * 在任意时刻，只有一个指针运动，而另一个保持静止。
   *
   * 时间复杂度：O(|S| + |T|)
   * 空间复杂度：O(|S| + |T|)
   * @param s
   * @param t
   * @return
   */
  public static String solution_01(String s, String t) {
    if (s.length() == 0 || t.length() == 0) {
      return "";
    }

    Map<Character, Integer> distT = new HashMap<>();

    for (int i = 0; i < t.length(); i++) {
      distT.merge(t.charAt(i), 1, Integer::sum);
    }

    int formed = 0;
    int required = distT.size();
    int lo = 0, hi = 0;
    Map<Character, Integer> windowCounts = new HashMap<>();

    int[] ans = {-1, 0, 0};

    while (hi < s.length()) {
      char c = s.charAt(hi);
      windowCounts.merge(c, 1, Integer::sum);

      if (distT.containsKey(c) && windowCounts.get(c).intValue() == distT.get(c).intValue()) {
        formed++;
      }

      while (lo <= hi && formed == required) {
        c = s.charAt(lo);

        if (ans[0] == -1 || hi - lo + 1 < ans[0]) {
          ans[0] = hi - lo + 1;
          ans[1] = lo;
          ans[2] = hi;
        }

        windowCounts.put(c, windowCounts.get(c) - 1);
        if (distT.containsKey(c) && windowCounts.get(c) < distT.get(c)) {
          formed--;
        }

        lo++;
      }

      hi++;
    }

    return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
  }

  public static void main(String[] args) {

  }
}
