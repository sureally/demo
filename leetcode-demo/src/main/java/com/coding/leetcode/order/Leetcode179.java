package com.coding.leetcode.order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author shu wj
 * @Date 2020/1/13 00:56
 * @Description
 */
public class Leetcode179 {
  public String largestNumber(int[] nums) {
    String[] asStrs = new String[nums.length];

    for (int i = 0; i < nums.length; i++) {
      asStrs[i] = String.valueOf(nums[i]);
    }

    // 从大到小排序
    Arrays.sort(asStrs, (a, b) -> (b + a).compareTo(a + b));

    // 如果数组中只包含0，则只返回0, 最大的为0，则说明所有都为0
    if (asStrs[0].equals("0")) {
      return "0";
    }

    StringBuilder sb = new StringBuilder();
    for (String numAsStr : asStrs) {
      sb.append(numAsStr);
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    Leetcode179 leetcode179 = new Leetcode179();
    int[] nums = {3, 30, 34, 5, 9};
    // 9534330
    System.out.println(leetcode179.largestNumber(nums));
  }
}
