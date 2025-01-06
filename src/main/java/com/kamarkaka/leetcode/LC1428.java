package com.kamarkaka.leetcode;

import java.util.List;

/***
 * 1428. Leftmost Column with at Least a One
 * A row-sorted binary matrix means that all elements are 0 or 1 and each row of the matrix is sorted in non-decreasing
 * order.
 * Given a row-sorted binary matrix binaryMatrix, return the index (0-indexed) of the leftmost column with a 1 in it. If
 * such an index does not exist, return -1.
 * You can't access the Binary Matrix directly. You may only access the matrix using a BinaryMatrix interface:
 *   BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
 *   BinaryMatrix.dimensions() returns the dimensions of the matrix as a list of 2 elements [rows, cols], which means
 *   the matrix is rows x cols.
 * Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer. Also, any solutions that
 * attempt to circumvent the judge will result in disqualification.
 * For custom testing purposes, the input will be the entire binary matrix mat. You will not have access to the binary
 * matrix directly.
 * Example 1:
 *   Input: mat = [[0,0],[1,1]]
 *   Output: 0
 * Example 2:
 *   Input: mat = [[0,0],[0,1]]
 *   Output: 1
 * Example 3:
 *   Input: mat = [[0,0],[0,0]]
 *   Output: -1
 * Constraints:
 *   rows == mat.length
 *   cols == mat[i].length
 *   1 <= rows, cols <= 100
 *   mat[i][j] is either 0 or 1.
 *   mat[i] is sorted in non-decreasing order.
 */
public class LC1428 {
   public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
      List<Integer> dimension = binaryMatrix.dimensions();
      int rows = dimension.get(0), cols = dimension.get(1);
      int smallestIndex = cols;

      for (int row = 0; row < rows; row++) {
         int lo = 0;
         int hi = smallestIndex - 1;
         while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (binaryMatrix.get(row, mid) == 0) {
               lo = mid + 1;
            } else {
               hi = mid;
            }
         }

         if (binaryMatrix.get(row, lo) == 1) {
            smallestIndex = Math.min(smallestIndex, lo);
         }
      }
      return smallestIndex == cols ? -1 : smallestIndex;
   }

   public int leftMostColumnWithOne2(BinaryMatrix binaryMatrix) {
      List<Integer> dimension = binaryMatrix.dimensions();
      int rows = dimension.get(0), cols = dimension.get(1);

      int currentRow = 0;
      int currentCol = cols - 1;

      while (currentRow < rows && currentCol >= 0) {
         if (binaryMatrix.get(currentRow, currentCol) == 0) {
            currentRow++;
         } else {
            currentCol--;
         }
      }

      // If we never left the last column, this is because it was all 0's.
      return (currentCol == cols - 1) ? -1 : currentCol + 1;
   }

   interface BinaryMatrix {
      int get(int row, int col);
      List<Integer> dimensions();
   };
}
