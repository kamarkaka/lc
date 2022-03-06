package com.kamarkaka.google;

import java.util.*;

public class MaximumExcitement {
   Map<String, Integer> excitements;
   Set<String> cities;
   Map<String, Set<String>> graph;

   public int getMaxExcitement(Map<String, Integer> excitements, List<String[]> flights) {
      this.excitements = excitements;
      this.cities = new HashSet<>();
      this.graph = new HashMap<>();

      for (String[] flight : flights) {
         graph.putIfAbsent(flight[0], new HashSet<>());
         graph.putIfAbsent(flight[1], new HashSet<>());
         graph.get(flight[0]).add(flight[1]);
         graph.get(flight[1]).add(flight[0]);
         cities.add(flight[0]);
         cities.add(flight[1]);
      }

      int maxExcitement = 0;
      for (String city : cities) {
         Set<String> visited = new HashSet<>();
         maxExcitement = Math.max(maxExcitement, dfs(city, visited, 0, 0));
      }
      return maxExcitement;
   }

   private int dfs(String city, Set<String> visited, int numOfVisistedCities, int excitement) {
      if (visited.contains(city)) return -1;
      if (numOfVisistedCities == 3) return excitement + excitements.get(city);

      int max = -1;
      visited.add(city);
      for (String neighbor : graph.get(city)) {
         int ext = dfs(neighbor, visited, numOfVisistedCities + 1, excitement + excitements.get(city));
         if (ext > max) max = ext;
      }
      visited.remove(city);
      return max;
   }

   public static void run() {
      MaximumExcitement sol = new MaximumExcitement();
      Map<String, Integer> excitements = new HashMap<>();
      excitements.put("New York", 10000);
      excitements.put("San Francisco", 1000);
      excitements.put("Texas", 500);
      excitements.put("LA", 20000);
      excitements.put("Chicago", 3000);
      excitements.put("San Jose", 900);

      List<String[]> flights = new ArrayList<>();
      flights.add(new String[] {"New York","San Francisco"});
      flights.add(new String[] {"New York","LA"});
      flights.add(new String[] {"Chicago","LA"});
      flights.add(new String[] {"San Jose","San Francisco"});

      System.out.println(sol.getMaxExcitement(excitements, flights));
   }
}
