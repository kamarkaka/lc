package com.kamarkaka.leetcode;

import java.util.PriorityQueue;

/***
 * 778. Swim in Rising Water
 * You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).
 * The rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.
 * Return the least time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).
 *
 * Example 1:
 *    Input: grid = [[0,2],[1,3]]
 *    Output: 3
 *    Explanation:
 *       At time 0, you are in grid location (0, 0).
 *       You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
 *       You cannot reach point (1, 1) until time 3.
 *       When the depth of water is 3, we can swim anywhere inside the grid.
 *
 * Example 2:
 *    Input: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
 *    Output: 16
 *    Explanation: The final route is shown.
 *       We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
 *
 * Constraints:
 *    n == grid.length
 *    n == grid[i].length
 *    1 <= n <= 50
 *    0 <= grid[i][j] < n^2
 *    Each value grid[i][j] is unique.
 */
public class LC0778 {
   public int swimInWater(int[][] grid) {
      boolean[][] visited = new boolean[grid.length][grid[0].length];
      PriorityQueue<Pair> pq = new PriorityQueue<>();
      pq.add(new Pair(0, 0, grid[0][0]));
      int ans = 0;
      while (pq.size() > 0) {
         Pair rem = pq.poll();
         if (visited[rem.i][rem.j]) continue;
         visited[rem.i][rem.j] = true;
         if (rem.wt > ans) ans = rem.wt;

         if(rem.i == grid.length - 1 && rem.j == grid[0].length - 1) return ans;

         if(rem.i - 1 >= 0) pq.add(new Pair(rem.i - 1, rem.j, grid[rem.i - 1][rem.j]));
         if(rem.i + 1 < grid.length) pq.add(new Pair(rem.i + 1, rem.j, grid[rem.i + 1][rem.j]));
         if(rem.j - 1 >= 0) pq.add(new Pair(rem.i, rem.j - 1, grid[rem.i][rem.j - 1]));
         if(rem.j + 1 < grid[0].length) pq.add(new Pair(rem.i, rem.j + 1, grid[rem.i][rem.j + 1]));
      }
      return ans;
   }

   private class Pair implements Comparable<Pair> {
      int i;
      int j;
      int wt;

      public Pair(int i, int j, int wt) {
         this.i = i;
         this.j = j;
         this.wt = wt;
      }

      public int compareTo(Pair o) {
         return this.wt - o.wt;
      }
   }
}
