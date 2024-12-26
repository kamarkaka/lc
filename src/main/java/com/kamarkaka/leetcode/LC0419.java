package com.kamarkaka.leetcode;

/***
 * 419. Battleships in a Board
 * Given an m x n matrix board where each cell is a battleship 'X' or empty '.', return the number of the battleships on
 * board.
 * Battleships can only be placed horizontally or vertically on board. In other words, they can only be made of the
 * shape 1 x k (1 row, k columns) or k x 1 (k rows, 1 column), where k can be of any size. At least one horizontal or
 * vertical cell separates between two battleships (i.e., there are no adjacent battleships).
 * Example 1:
 *   Input: board = [["X",".",".","X"],[".",".",".","X"],[".",".",".","X"]]
 *   Output: 2
 * Example 2:
 *   Input: board = [["."]]
 *   Output: 0
 * Constraints:
 *   m == board.length
 *   n == board[i].length
 *   1 <= m, n <= 200
 *   board[i][j] is either '.' or 'X'.
 * Follow up: Could you do it in one-pass, using only O(1) extra memory and without modifying the values board?
 */
public class LC0419 {
    public int countBattleships(char[][] board) {
        int shipCount = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'X' && noAdjacentShip(board, i, j)) shipCount++;
            }
        }

        return shipCount;
    }

    private boolean noAdjacentShip(char[][] board, int row, int col) {
        if (row == 0 && col == 0) return true;
        if (row == 0) return board[0][col - 1] != 'X';
        if (col == 0) return board[row - 1][0] != 'X';
        return board[row][col - 1] != 'X' && board[row - 1][col] != 'X';
    }
}
