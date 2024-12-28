package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.List;

/***
 * 697. Degree of an Array
 * Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency
 * of any one of its elements.
 * Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as
 * nums.
 * Example 1:
 *   Input: nums = [1,2,2,3,1]
 *   Output: 2
 *   Explanation:
 *   The input array has a degree of 2 because both elements 1 and 2 appear twice.
 *   Of the subarrays that have the same degree:
 *   [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 *   The shortest length is 2. So return 2.
 * Example 2:
 *   Input: nums = [1,2,2,3,1,4,2]
 *   Output: 6
 *   Explanation:
 *   The degree is 3 because the element 2 is repeated 3 times.
 *   So [2,2,3,1,4,2] is the shortest subarray, therefore returning 6.
 * Constraints:
 *   nums.length will be between 1 and 50,000.
 *   nums[i] will be an integer between 0 and 49,999.
 */
public class LC0697 {
    public int findShortestSubArray(int[] nums) {
        int[] map = new int[50000];

        for (int num : nums) {
            map[num]++;
        }

        int degree = 0;
        List<Integer> degreeNums = new ArrayList<>();

        for (int num = 0; num < map.length; num++) {
            int count = map[num];

            if (count == 0) continue;

            if (count > degree) {
                degree = count;
                degreeNums.clear();
                degreeNums.add(num);
            } else if (count == degree) {
                degreeNums.add(num);
            }
        }

        int len = nums.length;
        for (int num : degreeNums) {
            int numLen = 0;
            int start = 0, end = nums.length - 1;
            for (; start < nums.length; start++) {
                if (nums[start] == num) break;
            }

            for (; end >= 0; end--) {
                if (nums[end] == num) break;
            }

            numLen = end - start + 1;
            len = Math.min(len, numLen);
        }

        return len;
    }
}
