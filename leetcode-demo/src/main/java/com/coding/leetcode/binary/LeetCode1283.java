package com.coding.leetcode.binary;

/**
 * @Author shu wj
 * @Date 2019/12/8 11:47
 * @Description binary search
 */
public class LeetCode1283 {
  public int smallestDivisor(int[] nums, int threshold) {
    // first idea is 二分法

    // 目标是求得最小的num，满足 divisionSum(nums, num) <= threshold
    // 那么 一定没有比num更小的数使得 divisionSum(nums, num) = threshold
    // 所以 当 divisionSum(i) == threshold 时，需要小心判断 i 是不是目标点
    // 同时考虑到 lo < hi, 所以
    int lo = 1;
    int hi = 1000_001;
    while (lo < hi) {
      int mid =  (hi - lo) / 2 + lo; // 偏小
      if (divisionSum(nums, mid) > threshold) {
        // 说明mid，太小
        lo = mid + 1;
      } else {
        hi = mid ;
      }
    }

    return lo;
  }

  private int divisionSum(int[] nums, int divisor) {
    int division = 0;
    for (int num : nums) {
      division += (num + divisor - 1) / divisor;
    }
    return division;
  }
}
