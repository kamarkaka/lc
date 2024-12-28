package com.kamarkaka.leetcode;

/***
 * 896. Monotonic Array
 * An array is monotonic if it is either monotone increasing or monotone decreasing.
 * An array nums is monotone increasing if for all i <= j, nums[i] <= nums[j]. An array nums is monotone decreasing if
 * for all i <= j, nums[i] >= nums[j].
 * Given an integer array nums, return true if the given array is monotonic, or false otherwise.
 * Example 1:
 *   Input: nums = [1,2,2,3]
 *   Output: true
 * Example 2:
 *   Input: nums = [6,5,4,4]
 *   Output: true
 * Example 3:
 *   Input: nums = [1,3,2]
 *   Output: false
 * Constraints:
 *   1 <= nums.length <= 10^5
 *   -10^5 <= nums[i] <= 10^5
 */
public class LC0896 {
    public boolean isMonotonic(int[] A) {
        if (A == null || A.length < 3) return true;

        int dir = 0;

        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] < A[i]) {
                if (dir == -1) return false;
                if (dir == 0) dir = 1;
            } else if (A[i - 1] > A[i]) {
                if (dir == 1) return false;
                if (dir == 0) dir = -1;
            }
        }

        return true;
    }
}
