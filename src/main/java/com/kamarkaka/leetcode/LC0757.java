package com.kamarkaka.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/***
 * 757. Set Intersection Size At Least Two
 * An integer interval [a, b] (for integers a < b) is a set of all consecutive integers from a to b, including a and b.
 * Find the minimum size of a set S such that for every integer interval A in intervals, the intersection of S with A has a size of at least two.
 *
 * Example 1:
 *    Input: intervals = [[1,3],[1,4],[2,5],[3,5]]
 *    Output: 3
 *    Explanation: Consider the set S = {2, 3, 4}.  For each interval, there are at least 2 elements from S in the interval.
 *    Also, there isn't a smaller size set that fulfills the above condition.
 *    Thus, we output the size of this set, which is 3.
 *
 * Example 2:
 *    Input: intervals = [[1,2],[2,3],[2,4],[4,5]]
 *    Output: 5
 *    Explanation: An example of a minimum sized set is {1, 2, 3, 4, 5}.
 *
 * Constraints:
 *    1 <= intervals.length <= 3000
 *    intervals[i].length == 2
 *    0 <= ai < bi <= 108
 */
public class LC0757 {
   public int intersectionSizeTwo(int[][] intervals) {
      Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

      int secondLast = intervals[0][1] - 1;
      int last = intervals[0][1];
      int res = 2;
      for (int i = 1; i < intervals.length; i++) {
         int start = intervals[i][0], end = intervals[i][1];
         if (start > last) {
            last = end;
            secondLast = end - 1;
            res += 2;
         } else if (start == last || (start < last && start > secondLast)) {
            secondLast = last;
            last = end;
            res++;
         }
      }
      return res;
   }
}
