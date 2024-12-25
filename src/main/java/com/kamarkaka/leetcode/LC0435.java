package com.kamarkaka.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/***
 * 435. Non-overlapping Intervals
 * Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you
 * need to remove to make the rest of the intervals non-overlapping.
 * Note that intervals which only touch at a point are non-overlapping. For example, [1, 2] and [2, 3] are
 * non-overlapping.
 * Example 1:
 *   Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 *   Output: 1
 *   Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
 * Example 2:
 *   Input: intervals = [[1,2],[1,2],[1,2]]
 *   Output: 2
 *   Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
 * Example 3:
 *   Input: intervals = [[1,2],[2,3]]
 *   Output: 0
 *   Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 * Constraints:
 *   1 <= intervals.length <= 10^5
 *   intervals[i].length == 2
 *   -5 * 10^4 <= starti < endi <= 5 * 10^4
 */
public class LC0435 {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(arr -> arr[1]));

        int e = intervals[0][1];
        int res = 0;

        for (int i = 1; i < intervals.length; i++) {
            int is = intervals[i][0];
            int ie = intervals[i][1];

            if (is < e) {
                if (e > ie) {
                    e = ie;
                }
                res++;
            } else {
                e = Math.max(e, ie);
            }

        }

        return res;
    }
}
