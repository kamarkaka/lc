package main.java.com.kamarkaka;

/***
 * 562. Longest Line of Consecutive One in Matrix
 * Given an m x n binary matrix mat, return the length of the longest line of consecutive one in the matrix.
 * The line could be horizontal, vertical, diagonal, or anti-diagonal.
 *
 * Example 1:
 *   Input: mat = [[0,1,1,0],[0,1,1,0],[0,0,0,1]]
 *   Output: 3
 *
 * Example 2:
 *   Input: mat = [[1,1,1,1],[0,1,1,0],[0,0,0,1]]
 *   Output: 4
 *
 * Constraints:
 *   m == mat.length
 *   n == mat[i].length
 *   1 <= m, n <= 10^4
 *   1 <= m * n <= 10^4
 *   mat[i][j] is either 0 or 1.
 */
public class LC0562 {
   public int longestLine(int[][] mat) {
      int maxLen = 0;
      int[][][] dp = new int[mat[0].length][4][2]; // 0:from left, 1: from topleft, 2: from top, 3: from topright
      for (int i = 0; i < dp.length; i++) {
         if (i == 0) {
            dp[i][0][0] = mat[0][i];
            dp[i][0][1] = mat[0][i];
         } else {
            dp[i][0][0] = mat[0][i] == 0 ? dp[i-1][0][0] : Math.max(dp[i-1][0][0], dp[i-1][0][1] + 1);
            dp[i][0][1] = mat[0][i] == 0 ? 0 : dp[i-1][0][1] + 1;
         }

         dp[i][1][0] = mat[0][i];
         dp[i][1][1] = mat[0][i];

         dp[i][2][0] = mat[0][i];
         dp[i][2][1] = mat[0][i];

         dp[i][3][0] = mat[0][i];
         dp[i][3][1] = mat[0][i];
      }

      for (int i = 0; i < dp.length; i++) {
         for (int j = 0; j < 4; j++) {
            if (dp[i][j][0] > maxLen) maxLen = dp[i][j][0];
         }
      }

      for (int row = 1; row < mat.length; row++) {
         int[][][] newDp = new int[mat[0].length][4][2];
         for (int col = 0; col < mat[0].length; col++) {
            if (col == 0) {
               newDp[col][0][0] = mat[row][col];
               newDp[col][0][1] = mat[row][col];
            } else {
               newDp[col][0][0] = mat[row][col] == 0 ? newDp[col-1][0][0] : Math.max(newDp[col-1][0][0], newDp[col-1][0][1] + 1);
               newDp[col][0][1] = mat[row][col] == 0 ? 0 : newDp[col-1][0][1] + 1;
            }

            if (col == 0) {
               newDp[col][1][0] = mat[row][col];
               newDp[col][1][1] = mat[row][col];
            } else {
               newDp[col][1][0] = mat[row][col] == 0 ? dp[col-1][1][0] : Math.max(dp[col-1][1][0], dp[col-1][1][1] + 1);
               newDp[col][1][1] = mat[row][col] == 0 ? 0 : dp[col-1][1][1] + 1;
            }

            newDp[col][2][0] = mat[row][col] == 0 ? dp[col][2][0] : Math.max(dp[col][2][0], dp[col][2][1] + 1);
            newDp[col][2][1] = mat[row][col] == 0 ? 0 : dp[col][2][1] + 1;

            if (col == mat[row].length - 1) {
               newDp[col][3][0] = mat[row][col];
               newDp[col][3][1] = mat[row][col];
            } else {
               newDp[col][3][0] = mat[row][col] == 0 ? dp[col+1][3][0] : Math.max(dp[col+1][3][0], dp[col+1][3][1] + 1);
               newDp[col][3][1] = mat[row][col] == 0 ? 0 : dp[col+1][3][1] + 1;
            }
         }

         dp = newDp;
         for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < 4; j++) {
               if (dp[i][j][0] > maxLen) maxLen = dp[i][j][0];
            }
         }
      }

      return maxLen;
   }
}
