package com.coding.leetcode.hash;

/**
 * @Author shu wj
 * @Date 2019/12/7 21:08
 * @Description EASY
 */
public class LeetCode171 {
  public int titleToNumber(String s) {
    // like twenty-six
    // A -> 26^0 = 1
    // AA -> 26^1 + 26^0 = 27
    // AAA -> 26^2 + 26^1 + 26^0 =
    // B -> 2 * 26^0 = 2
    // BB -> 2 * 26^1 + 2 * 26^0 =
    // ZY -> 26 * 26^1 + 25 * 26^0 = 701

    int result = 0;
    int len = s.length();
    for (int i = 0; i < s.length(); ++i) {
      char ch = s.charAt(i);
      result += (ch - 'A' + 1) * Math.pow(26, len - i - 1);
    }

    return result;
  }
}
