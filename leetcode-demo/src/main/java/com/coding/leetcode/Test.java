package com.coding.leetcode;

import java.util.HashMap;
import java.util.Map;

import com.coding.leetcode.tree.TreeNode;

/** @Author shu wj @Date 2020/6/14 22:02 @Description */
public class Test {

  public static class Solution_01 {
    public int minFlips(String target) {
      int ans = 0;
      // base state
      char[] chars = new char[target.length()];
      char[] targetChars = target.toCharArray();
      int[] dp = new int[target.length() + 1];
      dp[0] = 0;
      // state
      // dp[i] 表示 前 i 个元素 满足正确情况的 最少数

      // 转移方程
      // dp[x] = dp[i] + 1
      for (int i = 1; i <= target.length(); i++) {
        for (int j = 1; j <= target.length(); j++) {

        }
      }

      return ans;
    }

    // 翻转 i + 1
    private int flip(char[] target, char[] chars, int i) {
      int correctNum = 0;
      for (int j = i + 1; j <= chars.length; j++) {
        if (target[j] != chars[j]) {
          correctNum++;
        } else {
          break;
        }
      }
      return correctNum;
    }
  }

  public static class Solution_02 {
    private final Map<TreeNode, Integer> minHeight = new HashMap<>();
    public int countPairs(TreeNode root, int distance) {
      return 0;
    }

    private int helper(TreeNode root, int distance) {
      return 0;
    }
  }

  public static void main(String[] args) {
    Solution_01 solution_01 = new Solution_01();

    String s = "aaiougrt";
    System.out.println(solution_01.minFlips(s));
    ;
  }
}
