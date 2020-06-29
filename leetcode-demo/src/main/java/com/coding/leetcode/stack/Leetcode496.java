package com.coding.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author shu wj
 * @Date 2020/6/29 18:25
 * @Description
 */
public class Leetcode496 {
  public static class Solution_01 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
      if (nums1 == null || nums2 == null) return null;
      Map<Integer, Integer> mem = new HashMap<>();
      Deque<Integer> stack = new ArrayDeque<>();
      for (int i = nums2.length - 1; i >= 0; i--) {
        while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
          stack.poll();
        }
        mem.put(nums2[i], stack.isEmpty() ? -1 : stack.peek());
        stack.push(nums2[i]);
      }
      int[] res = new int[nums1.length];

      for (int i = 0; i < nums1.length; i++) {
        res[i] = mem.get(nums1[i]);
      }

      return res;

    }
  }
}
