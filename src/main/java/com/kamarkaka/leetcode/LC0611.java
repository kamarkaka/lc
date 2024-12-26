package com.kamarkaka.leetcode;

import java.util.Arrays;

/***
 * 611. Valid Triangle Number
 * Given an integer array nums, return the number of triplets chosen from the array that can make triangles if we take
 * them as side lengths of a triangle.
 * Example 1:
 *   Input: nums = [2,2,3,4]
 *   Output: 3
 *   Explanation: Valid combinations are:
 *   2,3,4 (using the first 2)
 *   2,3,4 (using the second 2)
 *   2,2,3
 * Example 2:
 *   Input: nums = [4,2,3,4]
 *   Output: 4
 * Constraints:
 *   1 <= nums.length <= 1000
 *   0 <= nums[i] <= 1000
 */
public class LC0611 {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int result = 0;
        for (int i = nums.length - 1; i >= 2; i--) {
            int start = 0, end = i - 1;
            while (start < end) {
                if (nums[start] + nums[end] <= nums[i]) {
                    start++;
                    continue;
                }
                result += (end - start);
                end--;
            }
        }
        return result;
    }
}
