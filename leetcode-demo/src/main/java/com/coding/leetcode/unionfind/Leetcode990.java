package com.coding.leetcode.unionfind;

/** @Author shu wj @Date 2020/7/22 00:11 @Description */
public class Leetcode990 {
  public static class Solution_01 {

    public static class UnionFind {

      private int count;

      private final int[] size;

      private final int[] parent;

      public UnionFind(int n) {
        count = n;

        size = new int[n];
        parent = new int[n];
        for (int i = 0; i < n; i++) {
          size[i] = 1;
          parent[i] = i;
        }
      }

      private void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        if (size[rootP] < size[rootQ]) {
          parent[rootQ] = parent[rootP];
          size[rootP] += size[rootQ];
        } else {
          parent[rootP] = parent[rootQ];
          size[rootQ] += size[rootP];
        }

        count--;
      }

      private int find(int x) {
        while (parent[x] != x) {
          x = parent[x];
        }

        return x;
      }

      private boolean connect(int p, int q) {
        return find(p) == find(q);
      }
    }

    public boolean equationsPossible(String[] equations) {
      if (null == equations || equations.length == 0) return true;
      UnionFind unionFind = new UnionFind(26);
      for (String equation : equations) {
        if (equation.charAt(1) == '=') {
          char x = equation.charAt(0);
          char y = equation.charAt(3);

          unionFind.union(x - 'a', y - 'a');
        }
      }

      for (String equation : equations) {
        if (equation.charAt(1) == '!') {
          char x = equation.charAt(0);
          char y = equation.charAt(3);

          if (unionFind.connect(x - 'a', y - 'a')) {
            return false;
          }
        }
      }
      return true;
    }
  }

  public static void main(String[] args) {
    String[] equations = {"a==b","b!=a"};
    Solution_01 solution_01 = new Solution_01();

    System.out.println(solution_01.equationsPossible(equations));
  }
}
