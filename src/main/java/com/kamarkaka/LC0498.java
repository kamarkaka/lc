package com.kamarkaka;

/***
 * 498. Diagonal Traverse
 * Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.
 *
 * Example 1:
 *    Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
 *    Output: [1,2,4,7,5,3,6,8,9]
 *
 * Example 2:
 *    Input: mat = [[1,2],[3,4]]
 *    Output: [1,2,3,4]
 *
 * Constraints:
 *    m == mat.length
 *    n == mat[i].length
 *    1 <= m, n <= 10^4
 *    1 <= m * n <= 10^4
 *    -10^5 <= mat[i][j] <= 10^5
 */
public class LC0498 {
   public int[] findDiagonalOrder(int[][] matrix) {
      if (matrix == null || matrix.length == 0) return new int[0];

      int rows = matrix.length;
      int cols = matrix[0].length;

      int row = 0, col = 0;
      int direction = 1;

      int[] result = new int[rows * cols];

      int r = 0;
      while (row < rows && col < cols) {
         result[r] = matrix[row][col];
         r++;

         int nr = row + (direction == 1 ? -1 : 1);
         int nc = col + (direction == 1 ? 1 : -1);
         if (nr < 0 || nr == rows || nc < 0 || nc == cols) {
            if (direction == 1) {
               row += (col == cols - 1 ? 1 : 0) ;
               col += (col < cols - 1 ? 1 : 0);
            } else {
               col += (row == rows - 1 ? 1 : 0);
               row += (row < rows - 1 ? 1 : 0);
            }

            direction = 1 - direction;
         } else {
            row = nr;
            col = nc;
         }
      }
      return result;
   }
}
