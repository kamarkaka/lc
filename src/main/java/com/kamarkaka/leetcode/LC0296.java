package com.kamarkaka.leetcode;

/***
 * 296. Best Meeting Point
 * Given an m x n binary grid grid where each 1 marks the home of one friend, return the minimal total travel distance.
 * The total travel distance is the sum of the distances between the houses of the friends and the meeting point.
 * The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 * Example 1:
 *   Input: grid = [
 *     [1,0,0,0,1],
 *     [0,0,0,0,0],
 *     [0,0,1,0,0]
 *   ]
 *   Output: 6
 *   Explanation: Given three friends living at (0,0), (0,4), and (2,2).
 *   The point (0,2) is an ideal meeting point, as the total travel distance of 2 + 2 + 2 = 6 is minimal.
 *   So return 6.
 * Example 2:
 *   Input: grid = [[1,1]]
 *   Output: 1
 * Constraints:
 *   m == grid.length
 *   n == grid[i].length
 *   1 <= m, n <= 200
 *   grid[i][j] is either 0 or 1.
 *   There will be at least two friends in the grid.
 */
public class LC0296 {
    public int minTotalDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] row_sum = new int[n], col_sum = new int[m];

        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j) {
                row_sum[j] += grid[i][j];
                col_sum[i] += grid[i][j];
            }

        return minDistance1D(row_sum) + minDistance1D(col_sum);
    }

    public int minDistance1D(int[] vector) {
        int i = -1, j = vector.length;
        int d = 0, left = 0, right = 0;

        while (i != j) {
            if (left < right) {
                d += left;
                left += vector[++i];
            } else {
                d += right;
                right += vector[--j];
            }
        }
        return d;
    }
}
