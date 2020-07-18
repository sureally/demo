package com.coding.leetcode.recursive;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** @Author shu wj @Date 2020/7/17 22:30 @Description */
public class Leetcode248 {
  public static class Solution_01 {
    private Map<Integer, List<String>> mem = new HashMap<>();

    public int strobogrammaticInRange(String low, String high) {
      // 找到
      int ans = 0;
      int n = low.length();
      int m = high.length();
      BigInteger lowI = new BigInteger(low);
      BigInteger highI = new BigInteger(high);
      for (int i = n; i <= m; i++) {
        List<String> res = helper(i, i);
        if (mem.size() >= 2) {
          mem.put(i, res);
          mem.remove(i - 2);
        }
        if (i == n || i == m) {
          for (String str : res) {
            BigInteger t = new BigInteger(str);
            if (lowI.compareTo(t) <= 0 && highI.compareTo(t) >= 0) {
              ans++;
            }
          }
          continue;
        }

        ans += res.size();
      }

      return ans;
    }

    private List<String> helper(int n, int m) {
      if (n < 0 || m < 0 || n > m) throw new IllegalArgumentException();

      if (n == 0) return new ArrayList<>(Arrays.asList(""));
      else if (n == 1) return new ArrayList<>(Arrays.asList("0", "1", "8"));

      if (mem.containsKey(n)) return mem.get(n);

      // 缩小规模
      List<String> res = helper(n - 2, m);

      List<String> ans = new ArrayList<>();
      for (String str : res) {
        if (n != m) {
          ans.add("0" + str + "0");
        }
        ans.add("1" + str + "1");
        ans.add("6" + str + "9");
        ans.add("8" + str + "8");
        ans.add("9" + str + "6");
      }

      return ans;
    }
  }

  public static class Solution_02 {
    private static final char[][] PAIRS =
        new char[][] {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};

    public int strobogrammaticInRange(String low, String high) {
      if (low == null
          || high == null
          || low.length() > high.length()
          || (low.length() == high.length() && low.compareTo(high) > 0)) {
        return 0;
      }
      int count = 0;
      for (int len = low.length(); len <= high.length(); len++) {
        count += dfs(low, high, new char[len], 0, len - 1);
      }
      return count;
    }

    private int dfs(String low, String high, char[] current, int left, int right) {
      if (left > right) {
        String s = new String(current);
        if ((s.length() == low.length() && s.compareTo(low) < 0)
            || (s.length() == high.length() && s.compareTo(high) > 0)) {
          return 0;
        } else {
          return 1;
        }
      }

      int count = 0;
      for (char[] p : PAIRS) {
        // 每次把pair拿出来去放到current头尾然后继续dfs

        if (left == right && (p[0] == '6' || p[0] == '9')) {
          continue; // don't put 6/9 at the middle of string.
        }

        current[left] = p[0];
        current[right] = p[1];

        // 注意这里是current[0] 不是p[0], 发生在刚进loop拿出来了{0, 0}
        // 比如说放到了current[0], current[3]的位置，就不行了，得continue去拿下次{1,1}
        if (current.length != 1 && current[0] == '0') {
          continue; // don't start with 0
        }

        count += dfs(low, high, current, left + 1, right - 1);
      }
      return count;
    }
  }

  public static void main(String[] args) {
    Solution_01 solution_01 = new Solution_01();
    System.out.println(solution_01.strobogrammaticInRange("0", "0"));

    System.out.println(solution_01.strobogrammaticInRange("50", "100"));
  }
}
