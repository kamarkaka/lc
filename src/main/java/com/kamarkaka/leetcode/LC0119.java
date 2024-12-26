package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.List;

/***
 * 119. Pascal's Triangle II
 * Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 *     1
 *    1 1
 *   1 2 1
 *  1 3 3 1
 * 1 4 6 4 1
 * Example 1:
 *   Input: rowIndex = 3
 *   Output: [1,3,3,1]
 * Example 2:
 *   Input: rowIndex = 0
 *   Output: [1]
 * Example 3:
 *   Input: rowIndex = 1
 *   Output: [1,1]
 * Constraints:
 *   0 <= rowIndex <= 33
 * Follow up: Could you optimize your algorithm to use only O(rowIndex) extra space?
 */
public class LC0119 {
    public List<Integer> getRow(int rowIndex) {
        int[] list = new int[rowIndex + 1];

        for (int i = 0; i <= rowIndex; i++) {
            int val = 1;

            for (int j = i; j > 0; j--) {
                list[j] += list[j-1];
            }

            list[i] = val;
        }

        list[rowIndex] = 1;

        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i <= rowIndex; i++) {
            result.add(list[i]);
        }
        return result;
    }
}
