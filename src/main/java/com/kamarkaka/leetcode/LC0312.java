package com.kamarkaka.leetcode;

/***
 * 312. Burst Balloons
 * You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an
 * array nums. You are asked to burst all the balloons.
 * If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins. If i - 1 or i + 1 goes out of
 * bounds of the array, then treat it as if there is a balloon with a 1 painted on it.
 * Return the maximum coins you can collect by bursting the balloons wisely.
 * Example 1:
 *   Input: nums = [3,1,5,8]
 *   Output: 167
 *   Explanation:
 *   nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 *   coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 * Example 2:
 *   Input: nums = [1,5]
 *   Output: 10
 * Constraints:
 *   n == nums.length
 *   1 <= n <= 300
 *   0 <= nums[i] <= 100
 */
public class LC0312 {
    public int maxCoins(int[] iNums) {
        int[] nums = new int[iNums.length + 2];
        int n = 1;
        for (int x : iNums) if (x > 0) nums[n++] = x;
        nums[0] = nums[n++] = 1;
        int[][] memo = new int[n][n];
        return burst(memo, nums, 0, n - 1);
    }

    private int burst(int[][] memo, int[] nums, int left, int right) {
        if (left + 1 == right) return 0;
        if (memo[left][right] > 0) return memo[left][right];
        int coins = 0;
        for (int i = left + 1; i < right; i++) {
            int coin = nums[left] * nums[i] * nums[right];
            coin += burst(memo, nums, left, i) + burst(memo, nums, i, right);

            if (coin > coins) coins = coin;
        }

        memo[left][right] = coins;
        return coins;
    }
}
