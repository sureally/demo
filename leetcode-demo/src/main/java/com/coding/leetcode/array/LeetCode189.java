package com.coding.leetcode.array;

import java.util.Arrays;
import java.util.List;

/**
 * @Author shu wj
 * @Date 2019/12/2 23:57
 * @Description Leetcode 189 旋转数组
 */
public class LeetCode189 {

  public void rotate(int[] nums, int k) {

  }

  /**
   * 解法2 优化
   * @param nums
   * @param k
   */
  private void solution02_01(int[] nums, int k) {
    if (null == nums || nums.length < 2) {
      return;
    }
    int len = nums.length;
    k = k % len;

    // 0 1 2 3 4 5
    // 0 1 0 3 4 5 nextIndex = 2; nextVal = 2
    // 0 1 0 3 2 5 nextIndex = 4; nextVal = 4 ->
    // 4 1 0 3 2 5 nextIndex = 0 = initIndex -> initIndex = 1; curVal = 1
    int initIndex = 0;
    int curVal = nums[0];
    int nextIndex = 0;
    // 需要移动len次, k == 0 会出错
    for (int i = 0; i < len && k > 0; ++i) {
      nextIndex = (nextIndex + k) % len;
      int nextVal = nums[nextIndex];
      nums[nextIndex] = curVal; // 下一位置被当前位置的所占领

      // 特殊情况，正好移到了自己应该到的位置
      if (initIndex == nextIndex) {
        initIndex++;
        curVal = nums[initIndex];
        nextIndex = initIndex;
        continue;
      }
      // 正常情况
      curVal = nextVal;
    }
  }

  /**
   * 解法2：一个个的跳动。A移到B位置，原来B位置的需要移到C，这样一步步移动。但是，这里存在一种特殊情况就是，循环一圈后，A到达的B位置，就是
   * A应该去的B位置。
   * @param nums
   * @param k
   */
  private void solution02(int[] nums, int k) {
    if (null == nums || nums.length < 2) {
      return;
    }
    int len = nums.length;
    k = k % len;

    // 0 1 2 3 4 5
    // 0 1 0 3 4 5 nextIndex = 2; nextVal = 2 -> curIndex = 2; curVal = 2
    // 0 1 0 3 2 5 nextIndex = 4; nextVal = 4 -> initIndex = (4 + 2) % 6 = 0 -> initIndex = 1; curIndex = 1; curVal = 1
    int curIndex = 0;
    int curVal = nums[curIndex];
    int initIndex = curIndex;
    // 需要移动len次
    for (int i = 0; i < len; ++i) {
      int nextIndex = (curIndex + k) % len;
      int nextVal = nums[nextIndex];
      nums[nextIndex] = curVal; // 下一位置被当前位置的所占领

      // 特殊情况，正好移到了自己应该到的位置
      if (initIndex == (nextIndex + k) % len) {
        nums[initIndex] = nextVal;
        initIndex++;
        curIndex = initIndex;
        curVal = nums[curIndex];
        i++; // 因为这里增加了一次交换！！！
        continue;
      }
      // 正常情况
      curIndex = nextIndex;
      curVal = nextVal;
    }

  }

  /**
   * 解法1：思路是翻转数组达到旋转移动的目的。
   * @param nums
   * @param k
   */
  private void solution01(int[] nums, int k) {
    if (null == nums || nums.length < 2) {
      return;
    }
    int len = nums.length;
    k = k % len;
    reverse(nums, 0, len - 1);
    reverse(nums, 0, k - 1);
    reverse(nums, k, len - 1);
  }

  // 翻转
  private void reverse(int[] nums, int start, int end) {
    while (start < end) {
      int temp = nums[start];
      nums[start++] = nums[end];
      nums[end--] = temp;
    }
  }


}
