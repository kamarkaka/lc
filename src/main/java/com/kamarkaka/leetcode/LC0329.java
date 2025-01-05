package com.kamarkaka.leetcode;

/***
 * 329. Longest Increasing Path in a Matrix
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 * From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move
 * outside the boundary (i.e., wrap-around is not allowed).
 * Example 1:
 *   Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
 *   Output: 4
 *   Explanation: The longest increasing path is [1, 2, 6, 9].
 * Example 2:
 *   Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
 *   Output: 4
 *   Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 * Example 3:
 * Input: matrix = [[1]]
 * Output: 1
 * Constraints:
 *   m == matrix.length
 *   n == matrix[i].length
 *   1 <= m, n <= 200
 *   0 <= matrix[i][j] <= 2^31 - 1
 */
public class LC0329 {
   private static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
   private int m, n;

   public int longestIncreasingPath(int[][] matrix) {
      if (matrix.length == 0) return 0;
      m = matrix.length; n = matrix[0].length;
      int[][] cache = new int[m][n];
      int ans = 0;
      for (int i = 0; i < m; ++i)
         for (int j = 0; j < n; ++j)
            ans = Math.max(ans, dfs(matrix, i, j, cache));
      return ans;
   }

   private int dfs(int[][] matrix, int i, int j, int[][] cache) {
      if (cache[i][j] != 0) return cache[i][j];
      for (int[] d : DIRS) {
         int x = i + d[0], y = j + d[1];
         if (0 <= x && x < m && 0 <= y && y < n && matrix[x][y] > matrix[i][j]) {
            cache[i][j] = Math.max(cache[i][j], dfs(matrix, x, y, cache));
         }
      }
      return ++cache[i][j];
   }
}
