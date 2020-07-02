package com.coding.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author shu wj
 * @Date 2020/7/1 22:37
 * @Description
 */
public class Leetcode232 {
  public static class Solution_01 {
    class MyQueue {
      private final Deque<Integer> stack1 = new ArrayDeque<>();
      private final Deque<Integer> stack2 = new ArrayDeque<>();

      // [1 2 3
      // 3 2 1]
      /** Initialize your data structure here. */
      public MyQueue() {

      }

      /** Push element x to the back of queue. */
      public void push(int x) {
        stack1.push(x);
      }

      /** Removes the element from in front of queue and returns that element. */
      public int pop() {
        if (stack2.isEmpty()) {
          while (!stack1.isEmpty()) {
            stack2.push(stack1.poll());
          }
        }
        return stack2.poll();
      }

      /** Get the front element. */
      public int peek() {
        if (stack2.isEmpty()) {
          while (!stack1.isEmpty()) {
            stack2.push(stack1.poll());
          }
        }
        return stack2.peek();
      }

      /** Returns whether the queue is empty. */
      public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
      }
    }
  }
}
