package com.kamarkaka.leetcode;

/***
 * 2132. Stamping the Grid
 * You are given an m x n binary matrix grid where each cell is either 0 (empty) or 1 (occupied).
 * You are then given stamps of size stampHeight x stampWidth. We want to fit the stamps such that they follow the given restrictions and requirements:
 *    Cover all the empty cells.
 *    Do not cover any of the occupied cells.
 *    We can put as many stamps as we want.
 *    Stamps can overlap with each other.
 *    Stamps are not allowed to be rotated.
 *    Stamps must stay completely inside the grid.
 * Return true if it is possible to fit the stamps while following the given restrictions and requirements. Otherwise, return false.
 *
 * Example 1:
 *    Input: grid = [[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0]], stampHeight = 4, stampWidth = 3
 *    Output: true
 *    Explanation: We have two overlapping stamps (labeled 1 and 2 in the image) that are able to cover all the empty cells.
 *
 * Example 2:
 *    Input: grid = [[1,0,0,0],[0,1,0,0],[0,0,1,0],[0,0,0,1]], stampHeight = 2, stampWidth = 2
 *    Output: false
 *    Explanation: There is no way to fit the stamps onto all the empty cells without the stamps going outside the grid.
 *
 * Constraints:
 *    m == grid.length
 *    n == grid[r].length
 *    1 <= m, n <= 10^5
 *    1 <= m * n <= 2 * 10^5
 *    grid[r][c] is either 0 or 1.
 *    1 <= stampHeight, stampWidth <= 10^5
 */
public class LC2132 {
   public boolean possibleToStamp(int[][] grid, int h, int w) {
      int rows = grid.length;
      int cols = grid[0].length;
      for (int i = 0; i < grid.length; i++) {
         int prev = -1;
         for (int j = 0; j < cols; j++) {
            if (grid[i][j] == 0) {
               if (j == prev + 1 && j > 0 && j+w-1 < cols) {
                  if (i > 0 && grid[i-1][j] == 1 && i+h-1 < rows && grid[i+h-1][j+w-1] == 1) {
                     return false;
                  }
                  if (i + 1 < rows && grid[i+1][j] == 1 && i-h+1 >= 0 && grid[i-h+1][j+w-1] == 1) {
                     return false;
                  }
               }

               if (j + 1 < cols && grid[i][j+1] == 1 && j-w+1 >= 0) {
                  if (i > 0 && grid[i-1][j] == 1 && i+h-1 < rows && grid[i+h-1][j-w+1] == 1) {
                     return false;
                  }
                  if (i + 1 < rows && grid[i+1][j] == 1 && i-h+1 >= 0 && grid[i-h+1][j-w+1]==1) {
                     return false;
                  }
               }
               continue;
            }

            if (1 < j - prev && j - prev <= w) {
               return false;
            }

            prev = j;
         }
         if (1 < cols - prev && cols - prev <= w) {
            return false;
         }
      }

      for (int i = 0; i < cols; i++) {
         int prev = -1;
         for (int j = 0; j < rows; j++) {
            if (grid[j][i] == 0) {
               continue;
            }

            if (1 < j - prev && j - prev <= h) {
               return false;
            }

            prev = j;
         }

         if (1 < grid.length - prev && grid.length - prev <= h) {
            return false;
         }
      }

      return true;
   }
}
