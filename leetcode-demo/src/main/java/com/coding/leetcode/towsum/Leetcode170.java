package com.coding.leetcode.towsum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** @Author shu wj @Date 2020/7/6 00:18 @Description */
public class Leetcode170 {
  public static class Solution_01 {
    /**
     * 主要是 性能优化的点
     */
    public static class TwoSum {
      // 优化可以考虑，通过排序 和 二分来加速查找
      private final List<Integer> singleCache;
      private final Set<Integer> pairCache;
      /** Initialize your data structure here. */
      public TwoSum() {
        this.pairCache = new HashSet<>();
        this.singleCache = new ArrayList<>();
      }

      /** Add the number to an internal data structure.. */
      public void add(int number) {
        for (int num : singleCache) {
          pairCache.add(num + number);
        }
        singleCache.add(number);
      }

      /** Find if there exists any pair of numbers which sum is equal to the value. */
      public boolean find(int value) {
        if (pairCache.contains(value)) return true;

        Map<Integer, Integer> cache = new HashMap<>();
        for (int i = 0; i < singleCache.size(); i++) {
          cache.put(singleCache.get(i), i);
        }

        for (int i = 0; i < singleCache.size(); i++) {
          Integer targetIndex = cache.get(value - singleCache.get(i));
          if (targetIndex != null && targetIndex != i) {
            pairCache.add(value);
            return true;
          }
        }

        return false;
      }
    }
  }
}
