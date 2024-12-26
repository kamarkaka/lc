package com.kamarkaka.leetcode;

import java.util.HashMap;
import java.util.Map;

/***
 * 219. Contains Duplicate II
 * Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such
 * that nums[i] == nums[j] and abs(i - j) <= k.
 * Example 1:
 *   Input: nums = [1,2,3,1], k = 3
 *   Output: true
 * Example 2:
 *   Input: nums = [1,0,1,1], k = 1
 *   Output: true
 * Example 3:
 *   Input: nums = [1,2,3,1,2,3], k = 2
 *   Output: false
 * Constraints:
 *   1 <= nums.length <= 10^5
 *   -10^9 <= nums[i] <= 10^9
 *   0 <= k <= 10^5
 */
public class LC0219 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length < 1) return false;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], i);
            } else if (i - map.get(nums[i]) <= k) return true;
            else {
                map.put(nums[i], i);
            }
        }

        return false;
    }
}
