package com.coding.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/** @Author shu wj @Date 2020/6/29 18:26 @Description */
public class Leetcode503 {
  public int[] nextGreaterElement(int[] nums) {
    if (nums == null) return null;
    int n = nums.length;
    int[] res = new int[n];
    int[] copy = new int[2 * n];
    for (int i = 0; i < n; i++) {
      copy[i] = nums[i];
      copy[i + n] = nums[i];
    }

    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = copy.length - 1; i >= 0; i--) {
      while (!stack.isEmpty() && stack.peek() <= copy[i]) {
        stack.poll();
      }
      if (i < n) {
        res[i] = stack.isEmpty() ? -1 : stack.peek();
      }
      stack.push(copy[i]);
    }

    return res;
  }
}
