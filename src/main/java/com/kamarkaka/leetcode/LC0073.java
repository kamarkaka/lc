package com.kamarkaka.leetcode;

/***
 * 73. Set Matrix Zeroes
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
 * You must do it in place.
 * Example 1:
 *   Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
 *   Output: [[1,0,1],[0,0,0],[1,0,1]]
 * Example 2:
 *   Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 *   Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 * Constraints:
 *   m == matrix.length
 *   n == matrix[0].length
 *   1 <= m, n <= 200
 *   -2^31 <= matrix[i][j] <= 2^31 - 1
 * Follow up:
 *   A straightforward solution using O(mn) space is probably a bad idea.
 *   A simple improvement uses O(m + n) space, but still not the best solution.
 *   Could you devise a constant space solution?
 */
public class LC0073 {
    public void setZeroes(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        boolean rowFlag = false, colFlag = false;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && matrix[i][j] == 0) rowFlag = true;
                if (j == 0 && matrix[i][j] == 0) colFlag = true;

                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) matrix[i][j] = 0;
            }
        }

        if (rowFlag) {
            for (int i = 0; i < col; i++) {
                matrix[0][i] = 0;
            }
        }

        if (colFlag) {
            for (int i = 0; i < row; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
