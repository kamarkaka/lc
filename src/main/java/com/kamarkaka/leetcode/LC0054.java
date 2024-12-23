package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.List;

/***
 * 54. Spiral Matrix
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 *
 * Example 1:
 *    Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 *    Output: [1,2,3,6,9,8,7,4,5]
 *
 * Example 2:
 *    Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 *    Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * Constraints:
 *    m == matrix.length
 *    n == matrix[i].length
 *    1 <= m, n <= 10
 *    -100 <= matrix[i][j] <= 100
 */
public class LC0054 {
   public List<Integer> spiralOrder(int[][] matrix) {
      List<Integer> result = new ArrayList<>();
      int rows = matrix.length;
      int columns = matrix[0].length;
      int up = 0;
      int left = 0;
      int right = columns - 1;
      int down = rows - 1;

      while (result.size() < rows * columns) {
         // Traverse from left to right.
         for (int col = left; col <= right; col++) {
            result.add(matrix[up][col]);
         }
         // Traverse downwards.
         for (int row = up + 1; row <= down; row++) {
            result.add(matrix[row][right]);
         }
         // Make sure we are now on a different row.
         if (up != down) {
            // Traverse from right to left.
            for (int col = right - 1; col >= left; col--) {
               result.add(matrix[down][col]);
            }
         }
         // Make sure we are now on a different column.
         if (left != right) {
            // Traverse upwards.
            for (int row = down - 1; row > up; row--) {
               result.add(matrix[row][left]);
            }
         }
         left++;
         right--;
         up++;
         down--;
      }

      return result;
   }

   public static void run() {
      LC0054 sol = new LC0054();
      System.out.println(sol.spiralOrder(new int[][] {
         {1,2,3,4},
         {5,6,7,8},
         {9,10,11,12}
      }));
   }
}
