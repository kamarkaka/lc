package com.kamarkaka.leetcode;

/***
 * 311. Sparse Matrix Multiplication
 * Given two sparse matrices mat1 of size m x k and mat2 of size k x n, return the result of mat1 x mat2. You may assume
 * that multiplication is always possible.
 * Example 1:
 *   Input: mat1 = [[1,0,0],[-1,0,3]], mat2 = [[7,0,0],[0,0,0],[0,0,1]]
 *   Output: [[7,0,0],[-7,0,3]]
 * Example 2:
 *   Input: mat1 = [[0]], mat2 = [[0]]
 *   Output: [[0]]
 * Constraints:
 *   m == mat1.length
 *   k == mat1[i].length == mat2.length
 *   n == mat2[i].length
 *   1 <= m, n, k <= 100
 *   -100 <= mat1[i][j], mat2[i][j] <= 100
 */
public class LC0311 {
    public int[][] multiply(int[][] A, int[][] B) {
        int m = A.length, n = B[0].length, l = A[0].length;
        int[][] result = new int[m][n];

        boolean[] rowsA = new boolean[m];
        boolean[] colsB = new boolean[n];

        for (int i = 0; i < m; i++) rowsA[i] = true;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < l; j++) {
                if (A[i][j] != 0) {
                    rowsA[i] = false;
                    break;
                }
            }
        }

        for (int i = 0; i < n; i++) colsB[i] = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < l; j++) {
                if (B[j][i] != 0) {
                    colsB[i] = false;
                    break;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rowsA[i] || colsB[j]) result[i][j] = 0;
                else {
                    for (int k = 0; k < l; k++) {
                        result[i][j] += A[i][k] * B[k][j];
                    }
                }
            }
        }

        return result;
    }
}
