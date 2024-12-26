package com.kamarkaka.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/***
 * 594. Longest Harmonious Subsequence
 * We define a harmonious array as an array where the difference between its maximum value and its minimum value is
 * exactly 1.
 * Given an integer array nums, return the length of its longest harmonious subsequence among all its possible
 * subsequences.
 * Example 1:
 *   Input: nums = [1,3,2,2,5,2,3,7]
 *   Output: 5
 *   Explanation: The longest harmonious subsequence is [3,2,2,2,3].
 * Example 2:
 *   Input: nums = [1,2,3,4]
 *   Output: 2
 *   Explanation: The longest harmonious subsequences are [1,2], [2,3], and [3,4], all of which have a length of 2.
 * Example 3:
 *   Input: nums = [1,1,1,1]
 *   Output: 0
 *   Explanation: No harmonic subsequence exists.
 * Constraints:
 *   1 <= nums.length <= 2 * 10^4
 *   -10^9 <= nums[i] <= 10^9
 */
public class LC0594 {
    public int findLHS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int[] newNums = new int[map.size()];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            newNums[i] = entry.getKey();
            i++;
        }

        Arrays.sort(newNums);

        int result = 0;
        for (i = 0; i < newNums.length - 1; i++) {
            if (newNums[i + 1] - newNums[i] == 1) {
                result = Math.max(result, map.get(newNums[i]) + map.get(newNums[i + 1]));
            }
        }
        return result;
    }
}
