package com.kamarkaka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/***
 * 1548. The Most Similar Path in a Graph
 * We have n cities and m bi-directional roads where roads[i] = [ai, bi] connects city ai with city bi. Each city has a name consisting of exactly three upper-case English letters given in the string array names. Starting at any city x, you can reach any city y where y != x (i.e., the cities and the roads are forming an undirected connected graph).
 * You will be given a string array targetPath. You should find a path in the graph of the same length and with the minimum edit distance to targetPath.
 * You need to return the order of the nodes in the path with the minimum edit distance. The path should be of the same length of targetPath and should be valid (i.e., there should be a direct road between ans[i] and ans[i + 1]). If there are multiple answers return any one of them.
 *
 * Example 1:
 *   Input: n = 5, roads = [[0,2],[0,3],[1,2],[1,3],[1,4],[2,4]], names = ["ATL","PEK","LAX","DXB","HND"], targetPath = ["ATL","DXB","HND","LAX"]
 *   Output: [0,2,4,2]
 *   Explanation: [0,2,4,2], [0,3,0,2] and [0,3,1,2] are accepted answers.
 *   [0,2,4,2] is equivalent to ["ATL","LAX","HND","LAX"] which has edit distance = 1 with targetPath.
 *   [0,3,0,2] is equivalent to ["ATL","DXB","ATL","LAX"] which has edit distance = 1 with targetPath.
 *   [0,3,1,2] is equivalent to ["ATL","DXB","PEK","LAX"] which has edit distance = 1 with targetPath.
 *
 * Example 2:
 *   Input: n = 4, roads = [[1,0],[2,0],[3,0],[2,1],[3,1],[3,2]], names = ["ATL","PEK","LAX","DXB"], targetPath = ["ABC","DEF","GHI","JKL","MNO","PQR","STU","VWX"]
 *   Output: [0,1,0,1,0,1,0,1]
 *   Explanation: Any path in this graph has edit distance = 8 with targetPath.
 *
 * Example 3:
 *   Input: n = 6, roads = [[0,1],[1,2],[2,3],[3,4],[4,5]], names = ["ATL","PEK","LAX","ATL","DXB","HND"], targetPath = ["ATL","DXB","HND","DXB","ATL","LAX","PEK"]
 *   Output: [3,4,5,4,3,2,1]
 *   Explanation: [3,4,5,4,3,2,1] is the only path with edit distance = 0 with targetPath.
 *   It's equivalent to ["ATL","DXB","HND","DXB","ATL","LAX","PEK"]
 *
 * Constraints:
 *   2 <= n <= 100
 *   m == roads.length
 *   n - 1 <= m <= (n * (n - 1) / 2)
 *   0 <= ai, bi <= n - 1
 *   ai != bi
 *   The graph is guaranteed to be connected and each pair of nodes may have at most one direct road.
 *   names.length == n
 *   names[i].length == 3
 *   names[i] consists of upper-case English letters.
 *   There can be two cities with the same name.
 *   1 <= targetPath.length <= 100
 *   targetPath[i].length == 3
 *   targetPath[i] consists of upper-case English letters.
 *
 * Follow up: If each node can be visited only once in the path, What should you change in your solution?
 */
public class LC1548 {
   public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
      // Populate roads into an array of ArrayList, so easier to retrive by "node" id
      // and the node's linked ones
      List<Integer>[] graph = buildGraph(n, roads);

      int pathLen = targetPath.length;
      // here we leave "memory" for each nodes, from 0 ~ n, and each step of path, from 0 ~ pathLen
      // prev - track previous node
      // dist - track min edit distance
      int[][] prev = new int[n][pathLen];
      int[][] dist = new int[n][pathLen];

      // initial edit distance will be max value, so as patthLen
      for (int[] d : dist) {
         Arrays.fill(d, pathLen);
      }

      // intitial for step = 0, if == targetPath[0], we set as 0
      // otherwise as 1
      for (int i = 0; i < n; i++) {
         dist[i][0] = names[i].equals(targetPath[0]) ? 0 : 1;
      }

      // traverse step by step for the path
      for (int i = 1; i < pathLen; i++) {
         // check each nodes
         for (int j = 0; j < n; j++) {
            // see if any next nodes of current one has a shorter distance
            for (int next : graph[j]) {
               // if next node in previous step (i - 1) is even shorter than current node in this step (i)
               // we update distance and prev
               if (dist[j][i] > dist[next][i - 1]) {
                  dist[j][i] = dist[next][i - 1];
                  prev[j][i] = next;
               }
            }

            // not forget to check & update current step
            dist[j][i] += names[j].equals(targetPath[i]) ? 0 : 1;
         }
      }

      // find the city on the last step which has shortest distance
      int endCity = 0;
      for (int i = 1; i < n; i++) {
         if (dist[i][pathLen - 1] < dist[endCity][pathLen - 1]) {
            endCity = i;
         }
      }

      List<Integer> res = new LinkedList<>();
      // use the endCity + prev to trace back the path for answer
      for (int i = pathLen - 1; i >= 0; i--) {
         res.add(0, endCity);
         endCity = prev[endCity][i];
      }

      return res;
   }

   private List<Integer>[] buildGraph(int n, int[][] roads) {
      List<Integer>[] graph = new ArrayList[n];
      // initial List for each node
      for (int i = 0; i < n; i++) {
         graph[i] = new ArrayList<>();
      }

      for (int[] road : roads) {
         int from = road[0], to = road[1];
         // undirected, so add both directions
         graph[from].add(to);
         graph[to].add(from);
      }

      return graph;
   }
}
