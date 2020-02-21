package com.coding.leetcode.window;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author shu wj
 * @Date 2020/1/31 21:40
 * @Description
 */
public class LeetCode395 {
  /**
   * 解法1：滑动窗口处理
   * @param s
   * @param k
   * @return
   */
  public int solution1(String s, int k) {
    int d = 0;

    // 最多不同的字符26个，所以这个循环相当于是线性的。
    for (int numUniqueTarget = 1; numUniqueTarget <= 26; numUniqueTarget++) {
      d = Math.max(d, longestSubstringWithNUniqueChars(s, k, numUniqueTarget));
    }

    return d;
  }

  // 滑动窗口的方式寻找，最长子串，其最低出现次数大于等于k，且包含numUniqueTarget个
  private int longestSubstringWithNUniqueChars(String s, int k, int numUniqueTarget) {
    int[] map = new int[128];
    int numUnique = 0; // counter 1
    int numNoLessThanK = 0; // counter 2
    int begin = 0, end = 0;
    int d = 0;

    while (end < s.length()) {
      // 该字符增加一个，如果是第一次出现那么 numUnique增加一个
      if (map[s.charAt(end)]++ == 0) {
        numUnique++; // increment map[c] after this statement
      }
      // 如果该字符等于目标k，此时 numNoLessThanK增加一个
      // end 往后移动一位
      if (map[s.charAt(end++)] == k) {
        numNoLessThanK++; // inc end after this statement
      }
      // 不同的字符数量大于了设定的数量，则降低
      while (numUnique > numUniqueTarget) {

        if (map[s.charAt(begin)]-- == k) {
          numNoLessThanK--; // decrement map[c] after this statement
        }
        if (map[s.charAt(begin++)] == 0) {
          numUnique--; // inc begin after this statement
        }
      }

      // if we found a string where the number of unique chars equals our target
      // and all those chars are repeated at least K times then update max
      if (numUnique == numUniqueTarget && numUnique == numNoLessThanK)
        d = Math.max(end - begin, d);
    }

    return d;
  }

  /**
   * 解法2：分治递归
   * @param s
   * @param k
   * @return
   */
  public int solution2(String s, int k) {
    return helper(s, k, 0, s.length() - 1);
  }

  // 递归分治的解法
  private int helper(String s, int k, int lo, int hi){
    Map<Character, Integer> charNumbers = new HashMap<>(26);
    Set<Character> lessK = new HashSet<>();
    for (int i = lo; i <= hi; i++){
      char ch = s.charAt(i);
      charNumbers.merge(ch, 1, Integer::sum);
    }

    charNumbers.forEach((ch, n) -> {
      if (n < k) {
        lessK.add(ch);
      }
    });

    // 思路应该拆分，分治。拆分的理由是：出现次数小于k次的字符，说明需要的字串是一定不能包含它们的，那么就拆分它们
    for (char ch : lessK){
      // 实现的逻辑是：如果ch='b',  addadabbchhchc --拆分--> aaa 和 ccc
      int midLeft = s.indexOf(ch, lo); // 说明 midLeft 一定是在[lo, hi] 之间的
      int midRight = midLeft;
      // 移到 s 中 ch 在 lo 及其右边第一次出现的连续的最右边的右边一个
      while (midRight <= hi && s.charAt(midLeft) == s.charAt(midRight)) {
        midRight++;
      }
      return Math.max(helper(s, k, lo, midLeft - 1), helper(s, k, midRight, hi));
    }
    return hi - lo + 1;
  }

  public static void main(String[] args) {
    LeetCode395 leetCode395 = new LeetCode395();

    System.out.println(leetCode395.solution1("ababcc", 2));
  }
}
