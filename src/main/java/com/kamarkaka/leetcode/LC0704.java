package com.kamarkaka.leetcode;

/***
 * 704. Binary Search
 * Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search
 * target in nums. If target exists, then return its index. Otherwise, return -1.
 * You must write an algorithm with O(log n) runtime complexity.
 * Example 1:
 *   Input: nums = [-1,0,3,5,9,12], target = 9
 *   Output: 4
 *   Explanation: 9 exists in nums and its index is 4
 * Example 2:
 *   Input: nums = [-1,0,3,5,9,12], target = 2
 *   Output: -1
 *   Explanation: 2 does not exist in nums so return -1
 * Constraints:
 *   1 <= nums.length <= 10^4
 *   -10^4 < nums[i], target < 10^4
 *   All the integers in nums are unique.
 *   nums is sorted in ascending order.
 */
public class LC0704 {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        if (target < nums[0] || target > nums[nums.length - 1]) return -1;

        int min = 0, max = nums.length - 1;

        while (min <= max) {
            if (target == nums[min]) return min;
            if (target == nums[max]) return max;

            int p = (min + max) / 2;
            if (p == min) p++;
            else if (p == max) p--;

            if (p == max || p == min) break;

            if (target == nums[p]) return p;
            else if (target < nums[p]) max = p;
            else {
                min = p;
            }
        }

        return -1;
    }
}
