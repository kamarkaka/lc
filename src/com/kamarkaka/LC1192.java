package com.kamarkaka;

import com.kamarkaka.common.Utilities;

import java.util.*;

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

