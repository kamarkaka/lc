package com.kamarkaka.leetcode;

/***
 * 41. First Missing Positive
 * Given an unsorted integer array nums. Return the smallest positive integer that is not present in nums.
 * You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.
 * Example 1:
 *   Input: nums = [1,2,0]
 *   Output: 3
 *   Explanation: The numbers in the range [1,2] are all in the array.
 * Example 2:
 *   Input: nums = [3,4,-1,1]
 *   Output: 2
 *   Explanation: 1 is in the array but 2 is missing.
 * Example 3:
 *   Input: nums = [7,8,9,11,12]
 *   Output: 1
 *   Explanation: The smallest positive integer 1 is missing.
 * Constraints:
 *   1 <= nums.length <= 10^5
 *   -2^31 <= nums[i] <= 2^31 - 1
 */
public class LC0041 {
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0 && nums[i] <= nums.length && nums[i] != i + 1) {
                int j = nums[i];
                while (j > 0 && j <= nums.length && nums[j - 1] != j) {
                    int tmp = nums[j - 1];
                    nums[j - 1] = j;
                    j = tmp;
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) return i + 1;
        }

        return nums.length + 1;
    }
}
