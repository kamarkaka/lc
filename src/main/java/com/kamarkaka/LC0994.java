package com.kamarkaka;

import java.util.LinkedList;
import java.util.Queue;

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
