package com.kamarkaka.leetcode;

import com.kamarkaka.common.Utilities;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/***
 * 1950. Maximum of Minimum Values in All Subarrays
 * You are given an integer array nums of size n. You are asked to solve n queries for each integer i in the range 0 <= i < n.
 * To solve the ith query:
 *    Find the minimum value in each possible subarray of size i + 1 of the array nums.
 *    Find the maximum of those minimum values. This maximum is the answer to the query.
 * Return a 0-indexed integer array ans of size n such that ans[i] is the answer to the ith query.
 * A subarray is a contiguous sequence of elements in an array.
 *
 * Example 1:
 *    Input: nums = [0,1,2,4]
 *    Output: [4,2,1,0]
 *    Explanation:
 *       i=0:
 *       - The subarrays of size 1 are [0], [1], [2], [4]. The minimum values are 0, 1, 2, 4.
 *       - The maximum of the minimum values is 4.
 *       i=1:
 *       - The subarrays of size 2 are [0,1], [1,2], [2,4]. The minimum values are 0, 1, 2.
 *       - The maximum of the minimum values is 2.
 *       i=2:
 *       - The subarrays of size 3 are [0,1,2], [1,2,4]. The minimum values are 0, 1.
 *       - The maximum of the minimum values is 1.
 *       i=3:
 *       - There is one subarray of size 4, which is [0,1,2,4]. The minimum value is 0.
 *       - There is only one value, so the maximum is 0.
 *
 * Example 2:
 *    Input: nums = [10,20,50,10]
 *    Output: [50,20,10,10]
 *    Explanation:
 *       i=0:
 *       - The subarrays of size 1 are [10], [20], [50], [10]. The minimum values are 10, 20, 50, 10.
 *       - The maximum of the minimum values is 50.
 *       i=1:
 *       - The subarrays of size 2 are [10,20], [20,50], [50,10]. The minimum values are 10, 20, 10.
 *       - The maximum of the minimum values is 20.
 *       i=2:
 *       - The subarrays of size 3 are [10,20,50], [20,50,10]. The minimum values are 10, 10.
 *       - The maximum of the minimum values is 10.
 *       i=3:
 *       - There is one subarray of size 4, which is [10,20,50,10]. The minimum value is 10.
 *       - There is only one value, so the maximum is 10.
 *
 * Constraints:
 *    n == nums.length
 *    1 <= n <= 10^5
 *    0 <= nums[i] <= 10^9
 */
public class LC1950 {
   public int[] findMaximums(int[] nums) {
      int n = nums.length;
      int[] ans = new int[n];

      int[] L = new int[n];
      int[] R = new int[n];
      Arrays.fill(L, -1);
      Arrays.fill(R, n);

      // Queue is mono increasing.
      Deque<Integer> queue = new ArrayDeque<>();

      for (int i = 0; i < n; i++) {
         while (!queue.isEmpty() && nums[i] < nums[queue.getLast()]) {
            int index = queue.removeLast();
            R[index] = i;
         }
         L[i] = queue.isEmpty() ? -1 : queue.getLast();
         queue.add(i);
      }

      for (int i = 0; i < n; i++) {
         int index = R[i] - L[i] - 2;
         ans[index] = Math.max(ans[index], nums[i]);
      }
      int tmp = ans[n - 1];
      for (int i = n - 1; i >= 0; i--) {
         ans[i] = Math.max(tmp, ans[i]);
         tmp = ans[i];
      }
      return ans;
   }

   public static void run() {
      LC1950 sol = new LC1950();
      Utilities.print(sol.findMaximums(new int[] {3,2,1,5,8}));
   }
}
