package com.coding.leetcode.puzzle;

/**
 * @author shu wj
 */
public class Leetcode5473 {
  public static class Solution_01 {
    /**
     * 关键是怎么思考到这样的解法.
     * 连续0 和 连续的1 可以一起翻转，然后实际就是看1-0变化的次数了
     * @param target
     * @return
     */
    public int minFlips(String target) {
      target = "0" + target;
      int ans = 0;
      for (int i = 1; i < target.length(); i++) {
        if (target.charAt(i - 1) != target.charAt(i)) {
          ans++;
        }
      }
      return ans;
    }
  }
}
