package com.coding.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Author shu wj
 * @Date 2020/1/13 00:16
 * @Description
 */
public class Test {
  public static void main(String[] args) {
    List<String> list = new ArrayList<>(16);
    list.add("a");
    list.add("c");
    list.add("b");
    list.add(null);
    list.sort(Comparator.reverseOrder());

    System.out.println(list);
  }
}
