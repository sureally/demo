package com.coding.leetcode.binary;

/** @Author shu wj @Date 2020/7/3 00:23 @Description */
public class Leetcode704 {
  public static class Solution_01 {
    public int search(int[] nums, int target) {
      int lo = 0, hi = nums.length - 1;

      while (lo <= hi) {
        int mid = lo + (hi - lo) / 2;
        if (nums[mid] == target) {
          return mid;
        } else if (nums[mid] < target) {
          lo = mid + 1;
        } else {
          hi = mid - 1;
        }
      }

      return -1;
    }
  }
}
