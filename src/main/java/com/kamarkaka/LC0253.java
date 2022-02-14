package main.java.com.kamarkaka;

import java.util.*;

/***
 * 253. Meeting Rooms II
 * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.
 *
 * Example 1:
 *   Input: intervals = [[0,30],[5,10],[15,20]]
 *   Output: 2
 *
 * Example 2:
 *   Input: intervals = [[7,10],[2,4]]
 *   Output: 1
 *
 * Constraints:
 *   1 <= intervals.length <= 10^4
 *   0 <= starti < endi <= 10^6
 */
public class LC0253 {
   public int minMeetingRooms(int[][] intervals) {
      if (intervals.length == 0) {
         return 0;
      }

      PriorityQueue<Integer> allocator = new PriorityQueue<>();

      Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
      allocator.add(intervals[0][1]);

      for (int i = 1; i < intervals.length; i++) {
         if (intervals[i][0] >= allocator.peek()) {
            allocator.poll();
         }

         allocator.add(intervals[i][1]);
      }

      return allocator.size();
   }

   public static void run() {
      LC0253 solution = new LC0253();
      System.out.println(solution.minMeetingRooms(new int[][] {{0,30},{5,10},{15,20}}));
   }
}
