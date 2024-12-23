package com.kamarkaka.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/***
 * 547. Number of Provinces
 * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
 * Return the total number of provinces.
 *
 * Example 1:
 *   Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 *   Output: 2
 *
 * Example 2:
 *   Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 *   Output: 3
 *
 * Constraints:
 *   1 <= n <= 200
 *   n == isConnected.length
 *   n == isConnected[i].length
 *   isConnected[i][j] is 1 or 0.
 *   isConnected[i][i] == 1
 *   isConnected[i][j] == isConnected[j][i]
 */
public class LC0547 {
   public int findCircleNum(int[][] isConnected) {
      int n = isConnected.length;
      boolean[] visited = new boolean[n];
      Set<Integer> set = new HashSet<>();

      for (int i = 0; i < n; i++) {
         if (visited[i]) continue;

         Queue<Integer> queue = new LinkedList<>();
         queue.add(i);
         visited[i] = true;

         while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int j = 0; j < n; j++) {
               if (j != node && !visited[j] && isConnected[node][j] == 1) {
                  queue.add(j);
                  visited[j] = true;
               }
            }
         }

         set.add(i);
      }

      return set.size();
   }
}
