package com.kamarkaka.google;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BellmanFord {
   private final Set<Integer> vertices;
   private final Map<Integer, Map<Integer, Integer>> edges;
   private final Map<Integer, Integer> dist;

   public BellmanFord() {
      this.vertices = new HashSet<>();
      this.edges = new HashMap<>();
      this.dist = new HashMap<>();
   }

   public void addEdge(int src, int des, int weight) {
      vertices.add(src);
      vertices.add(des);
      edges.putIfAbsent(src, new HashMap<>());
      edges.get(src).put(des, weight);
   }

   public Map<Integer, Integer> findMinDist(int src) {
      for (int vertex : vertices) {
         dist.put(vertex, Integer.MAX_VALUE);
      }
      dist.put(src, 0);

      // relax edges
      for (int i = 1; i < vertices.size(); i++) {
         for (int u : edges.keySet()) {
            for (Map.Entry<Integer, Integer> entry : edges.get(u).entrySet()) {
               int v = entry.getKey();
               int w = entry.getValue();

               if (dist.get(u) < Integer.MAX_VALUE && dist.get(u) + w < dist.get(v)) {
                  dist.put(v, dist.get(u) + w);
               }
            }
         }
      }

      // check for negative-weight cycles
      for (int u : edges.keySet()) {
         for (Map.Entry<Integer, Integer> entry : edges.get(u).entrySet()) {
            int v = entry.getKey();
            int w = entry.getValue();

            if (dist.get(u) < Integer.MAX_VALUE && dist.get(u) + w < dist.get(v)) {
               System.out.println("negative weight cycle!");
            }
         }
      }

      return dist;
   }

   public static void run() {
      BellmanFord bf = new BellmanFord();
      bf.addEdge(0, 1, 9);
      bf.addEdge(0, 2, 3);
      bf.addEdge(1, 2, 6);
      bf.addEdge(1, 4, 2);
      bf.addEdge(2, 1, 2);
      bf.addEdge(2, 3, 1);
      bf.addEdge(3, 2, 2);
      bf.addEdge(3, 4, 2);

      Map<Integer, Integer> dist = bf.findMinDist(0);
      for (Map.Entry<Integer, Integer> entry : dist.entrySet()) {
         int id = entry.getKey();
         int distance = entry.getValue();
         System.out.println("{id: " + id + ", minDist: " + distance + "}");
      }
   }
}
