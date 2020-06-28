package com.coding.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

import com.coding.leetcode.tree.TreeNode;

/** @Author shu wj @Date 2020/6/28 23:28 @Description */
public class Leetcode337 {
  public static class Solution_01 {
    private final Map<TreeNode, Integer> memo = new HashMap<>();

    public int rob(TreeNode root) {
      if (root == null) return 0;
      // 利用备忘录消除重叠子问题
      if (memo.containsKey(root)) return memo.get(root);
      // 抢，然后去下下家
      int do_it =
          root.val
              + (root.left == null ? 0 : rob(root.left.left) + rob(root.left.right))
              + (root.right == null ? 0 : rob(root.right.left) + rob(root.right.right));
      // 不抢，然后去下家
      int not_do = rob(root.left) + rob(root.right);

      int res = Math.max(do_it, not_do);
      memo.put(root, res);
      return res;
    }
  }

  // 相同的思路，优化空间
  /** 虽然算法分析层面时间复杂度是相同的。原因在于此解法没有使用额外的备忘录，减少了数据操作的复杂性，所以实际运行效率会快 */
  public static class Solution_02 {
    public int rob(TreeNode root) {
      int[] res = dp(root);
      return Math.max(res[0], res[1]);
    }

    /* 返回一个大小为 2 的数组 arr
    arr[0] 表示不抢 root 的话，得到的最大钱数
    arr[1] 表示抢 root 的话，得到的最大钱数 */
    int[] dp(TreeNode root) {
      if (root == null) return new int[] {0, 0};
      int[] left = dp(root.left);
      int[] right = dp(root.right);
      // 抢，下家就不能抢了
      int rob = root.val + left[0] + right[0];
      // 不抢，下家可抢可不抢，取决于收益大小
      int not_rob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

      return new int[] {not_rob, rob};
    }
  }
}
