package com.kamarkaka.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/***
 * 179. Largest Number
 * Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.
 * Since the result may be very large, so you need to return a string instead of an integer.
 * Example 1:
 *   Input: nums = [10,2]
 *   Output: "210"
 * Example 2:
 *   Input: nums = [3,30,34,5,9]
 *   Output: "9534330"
 * Constraints:
 *   1 <= nums.length <= 100
 *   0 <= nums[i] <= 10^9
 */
public class LC0179 {
    public String largestNumber(int[] nums) {
        String[] numList = new String[nums.length];
        boolean isAllZero = true;
        for (int i = 0; i < nums.length; i++) {
            numList[i] = String.valueOf(nums[i]);
            if (nums[i] != 0 && isAllZero) isAllZero = false;
        }

        if (isAllZero) return "0";

        Arrays.sort(numList, new Comparator<String>() {
            @Override
            public int compare(String num1, String num2) {
                return (num2 + num1).compareTo(num1 + num2);
            }
        });

        StringBuilder sb = new StringBuilder();
        for (String num : numList) {
            sb.append(num);
        }

        return sb.toString();
    }
}
