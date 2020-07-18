package com.coding.leetcode.recursive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** @Author shu wj @Date 2020/7/18 00:00 @Description */
public class Leetcode247 {
  public static class Solution_01 {
    /**
     * 1. 当 n=0 的时候，应该输出空字符串：“ ”。 2. 当 n=1 的时候，也就是长度为 1 的中心对称数有：0，1，8。 3. 当 n=2 的时候，长度为 2
     * 的中心对称数有：11，69，88，96。注意：00 并不是一个合法的结果。 3. 当 n=3 的时候，只需要在长度为 1 的合法中心对称数的基础上，不断地在两边添加
     * 11，69，88，96 就可以了。 [101, 609, 808, 906, 111, 619, 818, 916, 181, 689, 888, 986] 随着 n
     * 不断地增长，我们只需要在长度为 n-2 的中心对称数两边添加 11，69，88，96 即可。
     */
    public List<String> findStrobogrammatic(int n) {
      return helper(n, n);
    }
    // n表示，当前循环中，求得字符串长度； m表示题目中要求的字符串长度
    public List<String> helper(int n, int m) {
      // 第一步：判断输入或者状态是否合法
      if (n < 0 || m < 0 || n > m) {
        throw new IllegalArgumentException("invalid input");
      }
      // 第二步：判断递归是否应当结束
      if (n == 0) return new ArrayList<>(Arrays.asList(""));
      if (n == 1) return new ArrayList<>(Arrays.asList("0", "1", "8"));

      // 第三步：缩小问题规模
      List<String> list = helper(n - 2, m);

      // 第四步：整合结果
      List<String> res = new ArrayList<>();
      for (String s : list) {
        if (n != m) {
          // n=m时，表示最外层处理。
          // 例如：原始需求n=m=2, '00'不合法
          // 若原始需求n=m=4, 内层循环n=2,m=4,'00';最外层循环，n=m=4时，'1001'
          res.add("0" + s + "0");
        }
        res.add("1" + s + "1");
        res.add("6" + s + "9");
        res.add("8" + s + "8");
        res.add("9" + s + "6");
      }
      return res;
    }
  }
}
