package com.kamarkaka.twosigma;

import java.util.*;

/***
 * Input
 *    codebase2time = {
 *       "A": 1,
 *       "B": 3,
 *       "C": 2,
 *       "D": 2,
 *       "E": 4
 *    },
 *    dependency = {
 *       "B": ["A"],
 *       "C": ["A"],
 *       "D": ["B","C"],
 *       "E": ["A","C"]
 *    }
 * Output
 *    1, A
 *    2, C, B
 *    1, B, E
 *    2, D, E
 *    1, E
 */
public class CompileLibraries {
   private final DirectedGraph graph = new DirectedGraph();

   List<String> compileTime(Map<String, Integer> codebase2time, Map<String, List<String>> dependency) {
      List<String> res = new ArrayList<>();

      graph.build(dependency);

      Map<String, Integer> vertexIdMap = graph.topologicalSort();
      if (vertexIdMap == null) {
         System.out.println("cycle detected");
         return res;
      }
      System.out.println("topological order: " + vertexIdMap);

      PriorityQueue<CodebaseTime> pq = new PriorityQueue<>(Comparator.comparingInt(t -> t.time));
      PriorityQueue<CodebaseTime> nextpq = new PriorityQueue<>(Comparator.comparingInt(t -> t.time));

      for (String vertex : graph.startingVertices) {
         pq.add(new CodebaseTime(vertex, codebase2time.get(vertex)));
      }

      while (!pq.isEmpty()) {
         CodebaseTime minCodebaseTime = pq.peek();
         int minTime = minCodebaseTime.time;

         StringBuilder sb = new StringBuilder();
         sb.append(minTime);

         while (!pq.isEmpty()) {
            CodebaseTime codebaseTime = pq.poll();
            sb.append(", ").append(codebaseTime.name);

            if (codebaseTime.time - minTime == 0) {
               if (graph.edges.containsKey(codebaseTime.name)) {
                  for (String neighbor : graph.edges.get(codebaseTime.name)) {
                     vertexIdMap.put(neighbor, vertexIdMap.get(neighbor) - 1);
                     if (vertexIdMap.get(neighbor) == 0) {
                        nextpq.add(new CodebaseTime(neighbor, codebase2time.get(neighbor)));
                     }
                  }
               }
            } else {
               nextpq.add(new CodebaseTime(codebaseTime.name, codebaseTime.time - minTime));
            }
         }

         res.add(sb.toString());
         pq = nextpq;
         nextpq = new PriorityQueue<>(Comparator.comparingInt(t -> t.time));
      }

      return res;
   }

   private class DirectedGraph {
      Set<String> vertices;
      Map<String, List<String>> edges;
      Set<String> startingVertices;

      public DirectedGraph() {
         this.vertices = new HashSet<>();
         this.edges = new HashMap<>();
         this.startingVertices = new HashSet<>();
      }

      public void build(Map<String, List<String>> dependency) {
         for (Map.Entry<String, List<String>> entry : dependency.entrySet()) {
            String from = entry.getKey();
            List<String> tos = entry.getValue();

            graph.vertices.add(from);

            for (String to : tos) {
               graph.vertices.add(to);
               graph.edges.putIfAbsent(to, new ArrayList<>());
               graph.edges.get(to).add(from);
            }
         }
      }

      public Map<String, Integer> topologicalSort() {
         Map<String, Integer> verticeIdMap = new HashMap<>();
         startingVertices = new HashSet<>(vertices);
         for (List<String> vertices : edges.values()) {
            startingVertices.removeAll(vertices);
         }

         if (startingVertices.isEmpty()) return null;

         Queue<String> queue = new LinkedList<>(startingVertices);
         Queue<String> nextQueue = new LinkedList<>();

         for (String vertice : startingVertices) {
            verticeIdMap.put(vertice, 0);
         }

         while (!queue.isEmpty()) {
            String vertice = queue.poll();

            if (edges.containsKey(vertice)) {
               List<String> neighbors = edges.get(vertice);
               for (String neighbor : neighbors) {
                  verticeIdMap.put(neighbor, verticeIdMap.getOrDefault(neighbor, 0) + 1);
               }
               nextQueue.addAll(neighbors);
            }

            if (queue.isEmpty()) {
               queue = nextQueue;
               nextQueue = new LinkedList<>();
            }
         }

         return verticeIdMap;
      }
   }

   private class CodebaseTime {
      String name;
      int time;

      CodebaseTime(String name, int time) {
         this.name = name;
         this.time = time;
      }
   }

   public static void run() {
      CompileLibraries sol = new CompileLibraries();

      Map<String, Integer> codebase2time = new HashMap<>();
      codebase2time.put("A", 1);
      codebase2time.put("B", 3);
      codebase2time.put("C", 2);
      codebase2time.put("D", 2);
      codebase2time.put("E", 4);

      Map<String, List<String>> dependency = new HashMap<>();
      dependency.put("B", List.of("A"));
      dependency.put("C", List.of("A"));
      dependency.put("D", List.of("B", "C"));
      dependency.put("E", List.of("A", "C"));

      List<String> res = sol.compileTime(codebase2time, dependency);
      for (String line : res) {
         System.out.println(line);
      }
   }
}
