package com.coding.leetcode.dp;

/** @Author shu wj @Date 2020/6/23 23:55 @Description */
public class Leetcode416 {
  public static class Solution_01 {
    public boolean canPartition(int[] nums) {
      if (null == nums || nums.length < 2) return false;
      // 是否存在 小于 n 个数之和等于 sum / 2
      int sum = 0;
      for (int num : nums) {
        sum += num;
      }
      if ((sum % 2) != 0) return false;
      sum = sum / 2;
      // 明确状态 和 选择
      // 状态：dp[i][j] = boolean 前i个元素是否存在和为j。

      boolean[][] dp = new boolean[nums.length + 1][sum + 1];
      // base case
      for (int i = 0; i <= nums.length; i++) {
        dp[i][0] = true;
      }

      for (int i = 1; i <= nums.length; i++) {
        for (int j = 1; j <= sum; j++) {
          if (j - nums[i - 1] < 0) {
            dp[i][j] = dp[i - 1][j];
          } else {
            dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
          }
        }
      }

      return dp[nums.length][sum];
    }
  }

  /**
   * 缩减存储状态的空间.
   *
   * 注意到 dp[i][j] 都是通过上一行 dp[i-1][..] 转移过来的，之前的数据都不会再使用了
   * 唯一需要注意的是 j 应该从后往前反向遍历，因为每个物品（或者说数字）只能用一次，以免之前的结果影响其他的结果。
   */
  public static class Solution_02 {
    public boolean canPartition(int[] nums) {
      if (null == nums || nums.length < 2) return false;
      // 是否存在 小于 n 个数之和等于 sum / 2
      int sum = 0;
      for (int num : nums) {
        sum += num;
      }
      if ((sum % 2) != 0) return false;
      sum = sum / 2;

      boolean[] dp = new boolean[sum + 1];
      dp[0] = true;
      int n = nums.length;
      for (int i = 0; i < n; i++) {
        for (int j = sum; j >= 0; j--) {
          if (j - nums[i] >= 0) {
            dp[j] = dp[j] || dp[j - nums[i]];
          }
        }
      }
      return dp[sum];
    }
  }
}
