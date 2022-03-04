package com.kamarkaka;

/***
 * 64. Minimum Path Sum
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
 * Note: You can only move either down or right at any point in time.
 *
 * Example 1:
 *    Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
 *    Output: 7
 *    Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
 *
 * Example 2:
 *    Input: grid = [[1,2,3],[4,5,6]]
 *    Output: 12
 *
 * Constraints:
 *    m == grid.length
 *    n == grid[i].length
 *    1 <= m, n <= 200
 *    0 <= grid[i][j] <= 100
 */
public class LC0064 {
   public int minPathSum(int[][] grid) {
      int rows = grid.length;
      int cols = grid[0].length;

      int[] dp = new int[cols];
      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < cols; j++) {
            if (i == 0) {
               if (j == 0) {
                  dp[j] = grid[i][j];
               } else {
                  dp[j] = grid[i][j] + dp[j-1];
               }
            } else {
               if (j == 0) {
                  dp[j] = grid[i][j] + dp[j];
               } else {
                  dp[j] = grid[i][j] + Math.min(dp[j], dp[j-1]);
               }
            }
         }
      }
      return dp[cols-1];
   }
}
