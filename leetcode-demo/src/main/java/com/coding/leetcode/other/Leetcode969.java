package com.coding.leetcode.other;

import java.util.LinkedList;
import java.util.List;

/** @Author shu wj @Date 2020/7/7 22:46 @Description */
public class Leetcode969 {
  /**
   * 如何思考到 使用递归算法！！！
   */
  public static class Solution_01 {
    // 记录反转操作序列
    LinkedList<Integer> res = new LinkedList<>();

    List<Integer> pancakeSort(int[] cakes) {
      sort(cakes, cakes.length);
      return res;
    }

    void sort(int[] cakes, int n) {
      // base case
      if (n == 1) return;

      // 寻找最大饼的索引
      int maxCake = 0;
      int maxCakeIndex = 0;
      for (int i = 0; i < n; i++)
        if (cakes[i] > maxCake) {
          maxCakeIndex = i;
          maxCake = cakes[i];
        }

      // 第一次翻转，将最大饼翻到最上面
      reverse(cakes, 0, maxCakeIndex);
      res.add(maxCakeIndex + 1);
      // 第二次翻转，将最大饼翻到最下面
      reverse(cakes, 0, n - 1);
      res.add(n);

      // 递归调用
      sort(cakes, n - 1);
    }

    void reverse(int[] arr, int i, int j) {
      while (i < j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        i++;
        j--;
      }
    }
  }
}
