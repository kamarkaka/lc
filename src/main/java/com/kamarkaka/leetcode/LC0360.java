package com.kamarkaka.leetcode;

/***
 * 360. Sort Transformed Array
 * Given a sorted integer array nums and three integers a, b and c, apply a quadratic function of the form
 * f(x) = ax^2 + bx + c to each element nums[i] in the array, and return the array in a sorted order.
 * Example 1:
 *   Input: nums = [-4,-2,2,4], a = 1, b = 3, c = 5
 *   Output: [3,9,15,33]
 * Example 2:
 *   Input: nums = [-4,-2,2,4], a = -1, b = 3, c = 5
 *   Output: [-23,-5,1,7]
 * Constraints:
 *   1 <= nums.length <= 200
 *   -100 <= nums[i], a, b, c <= 100
 *   nums is sorted in ascending order.
 * Follow up: Could you solve it in O(n) time?
 */
public class LC0360 {
   public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
      int[] result = new int[nums.length];
      int resultIndex = a < 0 ? 0 : result.length - 1;
      int lo = 0, hi = nums.length - 1;
      while (lo <= hi) {
         int lc = calculate(a, b, c, nums[lo]);
         int hc = calculate(a, b, c, nums[hi]);
         if (a < 0) {
            if (lc < hc) {
               result[resultIndex++] = lc;
               lo++;
            } else {
               result[resultIndex++] = hc;
               hi--;
            }
         } else {
            if (lc > hc) {
               result[resultIndex--] = lc;
               lo++;
            } else {
               result[resultIndex--] = hc;
               hi--;
            }
         }
      }

      return result;
   }

   private int calculate(int a, int b, int c, int x) {
      return a * x * x + b * x + c;
   }
}
