package com.kamarkaka.leetcode;

/***
 * 63. Unique Paths II
 * You are given an m x n integer array grid. There is a robot initially located at the top-left corner(i.e.,
 * grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move
 * either down or right at any point in time.
 * An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot include any
 * square that is an obstacle.
 * Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 * The testcases are generated so that the answer will be less than or equal to 2 * 10^9.
 * Example 1:
 *   Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 *   Output: 2
 *   Explanation: There is one obstacle in the middle of the 3x3 grid above.
 *   There are two ways to reach the bottom-right corner:
 *   1. Right -> Right -> Down -> Down
 *   2. Down -> Down -> Right -> Right
 * Example 2:
 *   Input: obstacleGrid = [[0,1],[0,0]]
 *   Output: 1
 * Constraints:
 *   m == obstacleGrid.length
 *   n == obstacleGrid[i].length
 *   1 <= m, n <= 100
 *   obstacleGrid[i][j] is 0 or 1.
 */
public class LC0063 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) return 0;

        int rows = obstacleGrid.length, cols = obstacleGrid[0].length;
        int[][] dp = new int[rows][cols];

        boolean unreachable = obstacleGrid[0][0] == 1;
        if (unreachable) return 0;

        dp[0][0] = 1;

        for (int i = 1; i < cols; i++) {
            if (unreachable) {
                dp[0][i] = 0;
            } else if (obstacleGrid[0][i] == 1) {
                unreachable = true;
                dp[0][i] = 0;
            } else {
                dp[0][i] = 1;
            }
        }

        unreachable = false;
        for (int i = 1; i < rows; i++) {
            if (unreachable) {
                dp[i][0] = 0;
            } else if (obstacleGrid[i][0] == 1) {
                unreachable = true;
                dp[i][0] = 0;
            } else {
                dp[i][0] = 1;
            }
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[rows - 1][cols - 1];
    }
}
