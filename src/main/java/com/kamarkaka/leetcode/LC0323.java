package com.kamarkaka.leetcode;

/***
 * 323. Number of Connected Components in an Undirected Graph
 * You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that
 * there is an edge between ai and bi in the graph.
 * Return the number of connected components in the graph.
 * Example 1:
 *   Input: n = 5, edges = [[0,1],[1,2],[3,4]]
 *   Output: 2
 * Example 2:
 *   Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
 *   Output: 1
 * Constraints:
 *   1 <= n <= 2000
 *   1 <= edges.length <= 5000
 *   edges[i].length == 2
 *   0 <= ai <= bi < n
 *   ai != bi
 *   There are no repeated edges.
 */
public class LC0323 {
   public int countComponents(int n, int[][] edges) {
      int[] roots = new int[n];
      for (int i = 0; i < n; i++) roots[i] = i;

      for (int[] e : edges) {
         int r1 = findRoot(roots, e[0]);
         int r2 = findRoot(roots, e[1]);
         if (r1 != r2) {
            roots[r1] = r2;
            n--;
         }
      }
      return n;
   }

   private int findRoot(int[] roots, int id) {
      while (roots[id] != id) {
         roots[id] = roots[roots[id]];
         id = roots[id];
      }
      return id;
   }
}
