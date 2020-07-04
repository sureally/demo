package com.coding.leetcode.list;

/** @Author shu wj @Date 2020/7/3 22:45 @Description */
public class Leetcode167 {
  public static class Solution_01 {
    public int[] twoSum(int[] numbers, int target) {
      int[] ans = {-1, -1};

      for (int i = 0; i < numbers.length; i++) {
        int leave = target - numbers[i];

        int lo = i + 1, hi = numbers.length - 1;
        while (lo <= hi) {
          int mid = lo + (hi - lo) / 2;
          if (numbers[mid] == leave) {
            ans[1] = mid + 1;
            break;
          } else if (numbers[mid] > leave) {
            hi = mid - 1;
          } else {
            lo = mid + 1;
          }
        }

        if (ans[1] != -1) {
          ans[0] = i + 1;
          return ans;
        }
      }
      return ans;
    }
  }
}
