package com.coding.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/** @Author shu wj @Date 2020/7/1 22:44 @Description */
public class Leetcode46 {
  public static class Solution_01 {
    List<List<Integer>> res = new LinkedList<>();

    /* 主函数，输入一组不重复的数字，返回它们的全排列 */
    List<List<Integer>> permute(int[] nums) {
      // 记录「路径」
      LinkedList<Integer> track = new LinkedList<>();
      backtrack(nums, track);
      return res;
    }

    // 路径：记录在 track 中
    // 选择列表：nums 中不存在于 track 的那些元素
    // 结束条件：nums 中的元素全都在 track 中出现
    void backtrack(int[] nums, LinkedList<Integer> track) {
      // 触发结束条件
      if (track.size() == nums.length) {
        res.add(new LinkedList<>(track));
        return;
      }

      for (int num : nums) {
        // 排除不合法的选择
        if (track.contains(num)) continue;
        // 做选择
        track.add(num);
        // 进入下一层决策树
        backtrack(nums, track);
        // 取消选择
        track.removeLast();
      }
    }
  }

  public static class Solution_02 {
    public List<List<Integer>> permute(int[] nums) {
      List<List<Integer>> resultList = new ArrayList<>();
      List<Integer> list = new ArrayList<>();
      for (int num : nums) {
        list.add(num);
      }
      backtrack(0, nums, list, resultList);
      return resultList;
    }

    private void backtrack(int level, int[] nums, List<Integer> list, List<List<Integer>> result) {
      if (level == nums.length) {
        result.add(new ArrayList<>(list));
        return;
      }

      for (int i = level; i < nums.length; i++) {
        Collections.swap(list, i, level);
        backtrack(level + 1, nums, list, result);
        Collections.swap(list, i, level);
      }
    }
  }
}
