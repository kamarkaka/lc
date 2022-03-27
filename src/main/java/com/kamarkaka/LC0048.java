package com.kamarkaka;

import com.kamarkaka.common.Utilities;

/***
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 *
 * Example 1:
 *   Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 *   Output: [[7,4,1],[8,5,2],[9,6,3]]
 *
 * Example 2:
 *   Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 *   Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 *
 * Example 3:
 *   Input: matrix = [[1]]
 *   Output: [[1]]
 *
 * Example 4:
 *   Input: matrix = [[1,2],[3,4]]
 *   Output: [[3,1],[4,2]]
 *
 * Constraints:
 *   matrix.length == n
 *   matrix[i].length == n
 *   1 <= n <= 20
 *   -1000 <= matrix[i][j] <= 1000
 */
public class LC0048 {
   public void rotate(int[][] matrix) {
      if (matrix == null || matrix.length < 2) return;

      int size = matrix.length, step = size / 2, stepCounter = 0, tmpData = -1;
      int[] tl, tr, bl, br;
      while (stepCounter < step) {
         tl = new int[]{stepCounter,stepCounter};
         tr = new int[]{stepCounter,size - 1 - stepCounter};
         bl = new int[]{size - 1 - stepCounter,stepCounter};
         br = new int[]{size - 1 - stepCounter,size - 1 - stepCounter};

         for (int i = 0; i < size - 2 * stepCounter - 1; i++) {
            tmpData = matrix[tl[0]][tl[1]];

            matrix[tl[0]][tl[1]] = matrix[bl[0]][bl[1]];
            matrix[bl[0]][bl[1]] = matrix[br[0]][br[1]];
            matrix[br[0]][br[1]] = matrix[tr[0]][tr[1]];
            matrix[tr[0]][tr[1]] = tmpData;

            tl[1]++;
            tr[0]++;
            br[1]--;
            bl[0]--;
         }

         stepCounter++;
      }
   }

   public static void run() {
      LC0048 solution = new LC0048();

      int[][] matrix;

      matrix = new int[][] {{1,2,3}, {4,5,6}, {7,8,9}};
      solution.rotate(matrix);
      Utilities.print(matrix);

      matrix = new int[][] {{5,1,9,11}, {2,4,8,10}, {13,3,6,7}, {15,14,12,16}};
      solution.rotate(matrix);
      Utilities.print(matrix);

      matrix = new int[][] {{1}};
      solution.rotate(matrix);
      Utilities.print(matrix);

      matrix = new int[][] {{1,2}, {3,4}};
      solution.rotate(matrix);
      Utilities.print(matrix);
   }
}
