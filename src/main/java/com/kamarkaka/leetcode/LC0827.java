package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 * 827. Making A Large Island
 * You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
 * Return the size of the largest island in grid after applying this operation.
 * An island is a 4-directionally connected group of 1s.
 *
 * Example 1:
 *    Input: grid = [[1,0],[0,1]]
 *    Output: 3
 *    Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
 *
 * Example 2:
 *    Input: grid = [[1,1],[1,0]]
 *    Output: 4
 *    Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
 *
 * Example 3:
 *    Input: grid = [[1,1],[1,1]]
 *    Output: 4
 *    Explanation: Can't change any 0 to 1, only one island with area = 4.
 *
 * Constraints:
 *    n == grid.length
 *    n == grid[i].length
 *    1 <= n <= 500
 *    grid[i][j] is either 0 or 1.
 */
public class LC0827 {
   private static final int[] dr = new int[]{-1, 0, 1, 0};
   private static final int[] dc = new int[]{0, -1, 0, 1};
   private int[][] grid;
   private int N;

   public int largestIsland(int[][] grid) {
      this.grid = grid;
      this.N = grid.length;

      int index = 2;
      int[] area = new int[N * N + 2];
      for (int r = 0; r < N; r++) {
         for (int c = 0; c < N; c++) {
            if (grid[r][c] == 1) area[index] = dfs(r, c, index++);
         }
      }

      int ans = 0;
      for (int x : area) ans = Math.max(ans, x);
      for (int r = 0; r < N; r++) {
         for (int c = 0; c < N; c++) {
            if (grid[r][c] == 0) {
               Set<Integer> seen = new HashSet();
               for (Integer move : neighbors(r, c)) {
                  if (grid[move / N][move % N] > 1) {
                     seen.add(grid[move / N][move % N]);
                  }
               }

               int bns = 1;
               for (int i : seen) bns += area[i];
               ans = Math.max(ans, bns);
            }
         }
      }

      return ans;
   }

   private int dfs(int r, int c, int index) {
      int ans = 1;
      grid[r][c] = index;
      for (Integer move: neighbors(r, c)) {
         if (grid[move / N][move % N] == 1) {
            grid[move / N][move % N] = index;
            ans += dfs(move / N, move % N, index);
         }
      }

      return ans;
   }

   private List<Integer> neighbors(int r, int c) {
      List<Integer> ans = new ArrayList();
      for (int k = 0; k < 4; ++k) {
         int nr = r + dr[k];
         int nc = c + dc[k];
         if (0 <= nr && nr < N && 0 <= nc && nc < N) ans.add(nr * N + nc);
      }

      return ans;
   }
}
