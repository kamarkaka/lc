package com.kamarkaka.leetcode;

/***
 * 361. Bomb Enemy
 * Given an m x n matrix grid where each cell is either a wall 'W', an enemy 'E' or empty '0', return the maximum enemies you can kill using one bomb. You can only place the bomb in an empty cell.
 * The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since it is too strong to be destroyed.
 *
 * Example 1:
 *    Input: grid = [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
 *    Output: 3
 *
 * Example 2:
 *    Input: grid = [["W","W","W"],["0","0","0"],["E","E","E"]]
 *    Output: 1
 *
 * Constraints:
 *    m == grid.length
 *    n == grid[i].length
 *    1 <= m, n <= 500
 *    grid[i][j] is either 'W', 'E', or '0'.
 */
public class LC0361 {
   public int maxKilledEnemies(char[][] grid) {
      if (grid.length == 0) return 0;

      int rows = grid.length, cols = grid[0].length;
      int maxCount = 0, rowHits = 0;
      int[] colHits = new int[cols];

      for (int row = 0; row < rows; row++) {
         for (int col = 0; col < cols; col++) {
            // reset the hits on the row, if necessary.
            if (col == 0 || grid[row][col - 1] == 'W') {
               rowHits = 0;
               for (int k = col; k < cols; k++) {
                  if (grid[row][k] == 'W') break;
                  if (grid[row][k] == 'E') rowHits++;
               }
            }

            // reset the hits on the column, if necessary.
            if (row == 0 || grid[row - 1][col] == 'W') {
               colHits[col] = 0;
               for (int k = row; k < rows; ++k) {
                  if (grid[k][col] == 'W') break;
                  if (grid[k][col] == 'E') colHits[col]++;
               }
            }

            // run the calculation for the empty cell.
            if (grid[row][col] == '0') {
               maxCount = Math.max(maxCount, rowHits + colHits[col]);
            }
         }
      }
      return maxCount;
   }
}
