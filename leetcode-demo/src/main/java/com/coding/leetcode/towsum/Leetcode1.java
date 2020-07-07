package com.coding.leetcode.towsum;

import java.util.HashMap;
import java.util.Map;

/** @Author shu wj @Date 2020/7/6 00:18 @Description */
public class Leetcode1 {
  public int[] twoSum(int[] nums, int target) {
    int n = nums.length;
    Map<Integer, Integer> index = new HashMap<>();
    // 构造一个哈希表：元素映射到相应的索引
    for (int i = 0; i < n; i++) {
      index.put(nums[i], i);
    }

    for (int i = 0; i < n; i++) {
      int other = target - nums[i];
      // 如果 other 存在且不是 nums[i] 本身
      if (index.containsKey(other) && index.get(other) != i) return new int[] {i, index.get(other)};
    }

    return new int[] {-1, -1};
  }
}
