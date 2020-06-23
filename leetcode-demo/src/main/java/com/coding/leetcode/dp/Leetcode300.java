package com.coding.leetcode.dp;

/**
 * @Author shu wj @Date 2020/6/21 12:54 @Description 明确 base case -> 明确「状态」-> 明确「选择」 -> 定义 dp
 * 数组/函数的含义
 *
 * <p>如何找到动态规划的状态转移关系： 1、明确 dp 数组所存数据的含义。这一步对于任何动态规划问题都很重要，如果不得当或者不够清晰，会阻碍之后的步骤。 2、根据 dp
 * 数组的定义，运用数学归纳法的思想，假设 dp[0...i-1] 都已知，想办法求出 dp[i]，一旦这一步完成，整个题目基本就解决了。
 *
 * <p>但如果无法完成这一步，很可能就是 dp 数组的定义不够恰当，需要重新定义 dp 数组的含义；或者可能是 dp 数组存储的信息还不够，不足以推出下一步的答案，需要把 dp
 * 数组扩大成二维数组甚至三维数组。
 */
public class Leetcode300 {
  public static class Solution_01 {
    public int lengthOfLIS(int[] nums) {
      // dp[i] 表示 nums[i] 这个数结尾的最长递增子序列的长度
      // 假设已经知道 dp[0..4] 的所有结果，如何通过这些结果推出 dp[5]
      // nums[5]=3，既然是递增子序列，只要找到前面那些结尾比3小的子序列，然后把3接到最后，就可以形成一个新的递增子序列，而且这个子序列长度加1。
      // 所以这个解法的复杂度为 O(n^2)
      if (null == nums || nums.length == 0) return 0;
      int n = nums.length;
      int[] dp = new int[n];
      int res = 0;
      for (int i = 0; i < n; i++) {
        dp[i] = 1;
        for (int j = 0; j < i; j++) {
          if (nums[j] < nums[i]) {
            dp[i] = Math.max(dp[j] + 1, dp[i]);
            res = Math.max(res, dp[i]);
          }
        }
      }

      return res;
    }
  }

  /**
   * patience sorting（耐心排序）
   * O(N*log(N))
   */
  public static class Solution_02 {
    public int lengthOfLIS(int[] nums) {
      int[] top = new int[nums.length];
      int piles = 0;
      for (int poker : nums) {
        int left = 0, right = piles;
        while (left < right) {
          int mid = (left + right) / 2;
          if (top[mid] > poker) {
            right = mid;
          } else if (top[mid] < poker) {
            left = mid + 1;
          } else {
            right = mid;
          }
        }

        if (left == piles) piles++;
        top[left] = poker;
      }
      return piles;
    }
  }

  /**
   * 贪心 + 二分 O(N*log(N)) 考虑一个简单的贪心，如果我们要使上升子序列尽可能的长，则我们需要让序列上升得尽可能慢，因此我们希望每次在上升子序列最后加上的那个数尽可能的小。
   *
   * <p>无序列表最关键的一句在于： 数组 d[i]表示长度为 i 的最长上升子序列的末尾元素的最小值，即在数组 1,2,3,4,5,6中长度为3的上升子序列可以为 1,2,3也可以为
   * 2,3,4等等但是d[3]=3，即子序列末尾元素最小为3。
   *
   * <p>无序列表解释清了数组d的含义之后，我们接着需要证明数组d具有单调性，即证明 i<j 时，d[i]<d[j]，使用反证法，假设存在k<j时，d[k]>d[j]，但在长度为j，末尾元素为d[j]的子序列A中，将后j-i个元素减掉，
   * 可以得到一个长度为i的子序列B，其末尾元素t1必然小于d[j]（因为在子序列A中，t1的位置上在d[j]的后面），而我们假设数组d必须符合表示长度为
   * i 的最长上升子序列的末尾元素的最小值，此时长度为i的子序列的末尾元素t1<d[j]<d[k]，即t1<d[k]，所以d[k]不是最小的，与题设相矛盾，因此可以证明其单调性
   *
   * <p>无序列表证明单调性有两个好处：1.可以使用二分法；2.数组d的长度即为最长子序列的长度；
   */
  public static class Solution_03 {
    public int lengthOfLIS(int[] nums) {
      // 需要用一个数组，定义为 d[i] 表示长度为i的上升序列的最小末尾长度。 然后，遍历 nums，进行更新。
      // i < j 必定有 d[i] < d[j]，因此，如果 nums[k] 值比 最后一个 d 中的值都大，则长度加1，否则，更新 d 中 查找 nums[k] 当前值的左边界
      if (null == nums || nums.length == 0) return 0;
      int n = nums.length;
      int[] d = new int[n + 1];

      int len = 1;
      d[len] = nums[0];
      for (int i = 1; i < n; i++) {
        if (nums[i] > d[len]) {
          d[++len] = nums[i];
        } else {
          // 找到 d 中对于 target = nums[i] 的左边界, [1, len) 范围内
          int left = 1, right = len;
          while (left < right) {
            int mid = left + (right - left) / 2;
            if (d[mid] > nums[i]) {
              // 往左搜索
              right = mid;
            } else if (d[mid] < nums[i]) {
              left = mid + 1;
            } else if (d[mid] == nums[i]) {
              right = mid;
            }
          }
          // 需要注意的是，这个找出来的left 其实它的范围是 [1, len] 所以这里做了二次判断
          if (d[left] > nums[i]) {
            d[left] = nums[i];
          }
        }
      }

      return len;
    }
  }

  public static void main(String[] args) {
    int[] nums = {1, 3, 6, 7, 9, 4, 10, 5, 6};
    Solution_01 solution_01 = new Solution_01();
    System.out.println(solution_01.lengthOfLIS(nums));
  }
}
