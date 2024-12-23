package com.kamarkaka.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/***
 * 286. Walls and Gates
 * You are given an m x n grid rooms initialized with these three possible values.
 *    -1 A wall or an obstacle.
 *    0 A gate.
 *    INF Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
 *
 * Example 1:
 *    Input: rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
 *    Output: [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
 *
 * Example 2:
 *    Input: rooms = [[-1]]
 *    Output: [[-1]]
 *
 * Constraints:
 *    m == rooms.length
 *    n == rooms[i].length
 *    1 <= m, n <= 250
 *    rooms[i][j] is -1, 0, or 2^31 - 1.
 */
public class LC0286 {
   private static final int EMPTY = Integer.MAX_VALUE;
   private static final int GATE = 0;
   private static final int[][] DIRS = new int[][] {{1,0},{-1,0},{0,1},{0,-1}};

   public void wallsAndGates(int[][] rooms) {
      if (rooms == null || rooms.length == 0 || rooms[0].length == 0) return;

      int rows = rooms.length;
      int cols = rooms[0].length;
      Queue<int[]> queue = new LinkedList<>();
      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < cols; j++) {
            if (rooms[i][j] == GATE) {
               queue.add(new int[] {i, j});
            }
         }
      }

      while (!queue.isEmpty()) {
         int[] point = queue.poll();
         int r = point[0];
         int c = point[1];
         for (int[] dir : DIRS) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if (nr < 0 || nr >= rows || nc < 0 || nc >= cols || rooms[nr][nc] != EMPTY) continue;

            rooms[nr][nc] = rooms[r][c] + 1;
            queue.add(new int[] {nr, nc});
         }
      }
   }
}
