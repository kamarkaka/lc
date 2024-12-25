package com.kamarkaka.leetcode;

/***
 * 75. Sort Colors
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color
 * are adjacent, with the colors in the order red, white, and blue.
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 * You must solve this problem without using the library's sort function.
 * Example 1:
 *   Input: nums = [2,0,2,1,1,0]
 *   Output: [0,0,1,1,2,2]
 * Example 2:
 *   Input: nums = [2,0,1]
 *   Output: [0,1,2]
 * Constraints:
 *   n == nums.length
 *   1 <= n <= 300
 *   nums[i] is either 0, 1, or 2.
 * Follow up: Could you come up with a one-pass algorithm using only constant extra space?
 */
public class LC0075 {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length < 2) return;

        int len = nums.length, left = 0, right = len - 1;
        int curr = left;

        while (curr <= right) {
            if (nums[curr] == 0) {
                swap(nums, left, curr);
                left++;
            } else if (nums[curr] == 2) {
                swap(nums, right, curr);
                right--;
            } else {
                curr++;
            }

            if (curr < left) curr = left;
        }
    }

    private void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }
}
