package com.kamarkaka;

import java.util.Arrays;

/***
 * 1509. Minimum Difference Between Largest and Smallest Value in Three Moves
 * You are given an integer array nums. In one move, you can choose one element of nums and change it by any value.
 * Return the minimum difference between the largest and smallest value of nums after performing at most three moves.
 *
 * Example 1:
 *   Input: nums = [5,3,2,4]
 *   Output: 0
 *   Explanation: Change the array [5,3,2,4] to [2,2,2,2].
 *   The difference between the maximum and minimum is 2-2 = 0.
 *
 * Example 2:
 *   Input: nums = [1,5,0,10,14]
 *   Output: 1
 *   Explanation: Change the array [1,5,0,10,14] to [1,1,0,1,1].
 *   The difference between the maximum and minimum is 1-0 = 1.
 *
 * Constraints:
 *   1 <= nums.length <= 10^5
 *   -10^9 <= nums[i] <= 10^9
 */
public class LC1509 {
   public int minDifference(int[] nums) {
      if (nums.length < 5) return 0;

      int[] smallest = new int[4];
      int[] largest = new int[4];

      smallest[0] = nums[0];
      smallest[1] = nums[1];
      smallest[2] = nums[2];
      smallest[3] = nums[3];

      largest[0] = nums[0];
      largest[1] = nums[1];
      largest[2] = nums[2];
      largest[3] = nums[3];

      Arrays.sort(smallest);
      Arrays.sort(largest);

      for (int i = 4; i < nums.length; i++) {
         int num = nums[i];
         if (num < smallest[0]) {
            smallest[3] = smallest[2];
            smallest[2] = smallest[1];
            smallest[1] = smallest[0];
            smallest[0] = num;
         } else if (num < smallest[1]) {
            smallest[3] = smallest[2];
            smallest[2] = smallest[1];
            smallest[1] = num;
         } else if (num < smallest[2]) {
            smallest[3] = smallest[2];
            smallest[2] = num;
         } else if (num < smallest[3]) {
            smallest[3] = num;
         }

         if (num > largest[3]) {
            largest[0] = largest[1];
            largest[1] = largest[2];
            largest[2] = largest[3];
            largest[3] = num;
         } else if (num > largest[2]) {
            largest[0] = largest[1];
            largest[1] = largest[2];
            largest[2] = num;
         } else if (num > largest[1]) {
            largest[0] = largest[1];
            largest[1] = num;
         } else if (num > largest[0]) {
            largest[0] = num;
         }
      }

      int minDiff = Integer.MAX_VALUE;
      for (int i = 0; i <= 3; i++) {
         minDiff = Math.min(largest[i] - smallest[i], minDiff);
      }

      return minDiff;
   }

   public static void run() {
      LC1509 solution = new LC1509();
      System.out.println(solution.minDifference(new int[] {9,48,92,48,81,31}));
      System.out.println(solution.minDifference(new int[] {9,5,0,10,14}));
      System.out.println(solution.minDifference(new int[] {5,3,2,4}));
   }
}
