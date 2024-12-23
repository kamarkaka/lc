package com.kamarkaka.leetcode;

import java.util.HashMap;
import java.util.Map;

/***
 * 1218. Longest Arithmetic Subsequence of Given Difference
 * Given an integer array arr and an integer difference, return the length of the longest subsequence in arr which is an arithmetic sequence such that the difference between adjacent elements in the subsequence equals difference.
 * A subsequence is a sequence that can be derived from arr by deleting some or no elements without changing the order of the remaining elements.
 *
 * Example 1:
 *    Input: arr = [1,2,3,4], difference = 1
 *    Output: 4
 *    Explanation: The longest arithmetic subsequence is [1,2,3,4].
 *
 * Example 2:
 *    Input: arr = [1,3,5,7], difference = 1
 *    Output: 1
 *    Explanation: The longest arithmetic subsequence is any single element.
 *
 * Example 3:
 *    Input: arr = [1,5,7,8,5,3,4,2,1], difference = -2
 *    Output: 4
 *    Explanation: The longest arithmetic subsequence is [7,5,3,1].
 *
 * Constraints:
 *    1 <= arr.length <= 10^5
 *    -10^4 <= arr[i], difference <= 10^4
 */
public class LC1218 {
   public int longestSubsequence(int[] arr, int difference) {
      Map<Integer, Integer> dp = new HashMap<>(); // arr[i] -> max subsequence length arr[i] is associated to upto 'i'
      int res = 0;

      for(int i = 0; i < arr.length; i++) {
         dp.put(arr[i], dp.getOrDefault(arr[i] - difference, 0) + 1);
         res = Math.max(res, dp.get(arr[i]));
      }

      return res;
   }
}
