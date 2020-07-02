package com.coding.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/** @Author shu wj @Date 2020/7/1 23:25 @Description */
public class Leetcode78 {
  public static class Solution_01 {
    List<List<Integer>> output = new ArrayList();
    int n, k;

    public void backtrack(int first, ArrayList<Integer> curr, int[] nums) {
      if (curr.size() == k) output.add(new ArrayList(curr));

      for (int i = first; i < n; ++i) {
        curr.add(nums[i]);
        backtrack(i + 1, curr, nums);
        curr.remove(curr.size() - 1);
      }
    }

    public List<List<Integer>> subsets(int[] nums) {
      n = nums.length;
      for (k = 0; k < n + 1; ++k) {
        backtrack(0, new ArrayList<Integer>(), nums);
      }
      return output;
    }
  }

  // 类似于数学归纳法
  public static class Solution_02 {
    public List<List<Integer>> subsets(int[] nums) {
      List<List<Integer>> output = new ArrayList();
      output.add(new ArrayList<Integer>());

      for (int num : nums) {
        List<List<Integer>> newSubsets = new ArrayList();
        for (List<Integer> curr : output) {
          newSubsets.add(
              new ArrayList<Integer>(curr) {
                {
                  add(num);
                }
              });
        }
        for (List<Integer> curr : newSubsets) {
          output.add(curr);
        }
      }
      return output;
    }
  }
}
