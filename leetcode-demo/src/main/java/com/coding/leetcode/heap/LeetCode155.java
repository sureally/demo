package com.coding.leetcode.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author shu wj
 * @Date 2019/12/5 23:32
 * @Description
 */
public class LeetCode155 {


}

class MinStack {

  // list 保存？ 记录索引
  // 两个链表来保存数据，一个记录原始值，一个 记录最小值
  private List<Integer> data;
  private List<Integer> minData;

  /** initialize your data structure here. */
  public MinStack() {
    data = new ArrayList<>();
    minData = new ArrayList<>();
  }

  public void push(int x) {
    if (data.size() <= 0) {
      minData.add(x);
    }
    data.add(x);
    minData.add(Math.min(x, getMin()));
  }

  public void pop() {
    if (data.size() <= 0) {
      throw new RuntimeException();
    }
    data.remove(data.size() - 1);
    minData.remove(minData.size() - 1);
  }

  public int top() {
    if (data.size() <= 0) {
      throw new RuntimeException();
    }
    return data.get(data.size() - 1);
  }

  public int getMin() {
    if (minData.size() <= 0) {
      throw new RuntimeException();
    }
    return minData.get(minData.size() - 1);
  }
}
