package com.kamarkaka.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/***
 * 317. Shortest Distance from All Buildings
 * You are given an m x n grid grid of values 0, 1, or 2, where:
 *   each 0 marks an empty land that you can pass by freely,
 *   each 1 marks a building that you cannot pass through, and
 *   each 2 marks an obstacle that you cannot pass through.
 * You want to build a house on an empty land that reaches all buildings in the shortest total travel distance. You can
 * only move up, down, left, and right.
 * Return the shortest travel distance for such a house. If it is not possible to build such a house according to the
 * above rules, return -1.
 * The total travel distance is the sum of the distances between the houses of the friends and the meeting point.
 * The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 * Example 1:
 *   Input: grid = [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 *   Output: 7
 *   Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2).
 *   The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal.
 *   So return 7.
 * Example 2:
 *   Input: grid = [[1,0]]
 *   Output: 1
 * Example 3:
 *   Input: grid = [[1]]
 *   Output: -1
 * Constraints:
 *   m == grid.length
 *   n == grid[i].length
 *   1 <= m, n <= 50
 *   grid[i][j] is either 0, 1, or 2.
 *   There will be at least one building in the grid.
 */
public class LC0317 {
   // Next four directions.
   private static final int DIRS[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

   public int shortestDistance(int[][] grid) {
      int rows = grid.length;
      int cols = grid[0].length;

      // Total Mtrix to store total distance sum for each empty cell.
      int[][] total = new int[rows][cols];

      int emptyLandValue = 0;
      int minDist = Integer.MAX_VALUE;

      for (int row = 0; row < rows; ++row) {
         for (int col = 0; col < cols; ++col) {

            // Start a BFS from each house.
            if (grid[row][col] == 1) {
               minDist = Integer.MAX_VALUE;

               // Use a queue to perform a BFS, starting from the cell at (r, c).
               Queue<int[]> q = new LinkedList<>();
               q.offer(new int[]{ row, col });

               int steps = 1;

               while (!q.isEmpty()) {
                  for (int level = q.size(); level > 0; --level) {
                     int[] curr = q.poll();

                     for (int[] dir : DIRS) {
                        int nextRow = curr[0] + dir[0];
                        int nextCol = curr[1] + dir[1];

                        // For each cell with the value equal to empty land value
                        // add distance and decrement the cell value by 1.
                        if (nextRow >= 0 && nextRow < rows &&
                           nextCol >= 0 && nextCol < cols &&
                           grid[nextRow][nextCol] == emptyLandValue) {
                           grid[nextRow][nextCol]--;
                           total[nextRow][nextCol] += steps;

                           q.offer(new int[]{ nextRow, nextCol });
                           minDist = Math.min(minDist, total[nextRow][nextCol]);
                        }
                     }
                  }
                  steps++;

               }

               // Decrement empty land value to be searched in next iteration.
               emptyLandValue--;
            }
         }
      }

      return minDist == Integer.MAX_VALUE ? -1 : minDist;
   }
}
