package com.kamarkaka;

import java.util.*;

/***
 * 954. Array of Doubled Pairs
 * Given an integer array of even length arr, return true if it is possible to reorder arr such that arr[2 * i + 1] = 2 * arr[2 * i] for every 0 <= i < len(arr) / 2, or false otherwise.
 *
 * Example 1:
 *    Input: arr = [3,1,3,6]
 *    Output: false
 *
 * Example 2:
 *    Input: arr = [2,1,2,6]
 *    Output: false
 *
 * Example 3:
 *    Input: arr = [4,-2,2,-4]
 *    Output: true
 *    Explanation: We can take two groups, [-2,-4] and [2,4] to form [-2,-4,2,4] or [2,4,-2,-4].
 *
 * Constraints:
 *    2 <= arr.length <= 3 * 10^4
 *    arr.length is even.
 *    -10^5 <= arr[i] <= 10^5
 */
public class LC0954 {
   public boolean canReorderDoubled(int[] arr) {
      // count[x] = the number of occurrences of x in A
      Map<Integer, Integer> count = new HashMap<>();
      for (int x : arr) {
         count.put(x, count.getOrDefault(x, 0) + 1);
      }

      // sorted by absolute value
      Integer[] arr2 = new Integer[arr.length];
      for (int i = 0; i < arr.length; i++) {
         arr2[i] = arr[i];
      }
      Arrays.sort(arr2, Comparator.comparingInt(Math::abs));

      for (int x : arr2) {
         // If this can't be consumed, skip
         if (count.get(x) == 0) continue;
         // If this doesn't have a doubled partner, the answer is false
         if (count.getOrDefault(2 * x, 0) <= 0) return false;

         // Write x, 2*x
         count.put(x, count.get(x) - 1);
         count.put(2 * x, count.get(2 * x) - 1);
      }
      return true;
   }
}
