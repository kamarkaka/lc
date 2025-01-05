package com.kamarkaka.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/***
 * 215. Kth Largest Element in an Array
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * Can you solve it without sorting?
 * Example 1:
 *   Input: nums = [3,2,1,5,6,4], k = 2
 *   Output: 5
 * Example 2:
 *   Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 *   Output: 4
 * Constraints:
 *   1 <= k <= nums.length <= 10^5
 *   -10^4 <= nums[i] <= 10^4
 */
public class LC0215 {
   public int findKthLargest(int[] nums, int k) {
      PriorityQueue<Integer> pq = new PriorityQueue<>(k + 1);

      for (int num : nums) {
         pq.add(num);
         if (pq.size() > k) pq.poll();
      }

      return pq.peek();
   }
}
