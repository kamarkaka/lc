package com.kamarkaka.leetcode;

import com.kamarkaka.common.Utilities;

import java.util.*;

/***
 * 1192. Critical Connections in a Network
 * There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi. Any server can reach other servers directly or indirectly through the network.
 * A critical connection is a connection that, if removed, will make some servers unable to reach some other server.
 * Return all critical connections in the network in any order.
 *
 * Example 1:
 *    Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 *    Output: [[1,3]]
 *    Explanation: [[3,1]] is also accepted.
 *
 * Example 2:
 *    Input: n = 2, connections = [[0,1]]
 *    Output: [[0,1]]
 *
 * Constraints:
 *    2 <= n <= 10^5
 *    n - 1 <= connections.length <= 10^5
 *    0 <= ai, bi <= n - 1
 *    ai != bi
 *    There are no repeated connections.
 */
public class LC1192 {
   public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
      List<Integer>[] graph = new ArrayList[n];
      for (int i = 0; i < n; i++) {
         graph[i] = new ArrayList<>();
      }

      for (List<Integer> edge : connections) {
         int u = edge.get(0), v = edge.get(1);
         graph[u].add(v);
         graph[v].add(u);
      }

      boolean[] visited = new boolean[n];
      int[] nodeRank = new int[n];

      List<List<Integer>> res = new ArrayList<>();
      DFS(graph, 0, -1, visited, 1, nodeRank, res);
      return res;
   }

   private void DFS(List<Integer>[] graph, int node, int parent, boolean[] visited, int rank, int[] nodeRank, List<List<Integer>> res) {
      visited[node] = true;
      nodeRank[node] = rank++;

      int currRank = nodeRank[node];

      for (int neighbor : graph[node]) {
         if (neighbor == parent) continue;
         if (!visited[neighbor]) {
            DFS(graph, neighbor, node, visited, rank, nodeRank, res);
         }
         nodeRank[node] = Math.min(nodeRank[node], nodeRank[neighbor]);

         if (currRank < nodeRank[neighbor]) {
            res.add(Arrays.asList(node, neighbor));
         }
      }
   }

   public static void run() {
      LC1192 solution = new LC1192();
      List<List<Integer>> conn = new ArrayList<>();
      conn.add(new ArrayList<>(Arrays.asList(0,1)));
      conn.add(new ArrayList<>(Arrays.asList(1,2)));
      conn.add(new ArrayList<>(Arrays.asList(2,0)));
      conn.add(new ArrayList<>(Arrays.asList(1,3)));
      List<List<Integer>> res = solution.criticalConnections(4, conn);
      Utilities.print(res);
   }
}
