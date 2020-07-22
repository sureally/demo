package com.coding.leetcode.unionfind;

/**
 * @Author shu wj
 * @Date 2020/7/22 22:46
 * @Description
 */
public class UnionFind {

  private int count;

  private int[] size;

  private int[] parent;

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
