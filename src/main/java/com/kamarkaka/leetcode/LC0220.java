package com.kamarkaka.leetcode;

/***
 * 220. Contains Duplicate III
 * You are given an integer array nums and two integers indexDiff and valueDiff.
 * Find a pair of indices (i, j) such that:
 *   i != j,
 *   abs(i - j) <= indexDiff.
 *   abs(nums[i] - nums[j]) <= valueDiff, and
 * Return true if such pair exists or false otherwise.
 * Example 1:
 *   Input: nums = [1,2,3,1], indexDiff = 3, valueDiff = 0
 *   Output: true
 *   Explanation: We can choose (i, j) = (0, 3).
 *   We satisfy the three conditions:
 *   i != j --> 0 != 3
 *   abs(i - j) <= indexDiff --> abs(0 - 3) <= 3
 *   abs(nums[i] - nums[j]) <= valueDiff --> abs(1 - 1) <= 0
 * Example 2:
 *   Input: nums = [1,5,9,1,5,9], indexDiff = 2, valueDiff = 3
 *   Output: false
 *   Explanation: After trying all the possible pairs (i, j), we cannot satisfy the three conditions, so we return
 *   false.
 * Constraints:
 *   2 <= nums.length <= 10^5
 *   -10^9 <= nums[i] <= 10^9
 *   1 <= indexDiff <= nums.length
 *   0 <= valueDiff <= 109
 */
public class LC0220 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        for (int i = 0; i < nums.length; i++) {
            long num1 = nums[i];
            for (int j = 1; j <= k; j++) {
                if (i + j >= nums.length) continue;
                long num2 = nums[i + j];
                if (Math.abs(num1 - num2) <= t) return true;
            }
        }
        return false;

    }
}
