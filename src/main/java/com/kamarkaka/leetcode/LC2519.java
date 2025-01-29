package com.kamarkaka.leetcode;

import java.util.Collections;
import java.util.PriorityQueue;

/***
 * 2519. Count the Number of K-Big Indices
 * You are given a 0-indexed integer array nums and a positive integer k.
 * We call an index i k-big if the following conditions are satisfied:
 *   There exist at least k different indices idx1 such that idx1 < i and nums[idx1] < nums[i].
 *   There exist at least k different indices idx2 such that idx2 > i and nums[idx2] < nums[i].
 * Return the number of k-big indices.
 * Example 1:
 *   Input: nums = [2,3,6,5,2,3], k = 2
 *   Output: 2
 *   Explanation: There are only two 2-big indices in nums:
 *   - i = 2 --> There are two valid idx1: 0 and 1. There are three valid idx2: 2, 3, and 4.
 *   - i = 3 --> There are two valid idx1: 0 and 1. There are two valid idx2: 3 and 4.
 * Example 2:
 *   Input: nums = [1,1,1], k = 3
 *   Output: 0
 *   Explanation: There are no 3-big indices in nums.
 * Constraints:
 *   1 <= nums.length <= 10^5
 *   1 <= nums[i], k <= nums.length
 */
public class LC2519 {
   public int kBigIndices(int[] nums, int k) {
      int n = nums.length;
      boolean[] prefix = new boolean[n];
      // pq holds smallest k elements at any time
      PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
      for (int i = 0; i < n; i++) {
         if (pq.size() == k && pq.peek() < nums[i]) {
            prefix[i] = true;
         }
         pq.add(nums[i]);
         if (pq.size() > k) pq.poll();
      }

      int result = 0;
      pq.clear();
      for (int i = n - 1; i >= 0; i--) {
         if (pq.size() == k && pq.peek() < nums[i] && prefix[i]) {
            result++;
         }
         pq.add(nums[i]);
         if (pq.size() > k) pq.poll();
      }
      return result;
   }
}
