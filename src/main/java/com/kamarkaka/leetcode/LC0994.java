package com.kamarkaka.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/***
 * 994. Rotting Oranges
 * You are given an m x n grid where each cell can have one of three values:
 *    0 representing an empty cell,
 *    1 representing a fresh orange, or
 *    2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible,
 * return -1.
 * Example 1:
 *   Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 *   Output: 4
 * Example 2:
 *   Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
 *   Output: -1
 *   Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens
 *   4-directionally.
 * Example 3:
 *   Input: grid = [[0,2]]
 *   Output: 0
 *   Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 * Constraints:
 *   m == grid.length
 *   n == grid[i].length
 *   1 <= m, n <= 10
 *   grid[i][j] is 0, 1, or 2.
 */
public class LC0994 {
   private static int[][] DIRS = new int[][] {{0,1},{0,-1},{1,0},{-1,0}};
   public int orangesRotting(int[][] grid) {
      int m = grid.length, n = grid[0].length;
      Queue<Point> rottenQueue = new LinkedList<>();

      int freshCount = 0;
      for (int i = 0; i < m; i++) {
         for (int j = 0; j < n; j++) {
            if (grid[i][j] == 1) {
               freshCount++;
            }
            else if (grid[i][j] == 2) {
               rottenQueue.add(new Point(i, j));
            }
         }
      }

      if (freshCount == 0) return 0;
      if (rottenQueue.isEmpty()) return -1;

      int timer = 0;
      //bfs
      Queue<Point> nextQueue;
      while (!rottenQueue.isEmpty()) {
         nextQueue = new LinkedList<>();
         while (!rottenQueue.isEmpty()) {
            Point rottenOrange = rottenQueue.poll();
            for (int[] dir : DIRS) {
               int x = rottenOrange.x + dir[0];
               int y = rottenOrange.y + dir[1];
               if (x < 0 || x >= m || y < 0 || y >= n) {}
               else if (grid[x][y] != 1) {}
               else {
                  grid[x][y] = 2;
                  freshCount--;
                  nextQueue.add(new Point(x,y));
               }
            }
         }
         rottenQueue = nextQueue;
         if (!rottenQueue.isEmpty()) timer++;
      }
      return freshCount == 0 ? timer : -1;
   }

   class Point {
      int x;
      int y;

      public Point(int x, int y) {
         this.x = x;
         this.y = y;
      }

      @Override
      public boolean equals(Object o) {
         if (o == null || o.getClass() != getClass()) return false;
         Point that = (Point) o;
         return this.x == that.x && this.y == that.y;
      }
   }

   public static void run() {
      LC0994 solution = new LC0994();
      System.out.println(solution.orangesRotting(new int[][] {{2,1,1},{1,1,0},{0,1,1}}));
   }
}
