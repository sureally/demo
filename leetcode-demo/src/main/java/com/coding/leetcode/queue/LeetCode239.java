package com.coding.leetcode.queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * @Author shu wj
 * @Date 2019/12/7 21:18
 * @Description HARD
 */
public class LeetCode239 {
  public int[] maxSlidingWindow(int[] nums, int k) {
    return null;
  }

  // 使用 分段解决, 空间是O(N),时间复杂度也是O(N)。但是，由于数组是顺序访问的缘故，所以其实速度是比solution01快的。
  public int[] solution02(int[] nums, int k) {
    if (null == nums || nums.length == 0) {
      return new int[0];
    }
    int len = nums.length;
    int[] result = new int[len - k + 1];
    int[] leftMax = new int[len], rightMax = new int[len];
    // 1,3,-1, -3,5,3,  6,7
    // 0 1 2    3 4 5   6 7
    // k - len % k + len - i

    rightMax[len - 1] = nums[len - 1];
    for (int i = 1; i < len; ++i) {
      leftMax[i] = (i - 1) % k == 0 ? nums[i] : Math.max(leftMax[i-1], nums[i]);
      rightMax[len - i - 1] = (len - i - 1) % k == 0 ? nums[len - i - 1] : Math.max(rightMax[len - i], nums[len - i - 1]);
    }

    for (int i = 0; i < len - k + 1; i++) {
      result[i] = Math.max(rightMax[i], leftMax[i + k - 1]);
    }
    return result;
  }

  // 使用双端队列
  public int[] solution01(int[] nums, int k) {
    if (null == nums || nums.length == 0) {
      return new int[0];
    }
    int len = nums.length;
    int[] result = new int[len - k + 1];
    // contain the index but not number
    Deque<Integer> deque = new ArrayDeque<>();
    // 1,3,-1,-3,5,3,6,7
    // 在deque中: (first) 1
    // deque:    (first) 1,3 (last)
    // deque:    (first) 3 (last)
    // deque:    (first) 3, -1 (last)
    // deque:    (first) 3, -1, -3 (last)
    int j = 0;
    for (int i = 0; i < len; i++) {
      // remove numbers out of range k, [i-k-1, i]
      while (!deque.isEmpty() && deque.peek() < i - k + 1) {
        deque.poll();
      }
      // remove smaller numbers in k range as they are useless
      while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
        deque.pollLast();
      }
      // q contains index... r contains content
      deque.offer(i);
      if (i >= k - 1) {
        result[j++] = nums[deque.peek()];
      }
    }
    return result;
  }
}
