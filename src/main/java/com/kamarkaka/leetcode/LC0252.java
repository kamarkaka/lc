package com.kamarkaka.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/***
 * 252. Meeting Rooms
 * Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.
 *
 * Example 1:
 *    Input: intervals = [[0,30],[5,10],[15,20]]
 *    Output: false
 *
 * Example 2:
 *    Input: intervals = [[7,10],[2,4]]
 *    Output: true
 *
 * Constraints:
 *    0 <= intervals.length <= 10^4
 *    intervals[i].length == 2
 *    0 <= starti < endi <= 10^6
 */
public class LC0252 {
   public boolean canAttendMeetings(int[][] intervals) {
      Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
      for (int i = 0; i < intervals.length - 1; i++) {
         if (intervals[i][1] > intervals[i + 1][0]) {
            return false;
         }
      }
      return true;
   }
}
