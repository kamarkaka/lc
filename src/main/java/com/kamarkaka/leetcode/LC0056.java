package com.kamarkaka.leetcode;

import java.util.*;

/***
 * 56. Merge Intervals
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array
 * of the non-overlapping intervals that cover all the intervals in the input.
 * Example 1:
 *   Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 *   Output: [[1,6],[8,10],[15,18]]
 *   Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 *   Input: intervals = [[1,4],[4,5]]
 *   Output: [[1,5]]
 *   Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * Constraints:
 *   1 <= intervals.length <= 10^4
 *   intervals[i].length == 2
 *   0 <= starti <= endi <= 10^4
 */
public class LC0056 {
   public int[][] merge(int[][] intervals) {
      Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
      LinkedList<int[]> merged = new LinkedList<>();
      for (int[] interval : intervals) {
         // if the list of merged intervals is empty or if the current
         // interval does not overlap with the previous, simply append it.
         if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
            merged.add(interval);
         }
         // otherwise, there is overlap, so we merge the current and previous
         // intervals.
         else {
            merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
         }
      }
      return merged.toArray(new int[merged.size()][]);
   }
}
