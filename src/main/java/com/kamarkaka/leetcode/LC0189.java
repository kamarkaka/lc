package com.kamarkaka.leetcode;

/***
 * 189. Rotate Array
 * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
 * Example 1:
 *   Input: nums = [1,2,3,4,5,6,7], k = 3
 *   Output: [5,6,7,1,2,3,4]
 *   Explanation:
 *   rotate 1 steps to the right: [7,1,2,3,4,5,6]
 *   rotate 2 steps to the right: [6,7,1,2,3,4,5]
 *   rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * Example 2:
 *   Input: nums = [-1,-100,3,99], k = 2
 *   Output: [3,99,-1,-100]
 *   Explanation:
 *   rotate 1 steps to the right: [99,-1,-100,3]
 *   rotate 2 steps to the right: [3,99,-1,-100]
 * Constraints:
 *   1 <= nums.length <= 10^5
 *   -2^31 <= nums[i] <= 2^31 - 1
 *   0 <= k <= 10^5
 * Follow up:
 *   Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
 *   Could you do it in-place with O(1) extra space?
 */
public class LC0189 {
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) return;

        if (k > nums.length) k = k % nums.length;

        int[] temp = new int[k];
        for (int i = 0; i < k; i++) {
            temp[i] = nums[nums.length - k + i];
        }
        for (int i = nums.length - k - 1; i >= 0; i--) {
            nums[i + k] = nums[i];
        }
        for (int i = 0; i < k; i++) {
            nums[i] = temp[i];
        }
    }
}
