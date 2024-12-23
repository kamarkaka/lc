package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.List;

/***
 * 305. Number of Islands II
 * You are given an empty 2D binary grid grid of size m x n. The grid represents a map where 0's represent water and 1's represent land. Initially, all the cells of grid are water cells (i.e., all the cells are 0's).
 * We may perform an add land operation which turns the water at position into a land. You are given an array positions where positions[i] = [ri, ci] is the position (ri, ci) at which we should operate the ith operation.
 * Return an array of integers answer where answer[i] is the number of islands after turning the cell (ri, ci) into a land.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 *    Input: m = 3, n = 3, positions = [[0,0],[0,1],[1,2],[2,1]]
 *    Output: [1,1,2,3]
 *    Explanation:
 *       Initially, the 2d grid is filled with water.
 *       - Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land. We have 1 island.
 *       - Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land. We still have 1 island.
 *       - Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land. We have 2 islands.
 *       - Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land. We have 3 islands.
 *
 * Example 2:
 *    Input: m = 1, n = 1, positions = [[0,0]]
 *    Output: [1]
 *
 * Constraints:
 *    1 <= m, n, positions.length <= 10^4
 *    1 <= m * n <= 10^4
 *    positions[i].length == 2
 *    0 <= ri < m
 *    0 <= ci < n
 *
 *
 * Follow up: Could you solve it in time complexity O(k log(mn)), where k == positions.length?
 */
public class LC0305 {
   public List<Integer> numIslands2(int m, int n, int[][] positions) {
      List<Integer> ans = new ArrayList<>();
      UnionFind uf = new UnionFind(m * n);

      for (int[] pos : positions) {
         int r = pos[0], c = pos[1];
         List<Integer> overlap = new ArrayList<>();

         if (r - 1 >= 0 && uf.isValid((r - 1) * n + c)) overlap.add((r - 1) * n + c);
         if (r + 1 < m && uf.isValid((r + 1) * n + c)) overlap.add((r + 1) * n + c);
         if (c - 1 >= 0 && uf.isValid(r * n + c - 1)) overlap.add(r * n + c - 1);
         if (c + 1 < n && uf.isValid(r * n + c + 1)) overlap.add(r * n + c + 1);

         int idx = r * n + c;
         uf.setParent(idx);
         for (int i : overlap) {
            uf.union(i, idx);
         }
         ans.add(uf.getCount());
      }

      return ans;
   }

   private class UnionFind {
      int count;
      int[] parent;
      int[] rank;

      public UnionFind(int N) {
         this.count = 0;
         this.parent = new int[N];
         this.rank = new int[N];

         for (int i = 0; i < N; i++) {
            parent[i] = -1;
            rank[i] = 0;
         }
      }

      public boolean isValid(int i) {
         return parent[i] >= 0;
      }

      public void setParent(int i) {
         if (parent[i] == -1) {
            parent[i] = i;
            count++;
         }
      }

      public int find(int i) {
         if (parent[i] != i) parent[i] = find(parent[i]);
         return parent[i];
      }

      public void union(int x, int y) {
         int rootx = find(x);
         int rooty = find(y);
         if (rootx == rooty) return;

         if (rank[rootx] > rank[rooty]) {
            parent[rooty] = rootx;
         } else if (rank[rootx] < rank[rooty]) {
            parent[rootx] = rooty;
         } else {
            parent[rooty] = rootx;
            rank[rootx]++;
         }
         count--;
      }

      public int getCount() {
         return count;
      }
   }
}
