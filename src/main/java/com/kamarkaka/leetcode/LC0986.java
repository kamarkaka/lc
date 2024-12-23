package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.List;

/***
 * 986. Interval List Intersections
 * You are given two lists of closed intervals, firstList and secondList, where firstList[i] = [starti, endi] and secondList[j] = [startj, endj]. Each list of intervals is pairwise disjoint and in sorted order.
 * Return the intersection of these two interval lists.
 * A closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
 * The intersection of two closed intervals is a set of real numbers that are either empty or represented as a closed interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].
 *
 * Example 1:
 *    Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
 *    Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 *
 * Example 2:
 *    Input: firstList = [[1,3],[5,9]], secondList = []
 *    Output: []
 *
 * Constraints:
 *    0 <= firstList.length, secondList.length <= 1000
 *    firstList.length + secondList.length >= 1
 *    0 <= start[i] < end[i] <= 10^9
 *    end[i] < start[i+1]
 *    0 <= start[j] < end[j] <= 10^9
 *    end[j] < start[j+1]
 */
public class LC0986 {
   public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
      if (firstList.length == 0 || secondList.length == 0) return new int[0][2];

      int p1 = 0, p2 = 0;
      List<int[]> res = new ArrayList<>();
      while (p1 < firstList.length && p2 < secondList.length) {
         int[] interval1 = firstList[p1];
         int[] interval2 = secondList[p2];

         if (interval2[1] < interval1[0]) {
            p2++;
            continue;
         }

         if (interval1[1] < interval2[0]) {
            p1++;
            continue;
         }

         int[] interval = new int[2];
         if (interval1[0] < interval2[0]) {
            interval[0] = interval2[0];
         } else {
            interval[0] = interval1[0];
         }

         if (interval1[1] < interval2[1]) {
            interval[1] = interval1[1];
            p1++;
         } else {
            interval[1] = interval2[1];
            p2++;
         }

         res.add(interval);
      }

      int[][] intersection = new int[res.size()][2];
      for (int i = 0; i < res.size(); i++) {
         intersection[i] = res.get(i);
      }
      return intersection;
   }
}
