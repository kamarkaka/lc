package com.kamarkaka.leetcode;

/***
 * 689. Maximum Sum of 3 Non-Overlapping Subarrays
 * Given an integer array nums and an integer k, find three non-overlapping subarrays of length k with maximum sum and
 * return them.
 * Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are
 * multiple answers, return the lexicographically smallest one.
 * Example 1:
 *   Input: nums = [1,2,1,2,6,7,5,1], k = 2
 *   Output: [0,3,5]
 *   Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
 *   We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
 * Example 2:
 *   Input: nums = [1,2,1,2,1,2,1,2,1], k = 2
 *   Output: [0,2,4]
 * Constraints:
 *   1 <= nums.length <= 2 * 10^4
 *   1 <= nums[i] < 2^16
 *   1 <= k <= floor(nums.length / 3)
 */
public class LC0689 {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int len = nums.length;
        int[] sums = new int[len];
        sums[0] = nums[0];

        for (int i = 1; i < len; i++) {
            sums[i] += sums[i - 1] + nums[i];
        }

        int[] left = new int[len];
        int[] right = new int[len];

        int maxSum = sums[k - 1];
        left[k - 1] = k - 1;
        for (int i = k; i < len - 2 * k; i++) {
            int sum = sums[i] - sums[i - k];
            if (sum > maxSum) {
                maxSum = sum;
                left[i] = i;
            } else {
                left[i] = left[i - 1];
            }
        }

        maxSum = sums[len - 1] - sums[len - k - 1];
        right[len - k] = len - k;
        for (int i = len - k - 1; i >= 2 * k; i--) {
            int sum = sums[i + k - 1] - sums[i - 1];
            if (sum >= maxSum) {
                maxSum = sum;
                right[i] = i;
            } else {
                right[i] = right[i + 1];
            }
        }

        maxSum = 0;
        int[] result = new int[3];
        for (int i = k; i < len - 2 * k + 1; i++) {
            int sum = sums[i + k - 1] - sums[i - 1];
            int leftIndex = left[i - 1];
            int rightIndex = right[i + k];

            if (leftIndex == k - 1) {
                sum += sums[leftIndex];
            } else {
                sum += sums[leftIndex] - sums[leftIndex - k];
            }
            sum += sums[rightIndex + k - 1] - sums[rightIndex - 1];

            if (maxSum < sum) {
                maxSum = sum;
                result[0] = leftIndex - k + 1;
                result[1] = i;
                result[2] = rightIndex;
            }
        }

        return result;
    }
}
