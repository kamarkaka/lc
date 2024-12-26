package com.kamarkaka.leetcode;

/***
 * 213. House Robber II
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money
 * stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last
 * one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two
 * adjacent houses were broken into on the same night.
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you
 * can rob tonight without alerting the police.
 * Example 1:
 *   Input: nums = [2,3,2]
 *   Output: 3
 *   Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
 * Example 2:
 *   Input: nums = [1,2,3,1]
 *   Output: 4
 *   Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 *   Total amount you can rob = 1 + 3 = 4.
 * Example 3:
 *   Input: nums = [1,2,3]
 *   Output: 3
 * Constraints:
 *   1 <= nums.length <= 100
 *   0 <= nums[i] <= 1000
 */
public class LC0213 {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int[] dp0 = new int[nums.length];
        int[] dp1 = new int[nums.length];

        dp0[0] = nums[0];
        dp1[1] = nums[1];

        helper(dp0, dp1, nums);
        return Math.max(dp0[nums.length - 2], dp1[nums.length - 1]);
    }

    private void helper(int[] dp0, int[] dp1, int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int money = nums[i];
            if (i == 1) {
                dp0[i] = dp0[i - 1];
            } else if (i < nums.length - 1) {
                dp0[i] = Math.max(dp0[i - 1], dp0[i - 2] + money);
            }

            if (i == 2) {
                dp1[i] = Math.max(dp1[i - 1], money);
            } else if (i > 2) {
                dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + money);
            }
        }
    }
}
