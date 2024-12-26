package com.kamarkaka.leetcode;

/***
 * 130. Surrounded Regions
 * You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:
 *   Connect: A cell is connected to adjacent cells horizontally or vertically.
 *   Region: To form a region connect every 'O' cell.
 *   Surround: The region is surrounded with 'X' cells if you can connect the region with 'X' cells and none of the
 *   region cells are on the edge of the board.
 * A surrounded region is captured by replacing all 'O's with 'X's in the input matrix board.
 * Example 1:
 *   Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 *   Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 *   Explanation:
 *   In the above diagram, the bottom region is not captured because it is on the edge of the board and cannot be
 *   surrounded.
 * Example 2:
 *   Input: board = [["X"]]
 *   Output: [["X"]]
 * Constraints:
 *   m == board.length
 *   n == board[i].length
 *   1 <= m, n <= 200
 *   board[i][j] is 'X' or 'O'.
 */
public class LC0130 {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;

        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == 'O') helper(board, i, 0);
            if (board[i][board[0].length - 1] == 'O') helper(board, i, board[0].length - 1);
        }

        for (int j = 0; j < board[0].length; j++) {
            if(board[0][j] == 'O' ) helper(board, 0, j);
            if (board[board.length - 1][j] == 'O') helper(board, board.length - 1, j);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'T') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void helper(char[][] board, int r, int c) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != 'O') {
            return;
        }
        board[r][c] = 'T';
        helper(board, r + 1, c);
        helper(board, r - 1, c);
        helper(board, r, c + 1);
        helper(board, r, c - 1);
    }
}
