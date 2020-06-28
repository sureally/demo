package com.coding.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author shu wj
 * @Date 2020/6/28 23:27
 * @Description
 */
public class Leetcode198 {
  public static class Solution_01 {
    private Map<Integer, Integer> mem = new HashMap<>();
    public int rob(int[] nums) {
      if (nums == null) return 0;
      return dp(nums, nums.length);
    }

    private int dp(int[] nums, int n) {
      if (n <= 0) return 0;
      Integer tmp = mem.get(n);
      if (null != tmp) return tmp;
      int res = Math.max(dp(nums, n - 1), dp(nums, n - 2) + nums[n - 1]);
      mem.put(n, res);
      return res;
    }
  }

  public static class Solution_02 {
    public int rob(int[] nums) {
      if (nums == null || nums.length == 0) return 0;
      int n = nums.length;
      if (n == 1) return nums[0];
      int dp_0 = 0, dp_1 = 0;
      for (int i = n - 1; i >= 0; i--) {
        int dp_i = Math.max(dp_1 , dp_0 + nums[i]);
        dp_0 = dp_1;
        dp_1 = dp_i;
      }

      return dp_1;
    }
  }
}
