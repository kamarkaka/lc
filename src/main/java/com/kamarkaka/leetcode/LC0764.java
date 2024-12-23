package com.kamarkaka.leetcode;

import java.util.HashSet;
import java.util.Set;

/***
 * 764. Largest Plus Sign
 * You are given an integer n. You have an n x n binary grid grid with all values initially 1's except for some indices given in the array mines. The ith element of the array mines is defined as mines[i] = [xi, yi] where grid[xi][yi] == 0.
 * Return the order of the largest axis-aligned plus sign of 1's contained in grid. If there is none, return 0.
 * An axis-aligned plus sign of 1's of order k has some center grid[r][c] == 1 along with four arms of length k - 1 going up, down, left, and right, and made of 1's. Note that there could be 0's or 1's beyond the arms of the plus sign, only the relevant area of the plus sign is checked for 1's.
 *
 * Example 1:
 *    Input: n = 5, mines = [[4,2]]
 *    Output: 2
 *    Explanation: In the above grid, the largest plus sign can only be of order 2. One of them is shown.
 *
 * Example 2:
 *    Input: n = 1, mines = [[0,0]]
 *    Output: 0
 *    Explanation: There is no plus sign, so return 0.
 *
 * Constraints:
 *    1 <= n <= 500
 *    1 <= mines.length <= 5000
 *    0 <= xi, yi < n
 *    All the pairs (xi, yi) are unique.
 */
public class LC0764 {
   public int orderOfLargestPlusSign(int N, int[][] mines) {
      Set<Integer> banned = new HashSet();
      int[][] dp = new int[N][N];

      for (int[] mine: mines) {
         banned.add(mine[0] * N + mine[1]);
      }

      int res = 0, count;
      for (int row = 0; row < N; row++) {
         count = 0;
         for (int col = 0; col < N; col++) {
            count = banned.contains(row * N + col) ? 0 : count + 1;
            dp[row][col] = count;
         }

         count = 0;
         for (int col = N-1; col >= 0; col--) {
            count = banned.contains(row * N + col) ? 0 : count + 1;
            dp[row][col] = Math.min(dp[row][col], count);
         }
      }

      for (int col = 0; col < N; col++) {
         count = 0;
         for (int row = 0; row < N; row++) {
            count = banned.contains(row * N + col) ? 0 : count + 1;
            dp[row][col] = Math.min(dp[row][col], count);
         }

         count = 0;
         for (int row = N - 1; row >= 0; row--) {
            count = banned.contains(row * N + col) ? 0 : count + 1;
            dp[row][col] = Math.min(dp[row][col], count);
            res = Math.max(res, dp[row][col]);
         }
      }

      return res;
   }
}
