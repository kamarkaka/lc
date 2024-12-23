package com.kamarkaka.leetcode;

/***
 * 1240. Tiling a Rectangle with the Fewest Squares
 * Given a rectangle of size n x m, return the minimum number of integer-sided squares that tile the rectangle.
 *
 * Example 1:
 *    Input: n = 2, m = 3
 *    Output: 3
 *    Explanation: 3 squares are necessary to cover the rectangle.
 *       2 (squares of 1x1)
 *       1 (square of 2x2)
 *
 * Example 2:
 *    Input: n = 5, m = 8
 *    Output: 5
 *
 * Example 3:
 *    Input: n = 11, m = 13
 *    Output: 6
 *
 * Constraints:
 *    1 <= n, m <= 13
 */
public class LC1240 {
   public int tilingRectangle(int n, int m) {
      if (n == m) return 1;
      if (n > m) return tilingRectangle(m, n);

      int[][] dp = new int[n+1][m+1];
      return dfs(n, m, dp);
   }

   private int dfs(int n, int m, int[][] dp) {
      if (n > m) return dfs(m, n, dp);
      if (n == 0) return 0;
      if (n == 1) return m;
      if (n == m) return 1;
      if (dp[n][m] > 0) return dp[n][m];

      int minCount = Integer.MAX_VALUE;
      for (int i = 1; i <= n; i++) {
         minCount = Math.min(minCount, 1 + dfs(n-i, m, dp) + dfs(i, m-i, dp));
         minCount = Math.min(minCount, 1 + dfs(n-i, i, dp) + dfs(n, m-i, dp));

         for (int j = n - i + 1; j < m - i && j < n; j++) {
            int count = 2 + dfs(n-i, m-j, dp) + dfs(i+j-n, m-i-j, dp) + dfs(n-j, m-i, dp);
            minCount = Math.min(minCount, count);
         }
      }
      dp[n][m] = minCount;
      return minCount;
   }
}
