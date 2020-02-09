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
        // nums[i] != nums[i + 1]
        // left 不重新设置为mid，是因为 需要从mid mid+1 中间分割，即左部分右极限mid，右部分左极限mid+1
        left = mid + 1;
      }
    }

    return left;
  }

  public static void main(String[] args) {

  }
}
