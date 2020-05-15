package com.coding.leetcode.dp;

/**
 * @Author shu wj
 * @Date 2020/3/29 21:33
 * @Description
 */
public class LeetCode53 {

  /**
   * 动态规划。基础版.
   * 时间复杂度：O(N)
   * 空间复杂度：O(N)
   * @param nums
   * @return
   */
  public int solution_01(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int ans = 0;

    /**
     * 1. 状态定义
     * dp[i] 表示前 i 个元素的连续子数组的和
     */
    int[] dp = new int[nums.length];

    /**
     * 2. 状态初始化，数组中第一元素的最大和就是第一个元素本身
     */
    dp[0] = nums[0];
    ans = nums[0];

    /**
     * 3. 状态转移
     * 转移方程：dp[i] = max(dp[i - 1], 0) + nums[i]
     * dp 当前元素的值等于前一个元素值和 0 的最大值再加上 nums[i]
     */
    for (int i = 1; i < nums.length; i++) {
      dp[i] = Math.max(dp[i - 1], 0) + nums[i];
      ans = Math.max(ans, dp[i]);
    }

    return ans;
  }

  /**
   * 动态规划。基础版.
   * 时间复杂度：O(N)
   * 空间复杂度：O(1)
   * 优化方向：转移方程 dp[i] = max(dp[i - 1], 0) + nums[i] 可以看出，当前状态的值只取决于前一个状态值，所以可以使用一个变量来代替 dp[i] 和 dp[i - 1]
   * @param nums
   * @return
   */
  public int solution_02(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int ans = 0;

    int curSum = nums[0];
    ans = nums[0];

    for (int i = 1; i < nums.length; i++) {
      curSum = Math.max(curSum, 0) + nums[i];
      ans = Math.max(ans, curSum);
    }

    return ans;
  }


  /**
   * 分治法
   * @param nums
   * @return
   */
  public int solution_03(int[] nums) {
    return -1;
  }
}
