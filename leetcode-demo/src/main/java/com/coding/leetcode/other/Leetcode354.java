package com.coding.leetcode.other;

import java.util.Arrays;

/**
 * @Author shu wj @Date 2020/7/14 00:45 @Description
 * 很多算法问题都需要排序技巧，其难点不在于排序本身，而是需要巧妙地排序进行预处理，将算法问题进行转换，为之后的操作打下基础
 */
public class Leetcode354 {
  /** O(N^2)，主要是 递增子序列的长度，求取导致的，可以优化为 O（Nlog(N)) */
  public static class Solution_01 {
    // [w, h]
    // 先按照 w 从小到大排序，然后 相同的 w 再按 h 从大到小排序，最后 找 h 的最长递增子序列即可。
    public int maxEnvelopes(int[][] envelopes) {
      if (envelopes == null || envelopes.length == 0) return 0;
      int ans = 1;
      Arrays.sort(
          envelopes,
          (a, b) -> {
            if (a[0] == b[0]) return b[1] - a[1];
            return a[0] - b[0];
          });

      int n = envelopes.length;
      int[] dp = new int[n];
      // base state
      Arrays.fill(dp, 1);
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < i; j++) {
          if (envelopes[i][1] > envelopes[j][1]) {
            dp[i] = Math.max(dp[i], dp[j] + 1);
            ans = Math.max(ans, dp[i]);
          }
        }
      }

      return ans;
    }
  }

  public static class Solution_02 {
    public int maxEnvelopes(int[][] envelopes) {
      if (envelopes == null || envelopes.length == 0) return 0;
      int ans = 1;
      Arrays.sort(
          envelopes,
          (a, b) -> {
            if (a[0] == b[0]) return b[1] - a[1];
            return a[0] - b[0];
          });

      int n = envelopes.length;
      int[] dp = new int[n + 1];

      // state: dp[i] 表示长度为i的递增序列的结尾元素的最小值。 所以这个是单调的 dp[i] < dp[j], i < j
      // 贪心的一种考虑，就是 要递增序列最长，则递增序列的每个值都要尽可能的小。
      // base state
      // 1 4 5 6 2 3 4 5
      // 1 4 5 6
      dp[1] = envelopes[0][1];
      for (int i = 1; i < n; i++) {
        // 找到 dp 中比 envelops[i][1] 大的第一个元素，如果超出边界，否则替换，如果大于最右边则新增。
        int left = 1, right = ans + 1;
        // 二分查找左边界，范围是 [0, res + 1), 停止条件 left == rifht
        while (left < right) {
          int mid = left + (right - left) / 2;
          if (dp[mid] > envelopes[i][1]) {
            right = mid;
          } else if (dp[mid] < envelopes[i][1]) {
            left = mid + 1;
          } else {
            right = mid;
          }
        }

        if (left != 1 || dp[left] >= envelopes[i][1]) {
          dp[left] = envelopes[i][1];
          ans = Math.max(ans, left);
        }
      }

      return ans;
    }
  }

  public static void main(String[] args) {
    int[][] envelops = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};

    Solution_02 solution_02 = new Solution_02();

    System.out.println(solution_02.maxEnvelopes(envelops));
  }
}
