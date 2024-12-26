package com.kamarkaka.leetcode;

/***
 * 416. Partition Equal Subset Sum
 * Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the
 * elements in both subsets is equal or false otherwise.
 * Example 1:
 *   Input: nums = [1,5,11,5]
 *   Output: true
 *   Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * Example 2:
 *   Input: nums = [1,2,3,5]
 *   Output: false
 *   Explanation: The array cannot be partitioned into equal sum subsets.
 * Constraints:
 *   1 <= nums.length <= 200
 *   1 <= nums[i] <= 100
 */
public class LC0416 {
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length < 2) return false;

        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 != 0) return false;
        return helper(nums.length - 1, nums, 0, sum / 2);
    }

    private boolean helper(int i, int[] n, int sum, int target) {
        if (i < 0 || sum > target) return false;
        if (n[i] > target) return false;
        if (sum == target) return true;
        return helper(i - 1, n, sum + n[i], target) || helper(i - 1, n, sum, target);
    }
}
