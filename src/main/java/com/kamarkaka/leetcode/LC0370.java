package com.kamarkaka.leetcode;

import com.kamarkaka.common.Utilities;

import java.util.Comparator;
import java.util.PriorityQueue;

/***
 * 370. Range Addition
 * You are given an integer length and an array updates where updates[i] = [startIdxi, endIdxi, inci].
 * You have an array arr of length length with all zeros, and you have some operation to apply on arr. In the ith operation, you should increment all the elements arr[startIdxi], arr[startIdxi + 1], ..., arr[endIdxi] by inci.
 * Return arr after applying all the updates.
 *
 * Example 1:
 *    Input: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
 *    Output: [-2,0,3,5,3]
 *
 * Example 2:
 *    Input: length = 10, updates = [[2,4,6],[5,6,8],[1,9,-4]]
 *    Output: [0,-4,2,2,2,4,4,-4,-4,-4]
 *
 * Constraints:
 *    1 <= length <= 10^5
 *    0 <= updates.length <= 10^4
 *    0 <= startIdxi <= endIdxi < length
 *    -1000 <= inci <= 1000
 */
public class LC0370 {
   public int[] getModifiedArray(int length, int[][] updates) {
      int[] arr = new int[length];
      PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
      for (int[] update : updates) {
         pq.add(new int[] {update[0], update[2]});
         pq.add(new int[] {update[1] + 1, -update[2]});
      }

      int pointer = 0;
      int incre = 0;
      while (pointer < length) {
         while (!pq.isEmpty() && pq.peek()[0] == pointer) {
            int[] op = pq.poll();
            incre += op[1];
         }

         arr[pointer] = incre;
         pointer++;
      }

      return arr;
   }

   public static void run() {
      LC0370 solution = new LC0370();
      Utilities.print(solution.getModifiedArray(5, new int[][] {{1,3,2},{2,4,3},{0,2,-2}}));
   }
}
