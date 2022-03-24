package com.kamarkaka;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
