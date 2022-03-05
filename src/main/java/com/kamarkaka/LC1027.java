package com.kamarkaka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 1027. Longest Arithmetic Subsequence
 * Given an array nums of integers, return the length of the longest arithmetic subsequence in nums.
 * Recall that a subsequence of an array nums is a list nums[i1], nums[i2], ..., nums[ik] with 0 <= i1 < i2 < ... < ik <= nums.length - 1, and that a sequence seq is arithmetic if seq[i+1] - seq[i] are all the same value (for 0 <= i < seq.length - 1).
 *
 * Example 1:
 *    Input: nums = [3,6,9,12]
 *    Output: 4
 *    Explanation:
 *       The whole array is an arithmetic sequence with steps of length = 3.
 *
 * Example 2:
 *    Input: nums = [9,4,7,2,10]
 *    Output: 3
 *    Explanation:
 *       The longest arithmetic subsequence is [4,7,10].
 *
 * Example 3:
 *    Input: nums = [20,1,15,3,10,5,8]
 *    Output: 4
 *    Explanation:
 *       The longest arithmetic subsequence is [20,15,10,5].
 *
 * Constraints:
 *    2 <= nums.length <= 1000
 *    0 <= nums[i] <= 500
 */
public class LC1027 {
   public int longestArithSeqLength(int[] nums) {
      int maxLen = 0;
      List<Map<Integer, Integer>> dpMaps = new ArrayList<>();
      for (int i = 0; i < nums.length; i++) {
         dpMaps.add(new HashMap<>());
      }

      for (int i = 0; i < nums.length; i++) {
         for (int j = i + 1; j < nums.length; j++) {
            int currNum = nums[i];
            int nextNum = nums[j];
            int diff = nextNum - currNum;
            int count = 0;
            if (dpMaps.get(i).containsKey(diff)) {
               count = dpMaps.get(i).get(diff);
            }

            int oldCount = dpMaps.get(j).getOrDefault(diff, 0);
            dpMaps.get(j).put(diff, Math.max(count + 1, oldCount));
            maxLen = Math.max(maxLen, count + 2);
         }
      }

      return maxLen;
   }

   public static void run() {
      LC1027 sol = new LC1027();
      System.out.println(sol.longestArithSeqLength(new int[] {3,6,9,12}));
      System.out.println(sol.longestArithSeqLength(new int[] {9,4,7,2,10}));
      System.out.println(sol.longestArithSeqLength(new int[] {20,1,15,3,10,5,8}));
   }
}
