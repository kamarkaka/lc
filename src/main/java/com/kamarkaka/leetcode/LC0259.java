package com.kamarkaka.leetcode;

import java.util.Arrays;

/***
 * 259. 3Sum Smaller
 * Given an array of n integers nums and an integer target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 *
 * Example 1:
 *    Input: nums = [-2,0,1,3], target = 2
 *    Output: 2
 *    Explanation: Because there are two triplets which sums are less than 2:
 *       [-2,0,1]
 *       [-2,0,3]
 *
 * Example 2:
 *    Input: nums = [], target = 0
 *    Output: 0
 *
 * Example 3:
 *    Input: nums = [0], target = 0
 *    Output: 0
 *
 * Constraints:
 *    n == nums.length
 *    0 <= n <= 3500
 *    -100 <= nums[i] <= 100
 *    -100 <= target <= 100
 */
public class LC0259 {
   public int threeSumSmaller(int[] nums, int target) {
      Arrays.sort(nums);
      int sum = 0;
      for (int i = 0; i < nums.length - 2; i++) {
         sum += twoSumSmaller(nums, i + 1, target - nums[i]);
      }
      return sum;
   }

   private int twoSumSmaller(int[] nums, int startIndex, int target) {
      int sum = 0;
      int left = startIndex;
      int right = nums.length - 1;
      while (left < right) {
         if (nums[left] + nums[right] < target) {
            sum += right - left;
            left++;
         } else {
            right--;
         }
      }
      return sum;
   }

   public static void run() {
      LC0259 solution = new LC0259();
      System.out.println(solution.threeSumSmaller(new int[] {3,2,-2,6,2,-2,6,-2,-4,2,3,0,4,4,1}, 3));
      System.out.println(solution.threeSumSmaller(new int[] {1,-1,2,0,3,-2}, 2));
   }
}
