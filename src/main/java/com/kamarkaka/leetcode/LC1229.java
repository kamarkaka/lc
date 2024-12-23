package com.kamarkaka.leetcode;

import java.util.*;

/***
 * 1229. Meeting Scheduler
 * Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration, return the earliest time slot that works for both of them and is of duration duration.
 * If there is no common time slot that satisfies the requirements, return an empty array.
 * The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.
 * It is guaranteed that no two availability slots of the same person intersect with each other. That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.
 *
 * Example 1:
 *    Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
 *    Output: [60,68]
 *
 * Example 2:
 *    Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
 *    Output: []
 *
 * Constraints:
 *    1 <= slots1.length, slots2.length <= 10^4
 *    slots1[i].length, slots2[i].length == 2
 *    slots1[i][0] < slots1[i][1]
 *    slots2[i][0] < slots2[i][1]
 *    0 <= slots1[i][j], slots2[i][j] <= 10^9
 *    1 <= duration <= 10^6
 */
public class LC1229 {
   public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
      PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(slot -> slot[0]));

      for (int[] slot: slots1) {
         if (slot[1] - slot[0] >= duration) pq.add(slot);
      }
      for (int[] slot: slots2) {
         if (slot[1] - slot[0] >= duration) pq.add(slot);
      }

      while (pq.size() > 1) {
         int[] slot1 = pq.poll();
         int[] slot2 = pq.peek();
         if (slot1[1] >= slot2[0] + duration) {
            return new ArrayList<>(Arrays.asList(slot2[0], slot2[0] + duration));
         }
      }
      return new ArrayList<>();
   }
}
