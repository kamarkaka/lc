package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.List;

/***
 * 986. Interval List Intersections
 * You are given two lists of closed intervals, firstList and secondList, where firstList[i] = [starti, endi] and
 * secondList[j] = [startj, endj]. Each list of intervals is pairwise disjoint and in sorted order.
 * Return the intersection of these two interval lists.
 * A closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
 * The intersection of two closed intervals is a set of real numbers that are either empty or represented as a closed
 * interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].
 * Example 1:
 *   Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
 *   Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 * Example 2:
 *   Input: firstList = [[1,3],[5,9]], secondList = []
 *   Output: []
 * Constraints:
 *   0 <= firstList.length, secondList.length <= 1000
 *   firstList.length + secondList.length >= 1
 *   0 <= start[i] < end[i] <= 10^9
 *   end[i] < start[i+1]
 *   0 <= start[j] < end[j] <= 10^9
 *   end[j] < start[j+1]
 */
public class LC0986 {
   public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
      List<int[]> result = new ArrayList<>();

      int p1 = 0, p2 = 0;
      while (p1 < firstList.length && p2 < secondList.length) {
         int lower = Math.max(firstList[p1][0], secondList[p2][0]);
         int upper = Math.max(firstList[p1][1], secondList[p2][1]);

         if (lower <= upper) {
            result.add(new int[] {lower, upper});
         }

         if (firstList[p1][1] < secondList[p2][1]) {
            p1++;
         } else {
            p2++;
         }
      }

      return result.toArray(new int[result.size()][]);
   }
}
