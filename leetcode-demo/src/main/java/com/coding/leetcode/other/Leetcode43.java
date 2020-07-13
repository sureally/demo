package com.coding.leetcode.other;

/** @Author shu wj @Date 2020/7/8 23:16 @Description */
public class Leetcode43 {
  public static class Solution_01 {
    public String multiply(String num1, String num2) {
      if (null == num1 || null == num2) return null;
      if (num1.length() == 0 || num2.length() == 0) return "";

      int n = num1.length();
      int m = num2.length();

      int[] arr = new int[n + m];

      for (int i = n - 1; i >= 0; i--) {
        for (int j = m - 1; j >= 0; j--) {
          System.out.println("j = " + num2.charAt(j) + ", i = " + num1.charAt(i));
          int out = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
          int first = out / 10;
          int second = out % 10;
          addOne(arr, (n - 1 - i) + (m - 1 - j), second);
          addOne(arr, (n - 1 - i) + (m - 1 - j) + 1, first);
        }
      }

      StringBuilder sb = new StringBuilder();
      int i = n + m;
      while (i >= 0 && arr[--i] == 0) {}
      while (i >= 0) {
        sb.append(arr[i--]);
      }

      return sb.toString();
    }

    private void addOne(int[] arr, int index, int add) {
      arr[index] += add;
      if (arr[index] >= 10) {
        arr[index] = arr[index] % 10;
        arr[index + 1] += 1;

        if (arr[index + 1] >= 10) {
          arr[index + 1] = arr[index + 1] % 10;
          arr[index + 2] += 1;
        }
      }

    }
  }

  public static void main(String[] args) {
    Solution_01 solution_01 = new Solution_01();
    System.out.println(solution_01.multiply("123", "456"));
  }
}
