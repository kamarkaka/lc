package com.kamarkaka.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/***
 * 1296. Divide Array in Sets of K Consecutive Numbers
 * Given an array of integers nums and a positive integer k, check whether it is possible to divide this array into sets of k consecutive numbers.
 * Return true if it is possible. Otherwise, return false.
 *
 * Example 1:
 *    Input: nums = [1,2,3,3,4,4,5,6], k = 4
 *    Output: true
 *    Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].
 *
 * Example 2:
 *    Input: nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
 *    Output: true
 *    Explanation: Array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and [9,10,11].
 *
 * Example 3:
 *    Input: nums = [1,2,3,4], k = 3
 *    Output: false
 *    Explanation: Each array should be divided in subarrays of size 3.
 *
 * Constraints:
 *    1 <= k <= nums.length <= 10^5
 *    1 <= nums[i] <= 10^9
 */
public class LC1296 {
   public boolean isPossibleDivide(int[] nums, int k) {
      int n = nums.length;
      if (n % k != 0) return false;

      Arrays.sort(nums);
      Map<Integer, Integer> cnt = new HashMap<>();
      for (int i = 0; i < n; i++) {
         cnt.put(nums[i], cnt.getOrDefault(nums[i], 0) + 1);
      }

      for (int i = 0; i < n; i++) {
         if (cnt.get(nums[i]) == 0) continue;
         int v = nums[i];
         for (int j = v; j <= v + k - 1; j++) {
            if (!cnt.containsKey(j) || cnt.get(j)==0) return false;
            cnt.put(j, cnt.get(j) - 1);
         }
      }
      return true;
   }
}
