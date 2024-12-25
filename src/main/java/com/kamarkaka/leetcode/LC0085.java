package com.kamarkaka.leetcode;

/***
 * 85. Maximal Rectangle
 * Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return
 * its area.
 * Example 1:
 *   Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 *   Output: 6
 *   Explanation: The maximal rectangle is shown in the above picture.
 * Example 2:
 *   Input: matrix = [["0"]]
 *   Output: 0
 * Example 3:
 *   Input: matrix = [["1"]]
 *   Output: 1
 * Constraints:
 *   rows == matrix.length
 *   cols == matrix[i].length
 *   1 <= row, cols <= 200
 *   matrix[i][j] is '0' or '1'.
 */
public class LC0085 {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int result = 0, rows = matrix.length, cols = matrix[0].length;

        int[][] straights = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (j == 0) {
                    straights[i][j] = matrix[i][j] == '1' ? 1 : 0;
                } else {
                    straights[i][j] = matrix[i][j] == '1' ? straights[i][j - 1] + 1 : 0;
                }

                if (straights[i][j] > result) result = straights[i][j];
            }
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int width = straights[i][j];
                int height = 1;
                for (int k = i - 1; k >= 0; k--) {
                    if (straights[k][j] == 0) {
                        break;
                    } else {
                        height++;
                        if (straights[k][j] < width) width = straights[k][j];
                    }
                    if (width * height > result) result = width * height;
                }
            }
        }

        return result;
    }
}
