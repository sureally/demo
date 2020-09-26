package com.coding.leetcode;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** @Author shu wj @Date 2020/6/14 22:02 @Description */
public class Test {

  public static class Solution_01 {

    private Map<Integer, List<String>> mem = new HashMap<>();

    public int strobogrammaticInRange(String low, String high) {
      if (null == low || high == null) return 0;
      int lo = low.length();
      int hi = high.length();
      BigInteger lowI = new BigInteger(low);
      BigInteger highI = new BigInteger(high);

      int ans = 0;
      for (int i = lo; i <= hi; i++) {
        List<String> cur = helper(i, i);
        mem.put(i, cur);
        if (mem.size() > 2) {
          mem.remove(i - 2);
        }

        if (i == lo || i == hi) {
          for (String s : cur) {
            BigInteger curI = new BigInteger(s);
            if (lowI.compareTo(curI) <= 0 && highI.compareTo(curI) >= 0) {
              ans++;
            }
          }
          continue;
        }
        ans += cur.size();
      }

      return ans;
    }

    // 当前长度为n，目标为m
    private List<String> helper(int n, int m) {
      if (n < 0 || m < 0 || n > m) {
        throw new IllegalArgumentException();
      }

      if (n == 0) {
        return new ArrayList<>(Collections.singletonList(""));
      } else if (n == 1) {
        return new ArrayList<>(Arrays.asList("0", "1", "8"));
      }

      if (mem.containsKey(n)) {
        return mem.get(n);
      }

      List<String> res = helper(n - 2, m);

      List<String> ans = new ArrayList<>();

      for (String str : res) {
        if (n != m) {
          // 0 不能放在首
          ans.add("0" + str + "0");
        }
        ans.add("1" + str + "1");
        ans.add("8" + str + "8");
        ans.add("6" + str + "9");
        ans.add("9" + str + "6");
      }
      return ans;
    }
  }

  public static void main(String[] args) {}
}
