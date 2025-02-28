package com.kamarkaka.leetcode;

/***
 * 152. Maximum Product Subarray
 * Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product, and
 * return the product.
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 * A subarray is a contiguous subsequence of the array.
 * Example 1:
 *   Input: nums = [2,3,-2,4]
 *   Output: 6
 *   Explanation: [2,3] has the largest product 6.
 * Example 2:
 *   Input: nums = [-2,0,-1]
 *   Output: 0
 *   Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 * Constraints:
 *   1 <= nums.length <= 2 * 10^4
 *   -10 <= nums[i] <= 10
 *   The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 */
public class LC0152 {
   public int maxProduct(int[] nums) {
      if (nums.length == 0) return 0;

      int maxSoFar = nums[0];
      int minSoFar = nums[0];
      int result = maxSoFar;

      for (int i = 1; i < nums.length; i++) {
         int curr = nums[i];
         int tempMax = Math.max(curr, Math.max(maxSoFar * curr, minSoFar * curr));
         minSoFar = Math.min(curr, Math.min(maxSoFar * curr, minSoFar * curr));

         maxSoFar = tempMax;
         result = Math.max(maxSoFar, result);
      }
      return result;
   }
}
