package com.kamarkaka.leetcode;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

/***
 * 1293. Shortest Path in a Grid with Obstacles Elimination
 *   You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle). You can move up, down, left, or right from and to an empty cell in one step.
 *   Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1) given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.
 *
 * Example 1:
 *   Input: grid = [[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]], k = 1
 *   Output: 6
 *   Explanation:
 *   The shortest path without eliminating any obstacle is 10.
 *   The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
 *
 * Example 2:
 *   Input: grid = [[0,1,1],[1,1,1],[1,0,0]], k = 1
 *   Output: -1
 *   Explanation: We need to eliminate at least two obstacles to find such a walk.
 *
 * Constraints:
 *   m == grid.length
 *   n == grid[i].length
 *   1 <= m, n <= 40
 *   1 <= k <= m * n
 *   grid[i][j] is either 0 or 1.
 *   grid[0][0] == grid[m - 1][n - 1] == 0
 */
public class LC1293 {
   public int shortestPath(int[][] grid, int k) {
      int rows = grid.length, cols = grid[0].length;
      int[] target = {rows - 1, cols - 1};

      // if we have sufficient quotas to eliminate the obstacles in the worst case,
      // then the shortest distance is the Manhattan distance.
      if (k >= rows + cols - 2) {
         return rows + cols - 2;
      }

      Deque<StepState> queue = new LinkedList<>();
      HashSet<StepState> seen = new HashSet<>();

      // (steps, row, col, remaining quota to eliminate obstacles)
      StepState start = new StepState(0, 0, 0, k);
      queue.addLast(start);
      seen.add(start);

      while (!queue.isEmpty()) {
         StepState curr = queue.pollFirst();

         // we reach the target here
         if (target[0] == curr.row && target[1] == curr.col) {
            return curr.steps;
         }

         int[] nextSteps = {curr.row, curr.col + 1, curr.row + 1, curr.col,
               curr.row, curr.col - 1, curr.row - 1, curr.col};

         // explore the four directions in the next step
         for (int i = 0; i < nextSteps.length; i += 2) {
            int nextRow = nextSteps[i];
            int nextCol = nextSteps[i + 1];

            // out of the boundary of grid
            if (0 > nextRow || nextRow == rows || 0 > nextCol || nextCol == cols) {
               continue;
            }

            int nextElimination = curr.k - grid[nextRow][nextCol];
            StepState nextState = new StepState(curr.steps + 1, nextRow, nextCol, nextElimination);

            // add the next move in the queue if it qualifies.
            if (nextElimination >= 0 && !seen.contains(nextState)) {
               seen.add(nextState);
               queue.addLast(nextState);
            }
         }
      }

      return -1;
   }

   class StepState {
      /**
       * data object to keep the state info for each step:
       * <steps, row, col, remaining_eliminations>
       */
      public int steps, row, col, k;

      public StepState(int steps, int row, int col, int k) {
         this.steps = steps;
         this.row = row;
         this.col = col;
         this.k = k;
      }

      @Override
      public int hashCode() {
         // needed when we put objects into any container class
         return (this.row + 1) * (this.col + 1) * this.k;
      }

      @Override
      public boolean equals(Object other) {
         /**
          * only (row, col, k) matters as the state info
          */
         if (!(other instanceof StepState)) {
            return false;
         }
         StepState newState = (StepState) other;
         return (this.row == newState.row) && (this.col == newState.col) && (this.k == newState.k);
      }

      @Override
      public String toString() {
         return String.format("%d %d %d", this.row, this.col, this.k);
      }
   }

   public static void run() {
      LC1293 solution = new LC1293();
      System.out.print(solution.shortestPath(new int[][] {
         {0,0,0},{1,1,0},{0,0,0},{0,1,1},{0,0,0}
      }, 1));
   }
}
