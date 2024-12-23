package com.kamarkaka.leetcode;

import java.util.*;

/***
 * 207. Course Schedule
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 *
 * Example 1:
 *    Input: numCourses = 2, prerequisites = [[1,0]]
 *    Output: true
 *    Explanation: There are a total of 2 courses to take.
 *    To take course 1 you should have finished course 0. So it is possible.
 *
 * Example 2:
 *    Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 *    Output: false
 *    Explanation: There are a total of 2 courses to take.
 *    To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 *
 * Constraints:
 *    1 <= numCourses <= 10^5
 *    0 <= prerequisites.length <= 5000
 *    prerequisites[i].length == 2
 *    0 <= ai, bi < numCourses
 *    All the pairs prerequisites[i] are unique.
 */
public class LC0207 {
   public boolean canFinish(int numCourses, int[][] prerequisites) {
      Map<Integer, Set<Integer>> map = new HashMap<>();

      for (int[] pre : prerequisites) {
         Set<Integer> set = map.getOrDefault(pre[0], new HashSet<>());
         set.add(pre[1]);
         map.put(pre[0], set);
      }

      for (int i = 0; i < numCourses; i++) {
         Set<Integer> set = new HashSet<>();
         Queue<Integer> queue = new LinkedList<>();
         queue.add(i);

         while (!queue.isEmpty()) {
            int val = queue.poll();
            Set<Integer> preset = map.getOrDefault(val, new HashSet<>());
            for (int pre : preset) {
               if (set.contains(pre)) continue;
               set.add(pre);
               queue.add(pre);
            }
         }

         if (set.contains(i)) return false;
      }

      return true;
   }

   public static void run() {
      LC0207 solution = new LC0207();
      System.out.println(solution.canFinish(2, new int[][] {
         {1,0}
      }));
      System.out.println(solution.canFinish(2, new int[][] {
         {1,0},
         {0,1}
      }));
      System.out.println(solution.canFinish(5, new int[][] {
         {1,4},
         {2,4},
         {3,1},
         {3,2}
      }));
   }
}
