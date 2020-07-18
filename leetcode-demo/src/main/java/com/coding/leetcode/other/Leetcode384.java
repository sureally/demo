package com.coding.leetcode.other;

import java.util.Arrays;
import java.util.Random;

/**
 * @Author shu wj
 * @Date 2020/7/14 00:48
 * @Description
 */
public class Leetcode384 {
  public Leetcode384(int[] nums) {
    this.nums = Arrays.copyOf(nums, nums.length);
    this.shuffle = Arrays.copyOf(nums, nums.length);
  }

  private Random random = new Random();
  private int[] nums;
  private int[] shuffle;
  /** Resets the array to its original configuration and return it. */
  public int[] reset() {
    return nums;
  }

  // [0, 1, 2, 3, 4]
  /** Returns a random shuffling of the array. */
  public int[] shuffle() {
    for (int i = 0; i < nums.length; i++) {
      int rand = random.nextInt(nums.length - i) + i;
      swap(this.shuffle, i, rand);
    }
    return this.shuffle;
  }

  private void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }
}
