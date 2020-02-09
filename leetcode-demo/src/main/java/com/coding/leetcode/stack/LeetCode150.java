package com.coding.leetcode.stack;

import java.util.Stack;

/**
 * @Author shu wj
 * @Date 2019/12/6 00:08
 * @Description
 */
public class LeetCode150 {
  public int evalRPN(String[] tokens) {
    // if the str is number, then it is pushed to stack, or pop two number from stack to calculate, and then
    // the result is pushed to stack.

    // because the problem ensure that it must is valid work, so it do not think more case
    Stack<Integer> numStack = new Stack<>();

    for (String token : tokens) {
      switch (token) {
        case "+":
          numStack.push(numStack.pop() + numStack.pop());
          break;
        case "-":
          numStack.push(-numStack.pop() + numStack.pop());
          break;
        case "/":
          int first = numStack.pop();
          numStack.push(numStack.pop() / first);
          break;
        case "*":
          numStack.push(numStack.pop() * numStack.pop());
          break;
        default:
          numStack.push(Integer.parseInt(token));
      }
    }


    return numStack.pop();
  }
}
