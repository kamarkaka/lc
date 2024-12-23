package com.kamarkaka.leetcode;

/***
 * 45. Jump Game II
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 * You can assume that you can always reach the last index.
 *
 * Example 1:
 *    Input: nums = [2,3,1,1,4]
 *    Output: 2
 *    Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * Example 2:
 *    Input: nums = [2,3,0,1,4]
 *    Output: 2
 *
 * Constraints:
 *    1 <= nums.length <= 10^4
 *    0 <= nums[i] <= 1000
 */
public class LC0045 {
   public int jump(int[] nums) {
      if (nums.length == 1) return 0;

      int[] dp = new int[nums.length];
      dp[nums.length - 1] = 0;

      for (int i = nums.length - 2; i >= 0; i--) {
         int steps = nums[i];
         dp[i] = -1;

         for (int j = i + 1; j <= i + steps; j++) {
            if (j >= nums.length || dp[j] == -1) continue;

            dp[i] = dp[i] == -1 ? (1 + dp[j]) : Math.min(dp[i], 1 + dp[j]);
         }
      }

      return dp[0];
   }

   public int jump2(int[] nums) {
      int jumps = 0, currentJumpEnd = 0, farthest = 0;
      for (int i = 0; i < nums.length - 1; i++) {
         // we continuously find how far we can reach in the current jump
         farthest = Math.max(farthest, i + nums[i]);
         // if we have come to the end of the current jump,
         // we need to make another jump
         if (i == currentJumpEnd) {
            jumps++;
            currentJumpEnd = farthest;
         }
      }
      return jumps;
   }

   public static void run() {
      LC0045 sol = new LC0045();
      System.out.println(sol.jump2(new int[] {2,3,1,1,4}));
   }
}
