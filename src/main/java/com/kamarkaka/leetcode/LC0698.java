package com.kamarkaka.leetcode;

import java.util.Arrays;
import java.util.HashMap;

/***
 * 698. Partition to K Equal Sum Subsets
 * Given an integer array nums and an integer k, return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.
 *
 * Example 1:
 *   Input: nums = [4,3,2,3,5,2,1], k = 4
 *   Output: true
 *   Explanation: It is possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 *
 * Example 2:
 *   Input: nums = [1,2,3,4], k = 3
 *   Output: false
 *
 * Constraints:
 *   1 <= k <= nums.length <= 16
 *   1 <= nums[i] <= 104
 *   The frequency of each element is in the range [1, 4].
 */
public class LC0698 {
   private boolean backtrack(int[] arr, int index, int count, int currSum, int k, int targetSum, char[] taken, HashMap<String, Boolean> memo) {
      int n = arr.length;

      // We made k - 1 subsets with target sum and last subset will also have target sum.
      if (count == k - 1) {
         return true;
      }

      // No need to proceed further.
      if (currSum > targetSum) {
         return false;
      }

      String takenStr = new String(taken);

      // If we have already computed the current combination.
      if (memo.containsKey(takenStr)) {
         return memo.get(takenStr);
      }

      // When curr sum reaches target then one subset is made.
      // Increment count and reset current sum.
      if (currSum == targetSum) {
         boolean ans = backtrack(arr, 0, count + 1, 0, k, targetSum, taken, memo);
         memo.put(takenStr, ans);
         return ans;
      }

      // Try not picked elements to make some combinations.
      for (int j = index; j < n; ++j) {
         if (taken[j] == '0') {
            // Include this element in current subset.
            taken[j] = '1';

            // If using current jth element in this subset leads to make all valid subsets.
            if (backtrack(arr, j + 1, count, currSum + arr[j], k, targetSum, taken, memo)) {
               return true;
            }

            // Backtrack step.
            taken[j] = '0';
         }
      }

      // We were not able to make a valid combination after picking each element from array,
      // hence we can't make k subsets.
      memo.put(takenStr, false);
      return false;
   }

   void reverse(int[] arr) {
      for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
         int temp = arr[i];
         arr[i] = arr[j];
         arr[j] = temp;
      }
   }

   public boolean canPartitionKSubsets(int[] arr, int k) {
      int totalArraySum = 0;
      int n = arr.length;

      for (int i = 0; i < n; ++i) {
         totalArraySum += arr[i];
      }

      // If total sum not divisible by k, we can't make subsets.
      if (totalArraySum % k != 0) {
         return false;
      }

      // Sort in decreasing order.
      Arrays.sort(arr);
      reverse(arr);

      int targetSum = totalArraySum / k;

      char[] taken = new char[n];
      for(int i = 0; i < n; ++i) {
         taken[i] = '0';
      }

      // Memoize the ans using taken element's string as key.
      HashMap<String, Boolean> memo = new HashMap<>();

      return backtrack(arr, 0, 0, 0, k, targetSum, taken, memo);
   }
}

