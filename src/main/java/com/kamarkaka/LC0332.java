package main.java.com.kamarkaka;

import java.util.*;

/***
 * 332. Reconstruct Itinerary
 * You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight. Reconstruct the itinerary in order and return it.
 * All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK". If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
 * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.
 *
 * Example 1:
 *    Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
 *    Output: ["JFK","MUC","LHR","SFO","SJC"]
 *
 * Example 2:
 *    Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 *    Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 *    Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.
 *
 * Constraints:
 *    1 <= tickets.length <= 300
 *    tickets[i].length == 2
 *    fromi.length == 3
 *    toi.length == 3
 *    fromi and toi consist of uppercase English letters.
 *    fromi != toi
 */
public class LC0332 {
   HashMap<String, List<String>> flightMap = new HashMap<>();
   HashMap<String, boolean[]> visitBitmap = new HashMap<>();
   int flights = 0;
   List<String> result = null;

   public List<String> findItinerary(List<List<String>> tickets) {
      // Step 1). build the graph first
      for (List<String> ticket : tickets) {
         String origin = ticket.get(0);
         String dest = ticket.get(1);
         if (flightMap.containsKey(origin)) {
            List<String> destList = flightMap.get(origin);
            destList.add(dest);
         } else {
            List<String> destList = new LinkedList<>();
            destList.add(dest);
            flightMap.put(origin, destList);
         }
      }

      // Step 2). order the destinations and init the visit bitmap
      for (Map.Entry<String, List<String>> entry : flightMap.entrySet()) {
         Collections.sort(entry.getValue());
         visitBitmap.put(entry.getKey(), new boolean[entry.getValue().size()]);
      }

      flights = tickets.size();
      LinkedList<String> route = new LinkedList<>();
      route.add("JFK");

      // Step 3). backtracking
      backtracking("JFK", route);
      return this.result;
   }

   private boolean backtracking(String origin, LinkedList<String> route) {
      if (route.size() == this.flights + 1) {
         result = (List<String>) route.clone();
         return true;
      }

      if (!flightMap.containsKey(origin)) return false;

      int i = 0;
      boolean[] bitmap = visitBitmap.get(origin);

      for (String dest : this.flightMap.get(origin)) {
         if (!bitmap[i]) {
            bitmap[i] = true;
            route.add(dest);
            boolean ret = backtracking(dest, route);
            route.pollLast();
            bitmap[i] = false;

            if (ret) return true;
         }
         i++;

      }
      return false;
   }
}
