package com.coding.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.junit.jupiter.api.Test;

/**
 * @Author shu wj
 * @Date 2019/12/3 23:32
 * @Description
 */
public class CombinationIterator {

  // abcd   abc  顺序是 其索引位置决定的 Cm,n = Am,n/m! = n!/(m! * (n-m)!)
  // (abc, 2) => 一共有多少种可能 C3,2 = 3!/2! = 3
  // (abcd, 2) => C4,2 = 4! / 2! / 2 = 6 // n = 4, m = 2
  // ab ac ad   => combination = [0,1] [0,2] [0,3] => 1, 2, 3 n - m + 1 - 0 = 3
  // bc bd                       [1,2] [1,3]       => 3 4     n - m + 1 - 1 = 2
  // cd                          [2,3]             => 5       n - m + 1 - 2 = 1
  // ahijp
  private long max = 1;
  private long nowIndex = 0;
  private int[] nowPos;
  private char[] characters;
  public CombinationIterator(String characters, int combinationLength) {
      int len = characters.length();
      for (int i = combinationLength + 1; i <= len; ++i) {
        max = max * i;
      }
      for (int i = 1; i <= len - combinationLength; ++i) {
        max = max / i;
      }

      nowPos = new int[combinationLength];
      for (int i = 0; i < combinationLength; ++i) {
        // 初始位置
        nowPos[i] = i;
      }
      this.characters = characters.toCharArray();
  }

  public String next() {
    nowIndex++;
    StringBuilder sb = new StringBuilder();
    for (int index : nowPos) {
      sb.append(characters[index]);
    }
    // 0 1

    return sb.toString();
  }
  // 相当于 n-1 进值一般,同时每一位上的最小值不是0，而是相比于前一位更大一位的数
  private void getNextPos() {
    for (int i = nowPos.length - 1; i >= 0; --i) {
      if (nowPos[i] < i) {
        nowPos[i]++;
        return;
      } else {
        while (i - 1 >= 0 && nowPos[i-1] < i - 1) {
          nowPos[i-1]++;
        }
      }
    }
  }

  public boolean hasNext() {
    return nowIndex < max;
  }

  public static void main(String[] args) {
    CombinationIterator iterator = new CombinationIterator("abcde", 3);
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }

  }
}
