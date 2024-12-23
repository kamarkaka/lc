package com.kamarkaka.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/***
 * 1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
 * Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that the absolute difference between any two elements of this subarray is less than or equal to limit.
 *
 * Example 1:
 *   Input: nums = [8,2,4,7], limit = 4
 *   Output: 2
 *   Explanation: All subarrays are:
 *     [8] with maximum absolute diff |8-8| = 0 <= 4.
 *     [8,2] with maximum absolute diff |8-2| = 6 > 4.
 *     [8,2,4] with maximum absolute diff |8-2| = 6 > 4.
 *     [8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
 *     [2] with maximum absolute diff |2-2| = 0 <= 4.
 *     [2,4] with maximum absolute diff |2-4| = 2 <= 4.
 *     [2,4,7] with maximum absolute diff |2-7| = 5 > 4.
 *     [4] with maximum absolute diff |4-4| = 0 <= 4.
 *     [4,7] with maximum absolute diff |4-7| = 3 <= 4.
 *     [7] with maximum absolute diff |7-7| = 0 <= 4.
 *     Therefore, the size of the longest subarray is 2.
 *
 * Example 2:
 *   Input: nums = [10,1,2,4,7,2], limit = 5
 *   Output: 4
 *   Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
 *
 * Example 3:
 *   Input: nums = [4,2,2,2,4,4,2,2], limit = 0
 *   Output: 3
 *
 * Constraints:
 *   1 <= nums.length <= 10^5
 *   1 <= nums[i] <= 10^9
 *   0 <= limit <= 10^9
 */
public class LC1438 {
   public int longestSubarray(int[] nums, int limit) {
      Deque<Integer> min = new ArrayDeque<>(); // maintains increasing order of the value index. So that min front will always have smallest value.
      Deque<Integer> max = new ArrayDeque<>();// maintains decreasing order of the value index. So that max front will always have the greatest value.
      int longest = 1;
      int lo = 0, hi = 0;

      while (hi < nums.length) {
         int val = nums[hi];
         // from behind remove all the index that have values less than val.
         while (!min.isEmpty() && nums[min.peekLast()] >= val) min.pollLast();
         // add val index to the end. Hence the increasing order will be maintained
         min.addLast(hi);
         while (!max.isEmpty() && nums[max.peekLast()] <= val) max.pollLast();
         // add val index to the end. Hence maintaining decreasing order.
         max.add(hi);

         int minVal = nums[min.peekFirst()]; // min front will have min value index
         int maxVal = nums[max.peekFirst()]; // max front will have max value index
         if (maxVal - minVal <= limit) {
            longest = Math.max(longest, hi - lo + 1); // right-left+1 will tell no of elements present in the current subarray
            hi++;
         } else {
            // As the if condition failed we will increment left index.
            // And from the new left index new subarray will start.
            // Hence all the indexes less than left should be removed from min and max deque as they are not part of the current subarray.*/
            lo++;
            while (lo > min.peekFirst()) min.pollFirst();
            while (lo > max.peekFirst()) max.pollFirst();
         }
      }

      return longest;
   }

   public static void run() {
      LC1438 solution = new LC1438();
      System.out.println(solution.longestSubarray(new int[] {4,2,2,2,4,4,2,2}, 0));
      System.out.println(solution.longestSubarray(new int[] {10,1,2,4,7,2}, 5));
      System.out.println(solution.longestSubarray(new int[] {8,2,4,7}, 4));
   }
}
