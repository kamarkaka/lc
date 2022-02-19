package com.kamarkaka;

import java.util.HashMap;
import java.util.Map;

/***
 * 2031. Count Subarrays With More Ones Than Zeros
 * You are given a binary array nums containing only the integers 0 and 1. Return the number of subarrays in nums that have more 1's than 0's. Since the answer may be very large, return it modulo 109 + 7.
 * A subarray is a contiguous sequence of elements within an array.
 *
 * Example 1:
 *    Input: nums = [0,1,1,0,1]
 *    Output: 9
 *    Explanation:
 *       The subarrays of size 1 that have more ones than zeros are: [1], [1], [1]
 *       The subarrays of size 2 that have more ones than zeros are: [1,1]
 *       The subarrays of size 3 that have more ones than zeros are: [0,1,1], [1,1,0], [1,0,1]
 *       The subarrays of size 4 that have more ones than zeros are: [1,1,0,1]
 *       The subarrays of size 5 that have more ones than zeros are: [0,1,1,0,1]
 *
 * Example 2:
 *    Input: nums = [0]
 *    Output: 0
 *    Explanation: No subarrays have more ones than zeros.
 *
 * Example 3:
 *    Input: nums = [1]
 *    Output: 1
 *    Explanation: The subarrays of size 1 that have more ones than zeros are: [1]
 *
 * Constraints:
 *    1 <= nums.length <= 10^5
 *    0 <= nums[i] <= 1
 */
public class LC2031 {
   public int subarraysWithMoreZerosThanOnes(int[] nums) {
      Map<Integer, Integer> hmap = new HashMap<>();
      int mod = 1_000_000_007;
      int[] dp = new int[nums.length];
      int sum = 0, res = 0;
      for (int i = 0; i < nums.length; i++) {
         sum += (nums[i] == 1 ? 1 : -1);
         if (!hmap.containsKey(sum)) {
            if (nums[i] == 1) {
               dp[i] = i + 1;
               if (sum == 0) dp[i]--;
            }
         } else {
            dp[i] = dp[hmap.get(sum)];
            if (nums[i] == 1) {
               dp[i] += i - hmap.get(sum) - 1;
            }
         }

         hmap.put(sum, i);
         res = (res + dp[i]) % mod;
      }
      return res;
   }
}
