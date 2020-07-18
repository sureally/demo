package com.coding.leetcode.recursive;

/** @Author shu wj @Date 2020/7/18 14:39 @Description */
public class Leetcode698 {
  /** 递归 + 回溯: 比较难分析，如果没有减枝，复杂度为 O(2^N) 次方 */
  public static class Solution_01 {
    public boolean canPartitionKSubsets(int[] nums, int k) {
      // 总和 和 最大值 判断，O(N)
      int maxNum = Integer.MIN_VALUE;
      int sumNum = 0;
      for (int num : nums) {
        sumNum += num;
        maxNum = Math.max(maxNum, num);
      }
      if (sumNum % k != 0 || maxNum > (sumNum / k)) return false;

      return backtracking(nums, k, sumNum / k, 0, 0, new boolean[nums.length]);
    }

    // 组合
    private boolean backtracking(
        int[] nums, int k, int target, int cur, int start, boolean[] used) {
      if (k == 0) return true;

      if (target == cur) {
        return backtracking(nums, k - 1, target, 0, 0, used);
      }

      for (int i = start; i < nums.length; i++) {
        if (!used[i] && cur + nums[i] <= target) {
          used[i] = true;
          if (backtracking(nums, k, target, cur + nums[i], i + 1, used)) return true;
          used[i] = false;
        }
      }
      return false;
    }
  }

  public static void main(String[] args) {
    Solution_01 solution_01 = new Solution_01();
    System.out.println(solution_01.canPartitionKSubsets(new int[] {4, 3, 2, 3, 5, 2, 1}, 4));
  }
}
