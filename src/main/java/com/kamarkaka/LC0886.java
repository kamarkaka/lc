package com.kamarkaka;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/***
 * 886. Possible Bipartition
 * We want to split a group of n people (labeled from 1 to n) into two groups of any size. Each person may dislike some other people, and they should not go into the same group.
 * Given the integer n and the array dislikes where dislikes[i] = [ai, bi] indicates that the person labeled ai does not like the person labeled bi, return true if it is possible to split everyone into two groups in this way.
 *
 * Example 1:
 *    Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
 *    Output: true
 *    Explanation: group1 [1,4] and group2 [2,3].
 *
 * Example 2:
 *    Input: n = 3, dislikes = [[1,2],[1,3],[2,3]]
 *    Output: false
 *
 * Example 3:
 *    Input: n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 *    Output: false
 *
 * Constraints:
 *    1 <= n <= 2000
 *    0 <= dislikes.length <= 10^4
 *    dislikes[i].length == 2
 *    1 <= dislikes[i][j] <= n
 *    ai < bi
 *    All the pairs of dislikes are unique.
 */
public class LC0886 {
   public boolean possibleBipartition(int n, int[][] dislikes) {
      List<Integer>[] map = new List[n+1];
      for (int i = 1; i <= n; i++) {
         map[i] = new ArrayList<>();
      }

      for(int[] edge: dislikes){
         map[edge[0]].add(edge[1]);
         map[edge[1]].add(edge[0]);
      }

      int[] colors = new int[n+1];
      for (int i = 1; i <= n; i++) {
         if (colors[i] == 0) {
            if (!bfs(map, colors, i)) return false;
         }
      }
      return true;
   }

   private boolean bfs(List<Integer>[] map, int[] colors, int i) {
      int color = 1;
      Queue<Integer> queue = new LinkedList<>();
      queue.add(i);
      colors[i] = color;
      while (!queue.isEmpty()) {
         int cur = queue.poll();
         color = -colors[cur];
         for (int nxt : map[cur]) {
            if (colors[nxt] == -color) return false;
            if (colors[nxt] == 0) {
               colors[nxt] = color;
               queue.add(nxt);
            }
         }
      }
      return true;
   }
}
