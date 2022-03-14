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
      double rate = wdg.bellmanFord(source, destination);
      if (rate < 0) return rate;
      return amount * rate;
   }

   private class WeightedDirectedGraph {
      private final Set<String> vertices;
      private final List<Edge> edges;

      WeightedDirectedGraph() {
         this.vertices = new HashSet<>();
         this.edges = new LinkedList<>();
      }

      void addEdge(String src, String dst, double weight) {
         vertices.add(src);
         vertices.add(dst);
         edges.add(new Edge(src, dst, weight));
      }

      double bellmanFord(String src, String dst) {
         if (!vertices.contains(src) || !vertices.contains(dst)) return -1;
         if (src.equals(dst)) return 1;

         Map<String, Double> distMap = new HashMap<>();
         Map<String, String> predecessorMap = new HashMap<>();
         for (String vertex : vertices) {
            if (vertex.equals(src)) {
               distMap.put(vertex, 1.0);
            } else {
               distMap.put(vertex, -1.0);
            }
         }

         for (int i = 1; i < vertices.size(); i++) {
            for (Edge edge : edges) {
               String u = edge.src;
               String v = edge.dst;
               double w = edge.weight;

               if (distMap.get(u) < 0) continue;
               distMap.put(v, Math.max(distMap.get(v), distMap.get(u) * w));
               predecessorMap.put(v, u);
            }
         }

         for (Edge edge : edges) {
            String u = edge.src;
            String v = edge.dst;
            double w = edge.weight;

            if (distMap.get(u) * w > distMap.get(v)) {
               return -1;
            }
         }

         return distMap.get(dst);
      }

      private class Edge {
         String src;
         String dst;
         double weight;

         public Edge(String src, String dst, double weight) {
            this.src = src;
            this.dst = dst;
            this.weight = weight;
         }
      }
   }

   public static void run() {
      CurrencyExchange sol = new CurrencyExchange();
//      sol.addExchangeRate("EUR", "GBP", 0.8);
//      sol.addExchangeRate("EUR", "USD", 1.1);
//      sol.addExchangeRate("GBP", "USD", 1.3);
//      sol.addExchangeRate("USD", "GBP", 0.8);
//      sol.addExchangeRate("GBP", "INR", 100);
//      sol.addExchangeRate("USD", "INR", 77);

      sol.addExchangeRate("EUR", "USD", 1.2);
      sol.addExchangeRate("USD", "GBP", 0.75);
      sol.addExchangeRate("GBP", "AUD", 1.7);
      sol.addExchangeRate("AUD", "JPY", 90);
      sol.addExchangeRate("GBP", "JPY", 150);
      sol.addExchangeRate("JPY", "INR", 0.6);
      System.out.println(sol.convert("EUR", "INR", 100));
   }
}
