package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.List;

/***
 * 229. Majority Element II
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * Example 1:
 *   Input: nums = [3,2,3]
 *   Output: [3]
 * Example 2:
 *   Input: nums = [1]
 *   Output: [1]
 * Example 3:
 *   Input: nums = [1,2]
 *   Output: [1,2]
 * Constraints:
 *   1 <= nums.length <= 5 * 10^4
 *   -10^9 <= nums[i] <= 10^9
 * Follow up: Could you solve the problem in linear time and in O(1) space?
 */
public class LC0229 {
    public List<Integer> majorityElement(int[] nums) {
        int candidate1 = 0, candidate2 = 0;
        int vote1 = 0, vote2 = 0;
        for (int num: nums) {
            if (candidate1 == num) vote1++;
            else if (candidate2 == num) vote2++;
            else if (vote1 == 0) {
                candidate1 = num;
                vote1++;
            } else if (vote2 == 0) {
                candidate2 = num;
                vote2++;
            } else {
                vote1--;
                vote2--;
            }
        }

        vote1 = 0; vote2 = 0;
        for (int num: nums) {
            if (candidate1 == num) vote1++;
            if (candidate2 == num) vote2++;
        }

        List<Integer> elements = new ArrayList<>();
        if (vote1 > nums.length / 3) elements.add(candidate1);
        if (vote2 > nums.length / 3 && candidate1 != candidate2) elements.add(candidate2);
        return elements;
    }
}
