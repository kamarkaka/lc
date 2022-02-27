package com.kamarkaka.twosigma;

import java.util.*;

public class CurrencyExchange {
   private final WeightedDirectedGraph wdg;

   public CurrencyExchange() {
      this.wdg = new WeightedDirectedGraph();
   }

   public void addExchangeRate(String source, String destination, double rate) {
      wdg.addEdge(source, destination, rate);
   }

   public double convert(String source, String destination, double amount) {
      double rate = wdg.dijkstra(source, destination);
      if (rate < 0) return rate;
      return amount * rate;
   }

   private class WeightedDirectedGraph {
      private final Set<String> vertices;
      private final Map<String, Map<String, Double>> weightedEdges;

      WeightedDirectedGraph() {
         this.vertices = new HashSet<>();
         this.weightedEdges = new HashMap<>();
      }

      void addEdge(String src, String dst, double weight) {
         vertices.add(src);
         vertices.add(dst);

         weightedEdges.putIfAbsent(src, new HashMap<>());
         Map<String, Double> dstMap = weightedEdges.get(src);
         dstMap.put(dst, weight);
         weightedEdges.put(src, dstMap);
      }

      double dijkstra(String src, String dst) {
         if (!vertices.contains(src) || !vertices.contains(dst)) return -1;
         if (src.equals(dst)) return 1;

         Set<String> visited = new HashSet<>();
         Map<String, Double> pathMap = new HashMap<>();
         for (String vertex : vertices) {
            if (vertex.equals(src)) {
               pathMap.put(vertex, 1.0);
            } else {
               pathMap.put(vertex, Double.MAX_VALUE);
            }
         }

         Queue<String> pq = new LinkedList<>();
         pq.add(src);

         while (!pq.isEmpty()) {
            String vertex = pq.poll();
            visited.add(vertex);
            if (vertex.equals(dst)) continue;

            Map<String, Double> edges = weightedEdges.get(vertex);
            for (String neighbor : edges.keySet()) {
               if (visited.contains(neighbor)) continue;

               double weight = edges.get(neighbor) * pathMap.get(vertex);
               if (weight < pathMap.get(neighbor)) {
                  pathMap.put(neighbor, weight);
                  pq.add(neighbor);
               }
            }
         }

         return pathMap.get(dst);
      }
   }

   public static void run() {
      CurrencyExchange sol = new CurrencyExchange();
      sol.addExchangeRate("EUR", "USD", 1.2);
      sol.addExchangeRate("USD", "GBP", 0.75);
      sol.addExchangeRate("GBP", "AUD", 1.7);
      sol.addExchangeRate("AUD", "JPY", 90);
      sol.addExchangeRate("GBP", "JPY", 150);
      sol.addExchangeRate("JPY", "INR", 0.6);
      System.out.println(sol.convert("EUR", "INR", 100));
   }
}
