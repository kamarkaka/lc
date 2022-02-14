package com.kamarkaka;

import java.util.LinkedList;
import java.util.Queue;

public class LC1197 {
   private static int[][] DIRS = new int[][]{
         {1,2},{2,1},{-1,2},{-2,1},{1,-2},{2,-1},{-1,-2},{-2,-1}
   };
   public int minKnightMoves(int x, int y) {
      if (x == 0 && y == 0) return 0;

      x = Math.abs(x);
      y = Math.abs(y);

      int[][] dp = new int[x+3][y+3];
      dp[0][1] = 3;
      dp[1][0] = 3;
      dp[1][1] = 2;

      Queue<int[]> queue = new LinkedList<>();
      queue.add(new int[]{0, 0});
      queue.add(new int[]{0, 1});
      queue.add(new int[]{1, 0});
      queue.add(new int[]{1, 1});

      while (!queue.isEmpty()) {
         int[] move = queue.poll();
         int step = dp[move[0]][move[1]];

         for (int[] dir : DIRS) {
            int dx = move[0] + dir[0];
            int dy = move[1] + dir[1];

            if (0 <= dx && dx < dp.length && 0 <= dy && dy < dp[dx].length) {
               if (dp[dx][dy] == 0 || dp[dx][dy] > step + 1) {
                  dp[dx][dy] = step + 1;
                  queue.add(new int[] {dx, dy});
               }
            }
         }
      }

      return dp[x][y];
   }

   public static void run() {
      LC1197 solution = new LC1197();
      System.out.println(solution.minKnightMoves(2, 1));
   }
}
