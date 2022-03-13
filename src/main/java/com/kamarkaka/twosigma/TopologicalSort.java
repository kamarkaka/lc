package com.kamarkaka.twosigma;

import java.util.*;

public class TopologicalSort {
   public List<Integer> sort(int N, int[][] edges) {
      boolean[][] adj = new boolean[N][N];
      int[] incomingEdgeCount = new int[N];
      for (int[] edge : edges) {
         adj[edge[0]][edge[1]] = true;
         incomingEdgeCount[edge[1]]++;
      }

      Queue<Integer> queue = new LinkedList<>();
      for (int i = 0; i < N; i++) {
         if (incomingEdgeCount[i] > 0) continue;
         queue.add(i);
      }

      List<Integer> res = new LinkedList<>();
      while (!queue.isEmpty()) {
         int u = queue.poll();
         res.add(u);

         for (int v = 0; v < N; v++) {
            if (u == v || !adj[u][v]) continue;

            incomingEdgeCount[v]--;
            if (incomingEdgeCount[v] > 0) continue;
            queue.add(v);
         }
      }

      return res;
   }

   public static void run() {
      TopologicalSort sol = new TopologicalSort();
      System.out.println(sol.sort(5, new int[][] {
         {0,1},{2,3},{4,1},{2,4}
      }));
   }
}
