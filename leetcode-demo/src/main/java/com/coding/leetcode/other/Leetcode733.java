package com.coding.leetcode.other;

/** @Author shu wj @Date 2020/7/13 23:07 @Description */
public class Leetcode733 {
  public static class Solution_01 {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
      fill(image, sr, sc, image[sr][sc], newColor);

      return image;
    }

    void fill(int[][] image, int x, int y, int origColor, int newColor) {
      // 出界：超出数组边界
      if (!inArea(image, x, y)) return;
      // 碰壁：遇到其他颜色，超出 origColor 区域
      if (image[x][y] != origColor) return;
      // 已探索过的 origColor 区域
      if (image[x][y] == -1) return;

      // choose：打标记，以免重复
      image[x][y] = -1;
      fill(image, x, y + 1, origColor, newColor);
      fill(image, x, y - 1, origColor, newColor);
      fill(image, x - 1, y, origColor, newColor);
      fill(image, x + 1, y, origColor, newColor);
      // unchoose：将标记替换为 newColor
      image[x][y] = newColor;
    }

    boolean inArea(int[][] image, int x, int y) {
      return x >= 0 && x < image.length && y >= 0 && y < image[0].length;
    }
  }
}
