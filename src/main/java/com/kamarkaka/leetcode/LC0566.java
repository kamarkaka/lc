package com.kamarkaka.leetcode;

/***
 * 566. Reshape the Matrix
 * In MATLAB, there is a handy function called reshape which can reshape an m x n matrix into a new one with a different
 * size r x c keeping its original data.
 * You are given an m x n matrix mat and two integers r and c representing the number of rows and the number of columns
 * of the wanted reshaped matrix.
 * The reshaped matrix should be filled with all the elements of the original matrix in the same row-traversing order as
 * they were.
 * If the reshape operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise,
 * output the original matrix.
 * Example 1:
 *   Input: mat = [[1,2],[3,4]], r = 1, c = 4
 *   Output: [[1,2,3,4]]
 * Example 2:
 *   Input: mat = [[1,2],[3,4]], r = 2, c = 4
 *   Output: [[1,2],[3,4]]
 * Constraints:
 *   m == mat.length
 *   n == mat[i].length
 *   1 <= m, n <= 100
 *   -1000 <= mat[i][j] <= 1000
 *   1 <= r, c <= 300
 */
public class LC0566 {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if (nums == null || nums.length == 0 || nums[0].length == 0) return null;
        if (r * c > nums.length * nums[0].length) return nums;

        int[][] result = new int[r][c];
        int[] newRow = new int[c];
        int colCounter = 0;
        int rowCounter = 0;

        for (int col = 0; col < nums.length; col++) {
            for (int row = 0; row < nums[0].length; row++) {
                newRow[colCounter] = nums[col][row];
                colCounter++;

                if (colCounter == c) {
                    colCounter = 0;
                    result[rowCounter] = newRow;
                    newRow = new int[c];
                    rowCounter++;
                }
            }
        }

        return result;
    }
}
