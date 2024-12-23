package com.kamarkaka.leetcode;

import java.util.*;

/***
 * 2092. Find All People With Secret
 * You are given an integer n indicating there are n people numbered from 0 to n - 1. You are also given a 0-indexed 2D integer array meetings where meetings[i] = [xi, yi, timei] indicates that person xi and person yi have a meeting at timei. A person may attend multiple meetings at the same time. Finally, you are given an integer firstPerson.
 * Person 0 has a secret and initially shares the secret with a person firstPerson at time 0. This secret is then shared every time a meeting takes place with a person that has the secret. More formally, for every meeting, if a person xi has the secret at timei, then they will share the secret with person yi, and vice versa.
 * The secrets are shared instantaneously. That is, a person may receive the secret and share it with people in other meetings within the same time frame.
 * Return a list of all the people that have the secret after all the meetings have taken place. You may return the answer in any order.
 *
 * Example 1:
 *    Input: n = 6, meetings = [[1,2,5],[2,3,8],[1,5,10]], firstPerson = 1
 *    Output: [0,1,2,3,5]
 *    Explanation:
 *       At time 0, person 0 shares the secret with person 1.
 *       At time 5, person 1 shares the secret with person 2.
 *       At time 8, person 2 shares the secret with person 3.
 *       At time 10, person 1 shares the secret with person 5.​​​​
 *       Thus, people 0, 1, 2, 3, and 5 know the secret after all the meetings.
 *
 * Example 2:
 *    Input: n = 4, meetings = [[3,1,3],[1,2,2],[0,3,3]], firstPerson = 3
 *    Output: [0,1,3]
 *    Explanation:
 *       At time 0, person 0 shares the secret with person 3.
 *       At time 2, neither person 1 nor person 2 know the secret.
 *       At time 3, person 3 shares the secret with person 0 and person 1.
 *       Thus, people 0, 1, and 3 know the secret after all the meetings.
 *
 * Example 3:
 *    Input: n = 5, meetings = [[3,4,2],[1,2,1],[2,3,1]], firstPerson = 1
 *    Output: [0,1,2,3,4]
 *    Explanation:
 *       At time 0, person 0 shares the secret with person 1.
 *       At time 1, person 1 shares the secret with person 2, and person 2 shares the secret with person 3.
 *       Note that person 2 can share the secret at the same time as receiving it.
 *       At time 2, person 3 shares the secret with person 4.
 *       Thus, people 0, 1, 2, 3, and 4 know the secret after all the meetings.
 *
 * Constraints:
 *    2 <= n <= 10^5
 *    1 <= meetings.length <= 10^5
 *    meetings[i].length == 3
 *    0 <= xi, yi <= n - 1
 *    xi != yi
 *    1 <= timei <= 10^5
 *    1 <= firstPerson <= n - 1
 */
public class LC2092 {
   public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
      Map<Integer, List<int[]>> map = new HashMap<>();
      for (int[] meeting : meetings) {
         List<int[]> list = map.getOrDefault(meeting[2], new ArrayList<>());
         list.add(new int[] {meeting[0], meeting[1]});
         map.put(meeting[2], list);
      }

      UnionFind uf = new UnionFind(n);
      uf.union(0, firstPerson);
      Set<Integer> resultSet = new HashSet<>();
      resultSet.add(0);
      resultSet.add(firstPerson);

      List<Integer> timestamps = new ArrayList<>(map.keySet());
      Collections.sort(timestamps);

      for (int timestamp : timestamps) {
         for (int[] meeting : map.get(timestamp)) {
            uf.union(meeting[0], meeting[1]);
         }

         for (int[] meeting : map.get(timestamp)) {
            if (uf.findParent(meeting[0]) == uf.findParent(0) || uf.findParent(meeting[1]) == uf.findParent(0)) {
               resultSet.add(meeting[0]);
               resultSet.add(meeting[1]);
            } else {
               uf.reset(meeting[0]);
               uf.reset(meeting[1]);
            }
         }
      }

      return new ArrayList<>(resultSet);
   }

   class UnionFind {
      private int[] parent;
      UnionFind(int n) {
         this.parent = new int[n];
         for (int i = 0; i < n; i++) {
            this.parent[i] = i;
         }
      }

      private int findParent(int i) {
         if (parent[i] == i) return i;
         return parent[i] = findParent(parent[i]);
      }

      private void union(int i, int j) {
         int pi = findParent(i);
         int pj = findParent(j);
         if (pi == pj) return;

         parent[pi] = parent[pj];
      }

      private void reset(int i) {
         parent[i] = i;
      }
   }
}
