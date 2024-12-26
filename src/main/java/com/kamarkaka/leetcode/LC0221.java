package com.kamarkaka.leetcode;

/***
 * 221. Maximal Square
 * Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its
 * area.
 * Example 1:
 *   Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 *   Output: 4
 * Example 2:
 *   Input: matrix = [["0","1"],["1","0"]]
 *   Output: 1
 * Example 3:
 *   Input: matrix = [["0"]]
 *   Output: 0
 * Constraints:
 *   m == matrix.length
 *   n == matrix[i].length
 *   1 <= m, n <= 300
 *   matrix[i][j] is '0' or '1'.
 */
public class LC0221 {
    public int maximalSquare(char[][] matrix) {
        int ret = '0';

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != '1') continue;
                if (ret == '0') ret = '1';
                if (i - 1 < 0 || j - 1 < 0) continue;
                int val = getval(i, j, matrix) + 1;
                ret = Math.max(ret, val);
                matrix[i][j] = (char) val;
            }
        }

        return (ret - '0') * (ret - '0');
    }

    public int getval(int i,int j, char[][] matrix) {
        return Math.min(
                Math.min(
                        matrix[i - 1][j],
                        matrix[i][j - 1]
                ),
                matrix[i - 1][j - 1]
        );
    }
}
