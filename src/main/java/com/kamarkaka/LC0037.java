package com.kamarkaka;

/***
 * 37. Sudoku Solver
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * A sudoku solution must satisfy all of the following rules:
 *    Each of the digits 1-9 must occur exactly once in each row.
 *    Each of the digits 1-9 must occur exactly once in each column.
 *    Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 *    The '.' character indicates empty cells.
 *
 * Example 1:
 *    Input: board = [
 *       ["5","3",".",".","7",".",".",".","."],
 *       ["6",".",".","1","9","5",".",".","."],
 *       [".","9","8",".",".",".",".","6","."],
 *       ["8",".",".",".","6",".",".",".","3"],
 *       ["4",".",".","8",".","3",".",".","1"],
 *       ["7",".",".",".","2",".",".",".","6"],
 *       [".","6",".",".",".",".","2","8","."],
 *       [".",".",".","4","1","9",".",".","5"],
 *       [".",".",".",".","8",".",".","7","9"]
 *    ]
 *    Output: [
 *       ["5","3","4","6","7","8","9","1","2"],
 *       ["6","7","2","1","9","5","3","4","8"],
 *       ["1","9","8","3","4","2","5","6","7"],
 *       ["8","5","9","7","6","1","4","2","3"],
 *       ["4","2","6","8","5","3","7","9","1"],
 *       ["7","1","3","9","2","4","8","5","6"],
 *       ["9","6","1","5","3","7","2","8","4"],
 *       ["2","8","7","4","1","9","6","3","5"],
 *       ["3","4","5","2","8","6","1","7","9"]
 *    ]
 *    Explanation: The input board is shown above and the only valid solution is shown below:
 *
 * Constraints:
 *    board.length == 9
 *    board[i].length == 9
 *    board[i][j] is a digit or '.'.
 *    It is guaranteed that the input board has only one solution.
 */
public class LC0037 {
   // box size
   int n = 3;
   // row size
   int N = n * n;

   int [][] rows = new int[N][N + 1];
   int [][] columns = new int[N][N + 1];
   int [][] boxes = new int[N][N + 1];

   char[][] board;
   boolean sudokuSolved = false;

   // Check if one could place a number d in (row, col) cell
   public boolean couldPlace(int d, int row, int col) {
      int idx = (row / n ) * n + col / n;
      return rows[row][d] + columns[col][d] + boxes[idx][d] == 0;
   }

   // Place a number d in (row, col) cell
   public void placeNumber(int d, int row, int col) {
      int idx = (row / n ) * n + col / n;

      rows[row][d]++;
      columns[col][d]++;
      boxes[idx][d]++;
      board[row][col] = (char)(d + '0');
   }

   // Remove a number which didn't lead to a solution
   public void removeNumber(int d, int row, int col) {
      int idx = (row / n ) * n + col / n;

      rows[row][d]--;
      columns[col][d]--;
      boxes[idx][d]--;
      board[row][col] = '.';
   }

   // backtrack
   public void placeNextNumbers(int row, int col) {
      if ((col == N - 1) && (row == N - 1)) {
         sudokuSolved = true;
      } else {
         if (col == N - 1) backtrack(row + 1, 0);
         else backtrack(row, col + 1);
      }
   }

   public void backtrack(int row, int col) {
      if (board[row][col] == '.') {
         for (int d = 1; d < 10; d++) {
            if (couldPlace(d, row, col)) {
               placeNumber(d, row, col);
               placeNextNumbers(row, col);
               if (!sudokuSolved) removeNumber(d, row, col);
            }
         }
      } else {
         placeNextNumbers(row, col);
      }
   }

   public void solveSudoku(char[][] board) {
      this.board = board;

      for (int i = 0; i < N; i++) {
         for (int j = 0; j < N; j++) {
            char num = board[i][j];
            if (num != '.') {
               int d = Character.getNumericValue(num);
               placeNumber(d, i, j);
            }
         }
      }
      backtrack(0, 0);
   }
}
