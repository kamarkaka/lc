package com.kamarkaka.twosigma;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/***
 * https://leetcode.com/discuss/interview-question/231726/image-matching
 * Images are stored in the form of a grid.
 * Image recognition is possible by comparing grids of two images and checking if they have any matching regions.
 * You are given two grids where each cell of a grid contains is either 0 or 1.
 * If two cells share a side then they are adjacent.
 * Cells that contain 1s form a connected region if any cell of that region can be reached by moving through the adjacent cells that contain 1.
 * Overlay the first grid onto the second grid and if a region in the first grid completely matches a region in the second grid, the regions are matched
 * Count total number of such matched regions in the second grid.
 *
 * Example 1
 *    Input: G1: 111  G2 111
 *               100     100
 *               100     101
 *    Output: 1
 *
 * Example 2
 *    Input: G1: 111  G2 111
 *               101     100
 *               100     101
 *    Output: 0
 *
 * Constraints:
 *    1 <= n <= 100
 *    1 <= |G1[i]|, |G2[i]| <= 100
 *    Grid cells contain only 0 or 1
 */
public class ImageMatching {
   private static final int[][] dirs = new int[][] {{1,0},{0,1},{-1,0},{0,-1}};
   private int rows;
   private int cols;

   public int countMatchingRegions(int[][] graph1, int[][] graph2) {
      rows = graph1.length;
      cols = graph1[0].length;
      int count = 0;

      boolean[][] visited1 = new boolean[rows][cols];
      for (boolean[] row : visited1) {
         Arrays.fill(row, false);
      }
      boolean[][] visited2 = new boolean[rows][cols];
      for (boolean[] row : visited2) {
         Arrays.fill(row, false);
      }

      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < cols; j++) {
            if (graph1[i][j] == 0 || graph2[i][j] == 0) continue;
            if (visited1[i][j] || visited2[i][j]) continue;

            int regionSize = bfs(i, j, graph1, graph2, visited1);
            if (regionSize > 0) count++;
         }
      }

      return count;
   }

   private int bfs(int i, int j, int[][] src, int[][] ref, boolean[][] visited) {
      int size = 0;

      Queue<int[]> queue = new LinkedList<>();
      queue.add(new int[] {i, j});

      while (!queue.isEmpty()) {
         int[] p = queue.poll();
         visited[p[0]][p[1]] = true;
         size++;

         for (int[] dir : dirs) {
            int nr = p[0] + dir[0];
            int nc = p[1] + dir[1];
            if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) continue;
            if (visited[nr][nc]) continue;
            if (src[nr][nc] != ref[nr][nc]) return -1;
            if (src[nr][nc] != 1) continue;

            queue.add(new int[] {nr, nc});
         }
      }

      return size;
   }

   public static void run() {
      ImageMatching sol = new ImageMatching();
      System.out.println(sol.countMatchingRegions(
         new int[][] {{1,1,1},{1,0,1},{1,0,0}},
         new int[][] {{1,1,1},{1,0,0},{1,0,1}}
      ));
   }
}
