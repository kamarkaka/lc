package com.kamarkaka.leetcode;

import com.kamarkaka.common.Utilities;

import java.util.LinkedList;
import java.util.Queue;

/***
 * 542. 01 Matrix
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 * The distance between two adjacent cells is 1.
 *
 * Example 1:
 *    Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
 *    Output: [[0,0,0],[0,1,0],[0,0,0]]
 *
 * Example 2:
 *    Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
 *    Output: [[0,0,0],[0,1,0],[1,2,1]]
 *
 * Constraints:
 *    m == mat.length
 *    n == mat[i].length
 *    1 <= m, n <= 10^4
 *    1 <= m * n <= 10^4
 *    mat[i][j] is either 0 or 1.
 *    There is at least one 0 in mat.
 */
public class LC0542 {
   int[][] mat;
   int rows;
   int cols;
   int[] dr = new int[] {-1,1,0,0};
   int[] dc = new int[] {0,0,-1,1};
   public int[][] updateMatrix(int[][] mat) {
      this.mat = mat;
      this.rows = mat.length;
      this.cols = mat[0].length;

      Queue<int[]> queue = new LinkedList<>();

      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < cols; j++) {
            if (mat[i][j] == 0) {
               queue.add(new int[] {i, j});
            } else {
               mat[i][j] = -1;
            }
         }
      }

      while (!queue.isEmpty()) {
         int[] pos = queue.poll();

         for (int i = 0; i < dr.length; i++) {
            int row = pos[0] + dr[i];
            int col = pos[1] + dc[i];

            if (0 > row || row >= rows) continue;
            if (0 > col || col >= cols) continue;
            if (mat[row][col] != -1) continue;

            mat[row][col] = mat[pos[0]][pos[1]] + 1;
            queue.add(new int[] {row, col});
         }
      }

      return mat;
   }

   public static void run() {
      LC0542 sol = new LC0542();
      Utilities.print(sol.updateMatrix(new int[][] {
         {0,0,0},
         {0,1,0},
         {1,1,1}
      }));
   }
}
