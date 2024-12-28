package com.kamarkaka.leetcode;

import java.util.Arrays;

/***
 * 628. Maximum Product of Three Numbers
 * Given an integer array nums, find three numbers whose product is maximum and return the maximum product.
 * Example 1:
 *   Input: nums = [1,2,3]
 *   Output: 6
 * Example 2:
 *   Input: nums = [1,2,3,4]
 *   Output: 24
 * Example 3:
 *   Input: nums = [-1,-2,-3]
 *   Output: -6
 * Constraints:
 *   3 <= nums.length <= 10^4
 *   -1000 <= nums[i] <= 1000
 */
public class LC0628 {
    public int maximumProduct(int[] nums) {
        if (nums == null || nums.length < 3) return 0;

        int product = 0;

        Arrays.sort(nums);

        if (nums[0] <= 0 && nums[1] <= 0) {
            product = nums[0] * nums[1] * nums[nums.length - 1];
        }

        return Math.max(product, nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3]);
    }
}
