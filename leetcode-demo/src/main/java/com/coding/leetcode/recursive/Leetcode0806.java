package com.coding.leetcode.recursive;

import java.util.List;

/** @Author shu wj @Date 2020/7/18 18:12 @Description */
public class Leetcode0806 {
  /**
   * n = 1 时，直接把盘子从 A 移到 C；
   * n > 1 时，先把上面 n - 1 个盘子从 A 移到 B（子问题，递归）；
   *          再将最大的盘子从 A 移到 C；
   *          再将 B 上 n - 1 个盘子从 B 移到 C（子问题，递归）
   */
    // A(i) = 2 * A(i - 1) + 1
    // 前i-1个先由C移动B，然后 第 i 个移动到C，前 i - 1个再由B到C。

  /**
   * 递归。
   */
  public static class Solution_02 {
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
      if (A == null || B == null || C == null) {
        return;
      }

      move(A.size(), A, B, C);
    }

    public void move(int n, List<Integer> A, List<Integer> B, List<Integer> C) {
      if (n < 1) {
        return;
      }
      // 从A移动到B
      move(n - 1, A, C, B);

      Integer num = A.get(A.size() - 1);
      A.remove(num);
      C.add(num);

      // 从B移动到C
      move(n - 1, B, A, C);
    }
  }

  public static void main(String[] args) {
  }
}
