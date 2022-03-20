package com.kamarkaka;

import com.kamarkaka.common.Pair;

import java.util.HashMap;
import java.util.List;

/***
 * 399. Evaluate Division
 * You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
 * You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
 * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
 * Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.
 *
 * Example 1:
 *    Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 *    Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 *    Explanation:
 *       Given: a / b = 2.0, b / c = 3.0
 *       queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 *       return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 *
 * Example 2:
 *    Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 *    Output: [3.75000,0.40000,5.00000,0.20000]
 *
 * Example 3:
 *    Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 *    Output: [0.50000,2.00000,-1.00000,-1.00000]
 *
 * Constraints:
 *    1 <= equations.length <= 20
 *    equations[i].length == 2
 *    1 <= Ai.length, Bi.length <= 5
 *    values.length == equations.length
 *    0.0 < values[i] <= 20.0
 *    1 <= queries.length <= 20
 *    queries[i].length == 2
 *    1 <= Cj.length, Dj.length <= 5
 *    Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 */
public class LC0399 {
   public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
      HashMap<String, Pair<String, Double>> gidWeight = new HashMap<>();
      for (int i = 0; i < equations.size(); i++) {
         List<String> equation = equations.get(i);
         String dividend = equation.get(0), divisor = equation.get(1);
         double quotient = values[i];
         union(gidWeight, dividend, divisor, quotient);
      }

      double[] results = new double[queries.size()];
      for (int i = 0; i < queries.size(); i++) {
         List<String> query = queries.get(i);
         String dividend = query.get(0), divisor = query.get(1);

         if (!gidWeight.containsKey(dividend) || !gidWeight.containsKey(divisor)) {
            results[i] = -1.0;
            continue;
         }

         Pair<String, Double> dividendEntry = find(gidWeight, dividend);
         Pair<String, Double> divisorEntry = find(gidWeight, divisor);

         String dividendGid = dividendEntry.getKey();
         String divisorGid = divisorEntry.getKey();
         Double dividendWeight = dividendEntry.getValue();
         Double divisorWeight = divisorEntry.getValue();

         if (!dividendGid.equals(divisorGid)) {
            results[i] = -1.0;
            continue;
         }

         results[i] = dividendWeight / divisorWeight;
      }
      return results;
   }

   private void union(HashMap<String, Pair<String, Double>> gidWeight, String dividend, String divisor, Double value) {
      Pair<String, Double> dividendEntry = find(gidWeight, dividend);
      Pair<String, Double> divisorEntry = find(gidWeight, divisor);

      String dividendGid = dividendEntry.getKey();
      String divisorGid = divisorEntry.getKey();
      Double dividendWeight = dividendEntry.getValue();
      Double divisorWeight = divisorEntry.getValue();

      if (!dividendGid.equals(divisorGid)) {
         gidWeight.put(dividendGid, new Pair<>(divisorGid, divisorWeight * value / dividendWeight));
      }
   }

   private Pair<String, Double> find(HashMap<String, Pair<String, Double>> gidWeight, String nodeId) {
      if (!gidWeight.containsKey(nodeId)) {
         gidWeight.put(nodeId, new Pair<>(nodeId, 1.0));
      }

      Pair<String, Double> entry = gidWeight.get(nodeId);
      if (!entry.getKey().equals(nodeId)) {
         Pair<String, Double> newEntry = find(gidWeight, entry.getKey());
         gidWeight.put(nodeId, new Pair<>(newEntry.getKey(), entry.getValue() * newEntry.getValue()));
      }

      return gidWeight.get(nodeId);
   }
}
