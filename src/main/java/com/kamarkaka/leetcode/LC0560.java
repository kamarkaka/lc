package com.kamarkaka.leetcode;

import java.util.HashMap;
import java.util.Map;

/***
 * 560. Subarray Sum Equals K
 * Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to
 * k.
 * Example 1:
 *   Input: nums = [1,1,1], k = 2
 *   Output: 2
 * Example 2:
 *   Input: nums = [1,2,3], k = 3
 *   Output: 2
 * Constraints:
 *   1 <= nums.length <= 2 * 10^4
 *   -1000 <= nums[i] <= 1000
 *   -10^7 <= k <= 10^7
 */
public class LC0560 {
   public int subarraySum(int[] nums, int k) {
      int count = 0;
      Map<Integer, Integer> sums = new HashMap<>();
      sums.put(0, 1);

      int sum = 0;
      for (int num : nums) {
         sum += num;
         count += sums.getOrDefault(sum - k, 0);

         sums.put(sum, sums.getOrDefault(sum, 0) + 1);
      }

      return count;
   }
}
