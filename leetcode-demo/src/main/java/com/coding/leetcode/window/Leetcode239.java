package com.coding.leetcode.window;

import java.util.Arrays;
import java.util.LinkedList;

/** @Author shu wj @Date 2020/6/29 20:34 @Description */
public class Leetcode239 {
  // 使用单调队列。
  // 要保证 时间复杂度为 O(N) 那么查找窗口的max值的复杂度需要为 O(1)
  public static class Solution_01 {

    private LinkedList<Integer> stack = new LinkedList<>();

    public int[] maxSlidingWindow(int[] nums, int k) {
      if (null == nums) return null;
      for (int i = 0; i < k; i++) {
        push(nums[i]);
      }
      int[] res = new int[nums.length - k + 1];

      for (int i = k; i <= nums.length; i++) {
        res[i - k] = max();
        if (i < nums.length) {
          push(nums[i]);
        }
        removeFirst(nums[i - k]);
      }
      return res;
    }

    private void removeFirst(int val) {
      if (stack.getFirst() == val) {
        stack.removeFirst();
      }
    }

    private int max() {
      return stack.getFirst();
    }

    private void push(int val) {
      while (!stack.isEmpty() && stack.getLast() < val) {
        stack.removeLast();
      }
      stack.addLast(val);
    }
  }

  public static void main(String[] args) {
    Solution_01 solution_01 = new Solution_01();
    int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
    int k = 3;
    System.out.println(Arrays.toString(solution_01.maxSlidingWindow(nums, k)));
  }
}
