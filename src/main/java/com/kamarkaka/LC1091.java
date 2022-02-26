package com.kamarkaka;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/***
 * 1091. Shortest Path in Binary Matrix
 * Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.
 * A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
 *    All the visited cells of the path are 0.
 *    All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
 * The length of a clear path is the number of visited cells of this path.
 *
 * Example 1:
 *    Input: grid = [[0,1],[1,0]]
 *    Output: 2
 *
 * Example 2:
 *    Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
 *    Output: 4
 *
 * Example 3:
 *    Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
 *    Output: -1
 *
 * Constraints:
 *    n == grid.length
 *    n == grid[i].length
 *    1 <= n <= 100
 *    grid[i][j] is 0 or 1
 */
public class LC1091 {
   private static final int[][] DIRS = new int[][] {
      {0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1}
   };

   public int shortestPathBinaryMatrix(int[][] grid) {
      // Firstly, we need to check that the start and target cells are open.
      if (grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) {
         return -1;
      }

      // Set up the BFS.
      Queue<int[]> queue = new ArrayDeque<>();
      grid[0][0] = 1;
      queue.add(new int[]{0, 0});

      // Carry out the BFS
      while (!queue.isEmpty()) {
         int[] cell = queue.remove();
         int row = cell[0];
         int col = cell[1];
         int distance = grid[row][col];
         if (row == grid.length - 1 && col == grid[0].length - 1) {
            return distance;
         }
         for (int[] neighbour : getNeighbours(row, col, grid)) {
            int neighbourRow = neighbour[0];
            int neighbourCol = neighbour[1];
            queue.add(new int[]{neighbourRow, neighbourCol});
            grid[neighbourRow][neighbourCol] = distance + 1;
         }
      }

      // The target was unreachable.
      return -1;
   }

   private List<int[]> getNeighbours(int row, int col, int[][] grid) {
      List<int[]> neighbours = new ArrayList<>();
      for (int i = 0; i < DIRS.length; i++) {
         int newRow = row + DIRS[i][0];
         int newCol = col + DIRS[i][1];
         if (newRow < 0 || newCol < 0 || newRow >= grid.length || newCol >= grid[0].length ||
            grid[newRow][newCol] != 0) {
            continue;
         }
         neighbours.add(new int[]{newRow, newCol});
      }
      return neighbours;
   }

   public static void run() {
      LC1091 sol = new LC1091();
      System.out.println(sol.shortestPathBinaryMatrix(new int[][] {
         {0,0,0},{1,1,0},{1,1,1}
      }));
   }
}
