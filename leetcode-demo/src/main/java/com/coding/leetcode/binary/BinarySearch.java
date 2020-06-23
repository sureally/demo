package com.coding.leetcode.binary;

/** @Author shu wj @Date 2020/6/22 23:49 @Description */
public class BinarySearch {
  // 二分查找
  public static class Solution_01 {

    public int binarySearch(int[] nums, int target) {
      if (null == nums || nums.length == 0) return -1;
      int left = 0, right = nums.length - 1;

      while (left <= right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) return mid;
        else if (nums[mid] > target) {
          right = mid - 1;
        } else if (nums[mid] < target) {
          left = mid + 1;
        }
      }

      return left;
    }
  }

  // 寻找左边界
  public static class Solution_02 {

    public int binarySearch(int[] nums, int target) {
      if (null == nums || nums.length == 0) return -1;
      int left = 0, right = nums.length;

      // 搜索范围是 [left, right)
      while (left < right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
          // 需要缩小右边界，继续搜索。
          right = mid;
        } else if (nums[mid] > target) {
          right = mid;
        } else if (nums[mid] < target) {
          left = mid + 1;
        }
      }

      return left;

      // target 比所有数都大
      // if (left == nums.length) return -1;
      // return nums[left] == target ? left : -1;

    }
  }

  public static class Solution_03 {

    public int binarySearch(int[] nums, int target) {
      if (null == nums || nums.length == 0) return -1;
      int left = 0, right = nums.length;

      while (left < right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
          left = mid + 1;
        } else if (nums[mid] > target) {
          right = mid;
        } else if (nums[mid] < target) {
          left = mid + 1;
        }
      }

      return left - 1;

      //      if (left == 0) return -1;
      //      return nums[left-1] == target ? (left-1) : -1;
    }
  }

  public static void main(String[] args) {
    Solution_02 solution_02 = new Solution_02();
    Solution_03 solution_03 = new Solution_03();
    int[] nums = {1, 2, 3, 3, 3, 4, 4, 5, 6};
    System.out.println(solution_02.binarySearch(nums, 7));
    System.out.println(solution_03.binarySearch(nums, 7));

  }
}
