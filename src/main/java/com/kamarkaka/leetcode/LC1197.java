package com.kamarkaka.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/***
 * 1197. Minimum Knight Moves
 * In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
 * A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
 * Return the minimum number of steps needed to move the knight to the square [x, y]. It is guaranteed the answer exists.
 *
 * Example 1:
 *    Input: x = 2, y = 1
 *    Output: 1
 *    Explanation: [0, 0] → [2, 1]
 *
 * Example 2:
 *    Input: x = 5, y = 5
 *    Output: 4
 *    Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
 *
 * Constraints:
 *    -300 <= x, y <= 300
 *    0 <= |x| + |y| <= 300
 */
public class LC1197 {
   private final static int[][] DIRS = new int[][]{
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
