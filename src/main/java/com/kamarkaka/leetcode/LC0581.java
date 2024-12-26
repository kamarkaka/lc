package com.kamarkaka.leetcode;

/***
 * 581. Shortest Unsorted Continuous Subarray
 * Given an integer array nums, you need to find one continuous subarray such that if you only sort this subarray in
 * non-decreasing order, then the whole array will be sorted in non-decreasing order.
 * Return the shortest such subarray and output its length.
 * Example 1:
 *   Input: nums = [2,6,4,8,10,9,15]
 *   Output: 5
 *   Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending
 *   order.
 * Example 2:
 *   Input: nums = [1,2,3,4]
 *   Output: 0
 * Example 3:
 *   Input: nums = [1]
 *   Output: 0
 * Constraints:
 *   1 <= nums.length <= 10^4
 *   -10^5 <= nums[i] <= 10^5
 * Follow up: Can you solve it in O(n) time complexity?
 */
public class LC0581 {
    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int max = nums[0], right = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            if (nums[i] < max) {
                right = i;
            }
        }

        int min = nums[nums.length - 1], left = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            min = Math.min(min, nums[i]);
            if (nums[i] > min) {
                left = i;
            }
        }

        return right - left + 1;
    }
}
