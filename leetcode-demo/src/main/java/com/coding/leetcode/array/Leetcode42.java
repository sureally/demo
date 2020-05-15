package com.coding.leetcode.array;

import java.util.Stack;

/**
 * @Author shu wj
 * @Date 2020/4/5 18:04
 * @Description 接雨水
 */
public class Leetcode42 {

//  public int trap(int[] height) {
//
//  }

  /**
   * 暴力遍历找， O（n^2）
   * @param height
   * @return
   */
  public int solution_01(int[] height) {
    if (null == height || height.length == 0) {
      return 0;
    }

    int res = 0;
    int len = height.length;
    for (int i = 0; i < len; i++) {
      int maxLeft = 0;
      int maxRight = 0;
      for (int j = i; j >= 0; j--) {
        maxLeft = Math.max(height[j], maxLeft);
      }

      for (int j = i; j < len; j++) {
        maxRight = Math.max(height[j], maxRight);
      }

      res += Math.max(maxRight, maxLeft) - height[i];
    }

    return res;
  }


  /**
   * 向左向右的扫描过程中，提前存储其最大值.
   *
   * 时间复杂度：O(n)
   * 空间复杂度：O(n)
   * @param height
   * @return
   */
  public static int solution_02(int[] height) {
    if (null == height || height.length == 0) {
      return 0;
    }
    int len = height.length;
    int res = 0;
    int[] maxLefts = new int[len];
    int[] maxRights = new int[len];
    maxLefts[0] = height[0];
    maxRights[len - 1] = height[len - 1];

    for (int i = 1; i < len; i++) {
      maxLefts[i] = Math.max(maxLefts[i - 1], height[i]);
      maxRights[len - i - 1] = Math.max(height[len - i - 1], maxRights[len - i]);
    }

    for (int i = 0; i < len; i++) {
      res += Math.min(maxLefts[i], maxRights[i]) - height[i];
    }

    return res;
  }


  /**
   * 在遍历数组时维护一个栈。如果当前的条形块小于或等于栈顶的条形块，我们将条形块的索引入栈，意思是当前的条形块被栈中的前一个条形块界定。
   * 如果我们发现一个条形块长于栈顶，我们可以确定栈顶的条形块被当前条形块和栈的前一个条形块界定，
   * 因此我们可以弹出栈顶元素并且累加答案到 ans 。
   *
   * 算法：
   * 使用栈来存储条形块的索引下标。
   * 遍历数组：
   *    当栈非空且height[current] > height[st.top()]
   *        意味着栈中元素可以被弹出。弹出栈顶元素top
   *        计算当前元素和栈顶元素的距离，准备进行填充操作：distance = current - st.top() - 1
   *        找出界定高度：boundde_height = min(height[current], height[st.top()]) - height[top]
   *        往答案中累加积水量 ans += distance * bounded_height
   *    将当前索引下标入栈
   *    将current移动到下个位置
   *
   * 分析：时间复杂度 O(n), 空间复杂度 O(n)
   * @param height
   * @return
   */
  public static int solution_03(int[] height) {
    if (null == height || 0 == height.length) {
      return 0;
    }

    int res = 0;

    Stack<Integer> indices = new Stack<>();
    for (int i = 0; i < height.length; i++) {
      while (!indices.isEmpty() && height[indices.peek()] <= height[i]) {
        int top = indices.pop();
        if (indices.isEmpty()) {
          break;
        }

        int distance = i - indices.peek() - 1;

        int heightDiff = Math.min(height[indices.peek()], height[i]) - height[top];

        res += distance * heightDiff;

      }
      indices.push(i);
    }

    return res;
  }


  public static void main(String[] args) {
    int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
    System.out.println(solution_02(height));
    System.out.println(solution_03(height));
  }
}
