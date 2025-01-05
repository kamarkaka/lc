package com.kamarkaka.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/***
 * 1091. Shortest Path in Binary Matrix
 * Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear
 * path, return -1.
 * A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell
 * (i.e., (n - 1, n - 1)) such that:
 *   All the visited cells of the path are 0.
 *   All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge
 *   or a corner).
 * The length of a clear path is the number of visited cells of this path.
 * Example 1:
 *   Input: grid = [[0,1],[1,0]]
 *   Output: 2
 * Example 2:
 *   Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
 *   Output: 4
 * Example 3:
 *   Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
 *   Output: -1
 * Constraints:
 *   n == grid.length
 *   n == grid[i].length
 *   1 <= n <= 100
 *   grid[i][j] is 0 or 1
 */
public class LC1091 {
   public int shortestPathBinaryMatrix(int[][] grid) {
      int N = grid.length;
      // Firstly, we need to check that the start and target cells are open.
      if (grid[0][0] != 0 || grid[N - 1][N - 1] != 0) return -1;
      if (N == 1 && grid[0][0] == 0) return 1;

      // Set up the BFS.
      Queue<int[]> queue = new ArrayDeque<>();
      queue.offer(new int[]{0, 0, 1});

      // Carry out the BFS
      while (!queue.isEmpty()) {
         int[] curr = queue.poll();
         int r = curr[0], c = curr[1], dist = curr[2];

         grid[r][c] = 1;

         for (int i = r - 1; i <= r + 1; i++) {
            for (int j = c - 1; j <= c + 1; j++) {
               if (i == -1 || i == N || j == -1 || j == N || grid[i][j] == 1) continue;
               if (i == N - 1 && j == N - 1) return dist + 1;
               grid[i][j] = 1;
               queue.add(new int[] {i, j, dist + 1});
            }
         }
      }

      // The target was unreachable.
      return -1;
   }

   public static void run() {
      LC1091 sol = new LC1091();
      System.out.println(sol.shortestPathBinaryMatrix(new int[][] {
         {0,0,0},{1,1,0},{1,1,1}
      }));
   }
}
