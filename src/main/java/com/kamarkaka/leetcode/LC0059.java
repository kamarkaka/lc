package com.kamarkaka.leetcode;

/***
 * 59. Spiral Matrix II
 * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
 * Example 1:
 *   Input: n = 3
 *   Output: [[1,2,3],[8,9,4],[7,6,5]]
 * Example 2:
 *   Input: n = 1
 *   Output: [[1]]
 * Constraints:
 *   1 <= n <= 20
 */
public class LC0059 {
    public int[][] generateMatrix(int n) {
        if (n <= 0) return null;

        int[][] result = new int[n][n];
        int startNum = 1, steps = n, si = 0, sj = 0;
        while (steps > 1) {
            startNum = fillMatrix(result, startNum, steps, si, sj);
            steps -= 2;
            si++;
            sj++;
        }

        if (steps == 1) {
            result[si][sj] = startNum;
        }

        return result;
    }

    private int fillMatrix(int[][] matrix, int startNum, int steps, int si, int sj) {
        for (int j = 0; j < steps; j++) {
            matrix[si][sj + j] = startNum;
            startNum++;
        }

        for (int i = 1; i < steps; i++) {
            matrix[si + i][sj + steps - 1] = startNum;
            startNum++;
        }

        for (int j = steps - 2; j >= 0; j--) {
            matrix[si + steps - 1][sj + j] = startNum;
            startNum++;
        }

        for (int i = steps - 2; i >= 1; i--) {
            matrix[si + i][sj] = startNum;
            startNum++;
        }

        return startNum;
    }
}
