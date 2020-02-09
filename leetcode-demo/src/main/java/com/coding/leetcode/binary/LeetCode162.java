package com.coding.leetcode.binary;

/**
 * @Author shu wj
 * @Date 2020/1/31 20:29
 * @Description
 */
public class LeetCode162 {
  public int findPeakElement(int[] nums) {
    int left = 0, right = nums.length - 1;
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] > nums[mid + 1]) {
        // 说明峰值在左边
        right = mid;
      } else {
        /**
         * nums[i] != nums[i + 1]
         * left 不重新设置为mid，是因为 需要从mid mid+1 中间分割，即左部分右极限mid，右部分左极限mid+1
         * 至于为什么不是从 mid-1, mid 中间分割，是因为这里的判断条件是 if (nums[mid] > nums[mid + 1])
         * 同时，需要注意的mid这样计算，如果在偶数个num的情况下，索引值是偏小的。
         */
        left = mid + 1;
      }
    }

    return left;
  }

  public int findPeakElement2(int[] nums) {
    int left = 0, right = nums.length - 1;
    while (left < right) {
      // mid 的选择需要偏大。
      int mid = left + (right - left) / 2 + 1;
      if (mid - 1 >= 0 && nums[mid - 1] > nums[mid]) {
        right = mid - 1;
      } else {
        left = mid;
      }
    }

    return left;
  }

  public static void main(String[] args) {

  }
}
