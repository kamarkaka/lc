package com.kamarkaka;

import java.util.Arrays;

/***
 * 947. Most Stones Removed with Same Row or Column
 * On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.
 * A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
 * Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the largest possible number of stones that can be removed.
 *
 * Example 1:
 *    Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 *    Output: 5
 *    Explanation: One way to remove 5 stones is as follows:
 *       1. Remove stone [2,2] because it shares the same row as [2,1].
 *       2. Remove stone [2,1] because it shares the same column as [0,1].
 *       3. Remove stone [1,2] because it shares the same row as [1,0].
 *       4. Remove stone [1,0] because it shares the same column as [0,0].
 *       5. Remove stone [0,1] because it shares the same row as [0,0].
 *       Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
 *
 * Example 2:
 *    Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 *    Output: 3
 *    Explanation: One way to make 3 moves is as follows:
 *       1. Remove stone [2,2] because it shares the same row as [2,0].
 *       2. Remove stone [2,0] because it shares the same column as [0,0].
 *       3. Remove stone [0,2] because it shares the same row as [0,0].
 *       Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.
 *
 * Example 3:
 *    Input: stones = [[0,0]]
 *    Output: 0
 *    Explanation: [0,0] is the only stone on the plane, so you cannot remove it.
 *
 * Constraints:
 *    1 <= stones.length <= 1000
 *    0 <= xi, yi <= 10^4
 *    No two stones are at the same coordinate point.
 */
public class LC0947 {
   int n;
   int[] parent;
   int[] size;
   int res;

   public int removeStones(int[][] stones) {
      n = stones.length;
      parent = new int[n];
      for (int i = 0; i < n; i++) {
         parent[i] = i;
      }

      size = new int[n];
      Arrays.fill(size, 1);

      res = n;
      for (int i = 0; i < n; i++) {
         for (int j = i + 1; j < n; j++) {
            if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
               union(i, j);
            }
         }
      }
      return n - res;
   }

   private void union(int a, int b) {
      int p1 = findParent(a);
      int p2 = findParent(b);

      if (p1 == p2) return;

      if (size[p1] < size[p2]) {
         parent[p1] = p2;
         size[p2] += size[p1];
      } else {
         parent[p2] = p1;
         size[p1] += size[p2];
      }
      res--;
   }

   private int findParent(int node) {
      if (parent[node] == node) return node;
      return parent[node] = findParent(parent[node]);
   }
}
