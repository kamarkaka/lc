package com.kamarkaka.leetcode;

/***
 * 665. Non-decreasing Array
 * Given an array nums with n integers, your task is to check if it could become non-decreasing by modifying at most one
 * element.
 * We define an array is non-decreasing if nums[i] <= nums[i + 1] holds for every i (0-based) such that
 * (0 <= i <= n - 2).
 * Example 1:
 *   Input: nums = [4,2,3]
 *   Output: true
 *   Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
 * Example 2:
 *   Input: nums = [4,2,1]
 *   Output: false
 *   Explanation: You cannot get a non-decreasing array by modifying at most one element.
 * Constraints:
 *   n == nums.length
 *   1 <= n <= 10^4
 *   -10^5 <= nums[i] <= 10^5
 */
public class LC0665 {
    public boolean checkPossibility(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        if (nums.length == 1) return true;

        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                count++;

                boolean fixable1 = true;
                boolean fixable2 = true;

                if (i - 1 >= 0) {
                    if (nums[i - 1] > nums[i + 1]) fixable1 = false;
                    else fixable1 = true;
                }

                if (i + 2 <= nums.length - 1) {
                    if (nums[i] > nums[i + 2]) fixable2 = false;
                    else fixable2 = true;
                }

                if (!fixable1 && !fixable2) return false;

            }
        }

        return count <= 1;
    }
}
