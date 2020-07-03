package com.coding.leetcode.binary;

/** @Author shu wj @Date 2020/7/3 00:23 @Description */
public class Leetcode34 {

  public static class Solution_01 {
    public int[] searchRange(int[] nums, int target) {
      int[] ans = {-1, -1};

      // 先找 left
      int lo = 0, hi = nums.length;
      while (lo < hi) {
        int mid = lo + (hi - lo) / 2;
        if (nums[mid] == target) {
          hi = mid;
        } else if (nums[mid] > target) {
          // 缩小
          hi = mid;
        } else if (nums[mid] < target) {
          // 一定在右边
          lo = mid + 1;
        }
      }
      if (lo < nums.length && nums[lo] == target) {
        ans[0] = lo;
      }

      lo = 0;
      hi = nums.length;
      // 找 right
      while (lo < hi) {
        int mid = lo + (hi - lo) / 2;
        if (nums[mid] == target) {
          lo = mid + 1;
        } else if (nums[mid] > target) {
          hi = mid;
        } else if (nums[mid] < target) {
          lo = mid + 1;
        }
      }
      if (lo - 1 >= 0 && nums[lo - 1] == target) {
        ans[1] = lo - 1;
      }

      return ans;
    }
  }
}
