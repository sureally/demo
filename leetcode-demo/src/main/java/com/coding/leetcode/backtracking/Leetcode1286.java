package com.coding.leetcode.backtracking;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author shu wj
 * @Date 2019/12/28 00:09
 * @Description 使用bigmap，不使用递归，o(1). content 想到了方法，但是，笨人没有做出来。
 */
public class Leetcode1286 {
  class Solution01 {
    Queue<String> queue = new LinkedList();
    String origin = "";

    public Solution01(String characters, int combinationLength) {
      origin = characters;
      find("", 0, combinationLength);
    }

    /**
     * @param str
     * @param index 记录，拼接的字符位置的索引
     * @param len   子串的长度
     */
    private void find(String str, int index, int len) {
      if (0 == len) {
        queue.add(str);
        return;
      }
      for (int i = 0; i < origin.length(); i++) {
        char ch = origin.charAt(i);
        find(str + ch, i + 1, len - 1);
      }
    }

    public String next() {
      if (!queue.isEmpty()) {
        return queue.poll();
      }
      return "";
    }

    public boolean hasNext() {
      return !queue.isEmpty();
    }
  }

  class Solution02 {
    // 原字符串取反
    String originReverse;
    int encode;
    int len;

    public Solution02(String characters, int combinationLength) {
      //
      originReverse = new StringBuffer(characters).reverse().toString();
      encode = (1 << characters.length()) - 1;
      len = combinationLength;
    }

    public String next() {
      desc();
      String res = "";
      for (int i = 0; i < originReverse.length(); ++i) {
        // 判断 encode 的二进制编码的从右往左数第 i 位 是否是 1
        if (((encode & (1 << i)) >> i) == 1) {
          res = originReverse.charAt(i) + res;
        }
      }
      encode--;
      return res;
    }

    public boolean hasNext() {
      desc();
      return encode >= 0;
    }

    private void desc() {
      // encode 大于等于0，同时 encode 的 1 的个数不等于len。
      // 目的是 找到下一个encode
      while (encode >= 0 && Integer.bitCount(encode) != len) {
        encode--;
      }
    }
  }

  public static void main(String[] args) {
    Solution02 solution = new Leetcode1286().new Solution02("abcd", 2);
    while (solution.hasNext()) {
      System.out.println(solution.next());
    }
  }
}
