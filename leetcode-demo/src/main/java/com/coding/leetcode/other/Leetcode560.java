package com.coding.leetcode.other;

import java.util.HashMap;

/** @Author shu wj @Date 2020/7/7 22:46 @Description */
public class Leetcode560 {
  public static class Solution_01 {
    public int subarraySum(int[] nums, int k) {
      if (nums == null || nums.length == 0) return 0;
      int n = nums.length;

      int[] prefixSum = new int[n + 1];
      for (int i = 1; i <= n; i++) {
        prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
      }

      int count = 0;

      for (int i = 0; i <= n; i++) {
        for (int j = i + 1; j <= n; j++) {
          if (k == prefixSum[j] - prefixSum[i]) {
            count++;
          }
        }
      }

      return count;
    }
  }

  // TODO: 直接记录下有几个 sum[j] 和 sum[i] - k 相等，直接更新结果，就避免了内层的 for 循环
  public static class Solution_02 {
    public int subarraySum(int[] nums, int k) {
      if (nums == null || nums.length == 0) return 0;
      // map：前缀和 -> 该前缀和出现的次数
      HashMap<Integer, Integer> preSum = new HashMap<>();
      // base case
      preSum.put(0, 1);

      int ans = 0, sum0_i = 0;
      for (int num : nums) {
        sum0_i += num;
        // 这是我们想找的前缀和 nums[0..j]
        int sum0_j = sum0_i - k;
        // 如果前面有这个前缀和，则直接更新答案
        if (preSum.containsKey(sum0_j)) ans += preSum.get(sum0_j);
        // 把前缀和 nums[0..i] 加入并记录出现次数
        preSum.compute(sum0_i, (kk, v) -> v == null ? 1 : v + 1);
      }
      return ans;
    }
  }

  public static void main(String[] args) {
    Solution_01 solution_01 = new Solution_01();
    int[] nums = {1};
    int k = 1;
    System.out.println(solution_01.subarraySum(nums, k));
  }
}
