package com.coding.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** @Author shu wj @Date 2020/7/1 22:44 @Description */
public class Leetcode51 {
  public static class Solution_01 {

    private List<List<String>> ans;

    public List<List<String>> solveNQueens(int n) {
      ans = new ArrayList<>();
      char[][] board = new char[n][n];
      for (char[] chars : board) {
        Arrays.fill(chars, '.');
      }
      backtrack(board, 0);
      return ans;
    }

    private List<String> charToString(char[][] board) {
      List<String> oneRes = new ArrayList<>();
      for (char[] chars : board) {
        oneRes.add(String.valueOf(chars));
      }
      return oneRes;
    }

    private void backtrack(char[][] board, int row) {
      if (board.length == row) {
        ans.add(charToString(board));
        return;
      }
      char[] chars = board[row];
      for (int col = 0; col < board.length; col++) {
        if (!isValid(board, row, col)) {
          continue;
        }
        chars[col] = 'Q';
        backtrack(board, row + 1);
        chars[col] = '.';
      }
    }

    private boolean isValid(char[][] board, int row, int col) {
      // col
      for (int j = row - 1; j >= 0; j--) {
        if ('Q' == board[j][col] ) return false;
      }
      // upright
      for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
        if ('Q' == board[i][j]) return false;
      }
      // upleft
      for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
        if ('Q' == board[i][j]) return false;
      }
      return true;
    }
  }

  public static void main(String[] args) {
    Solution_01 solution_01 = new Solution_01();

    solution_01.solveNQueens(4);
  }
}
