package com.coding.leetcode.backtracking;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author shu wj
 * @Date 2019/12/27 23:17
 * @Description 递归搜索，关键是存储O(K), next -> O(1), hasNext -> O(1)
 */
public class CombinationIterator {
  Queue<String> queue = new LinkedList();
  String origin = "";

  public CombinationIterator(String characters, int combinationLength) {
    origin = characters;
    find("", 0, combinationLength);
  }

  /**
   *
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

  public static void main(String[] args) {
    new CombinationIterator("abcd", 2).next();

  }
}
