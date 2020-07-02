package com.coding.leetcode.backtracking;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/** @Author shu wj @Date 2020/7/1 22:44 @Description */
public class Leetcode51 {
  public static class Solution_01 {

    List<List<String>> res;
    private List<String> charToString(char[][] array) {
      List<String> result = new LinkedList<>();
      for (char[] chars : array) {
        result.add(String.valueOf(chars));
      }
      return result;
    }

    public List<List<String>> solveNQueens(int n) {
      if (n <= 0) return null;
      res = new LinkedList<>();
      char[][] board = new char[n][n];
      for (char[] chars : board) Arrays.fill(chars, '.');
      backtrack(board, 0);
      return res;
    }

    private void backtrack(char[][] board, int row) {
      if (row == board.length) {
        res.add(charToString(board));
        return;
      }
      int n = board[row].length;
      for (int col = 0; col < n; col++) {
        if (!isValid(board, row, col)) continue;
        board[row][col] = 'Q';
        backtrack(board, row + 1);
        board[row][col] = '.';
      }
    }

    private boolean isValid(char[][] board, int row, int col) {
      int rows = board.length;
      // check is valid in col
      for (char[] chars : board) {
        if (chars[col] == 'Q') return false;
      }
      // check is valide upright
      for (int i = row - 1, j = col + 1; i >= 0 && j < rows; i--, j++) {
        if (board[i][j] == 'Q') return false;
      }
      // check is valide upleft
      for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
        if (board[i][j] == 'Q') return false;
      }
      return true;
    }
  }
}
