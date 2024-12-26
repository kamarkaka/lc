package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.List;

/***
 * 163. Missing Ranges
 * You are given an inclusive range [lower, upper] and a sorted unique integer array nums, where all elements are within
 * the inclusive range.
 * A number x is considered missing if x is in the range [lower, upper] and x is not in nums.
 * Return the shortest sorted list of ranges that exactly covers all the missing numbers. That is, no element of nums is
 * included in any of the ranges, and each missing number is covered by one of the ranges.
 * Example 1:
 *   Input: nums = [0,1,3,50,75], lower = 0, upper = 99
 *   Output: [[2,2],[4,49],[51,74],[76,99]]
 *   Explanation: The ranges are:
 *   [2,2]
 *   [4,49]
 *   [51,74]
 *   [76,99]
 * Example 2:
 *   Input: nums = [-1], lower = -1, upper = -1
 *   Output: []
 *   Explanation: There are no missing ranges since there are no missing numbers.
 * Constraints:
 *   -10^9 <= lower <= upper <= 10^9
 *   0 <= nums.length <= 100
 *   lower <= nums[i] <= upper
 *   All the values of nums are unique.
 */
public class LC0163 {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0)  {
            if (lower == upper) result.add(String.valueOf(lower));
            else result.add(lower + "->" + upper);
            return result;
        }

        int i = 0, a = nums[i], b = lower;

        while (i < nums.length) {
            if (a > b) {
                if (b == a - 1) {
                    result.add(String.valueOf(b));
                } else {
                    result.add(b + "->" + (a - 1));
                }
            }

            if (a == upper) return result;

            b = Math.max(a + 1, lower);
            i++;
            a = i == nums.length ? upper : nums[i];
        }

        if (b <= a) {
            if (b == a) result.add(String.valueOf(b));
            else result.add(b + "->" + a);
        }

        return result;
    }
}
