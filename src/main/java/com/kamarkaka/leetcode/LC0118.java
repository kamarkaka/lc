package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.List;

/***
 * 118. Pascal's Triangle
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 *     1
 *    1 1
 *   1 2 1
 *  1 3 3 1
 * 1 4 6 4 1
 * Example 1:
 *   Input: numRows = 5
 *   Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * Example 2:
 *   Input: numRows = 1
 *   Output: [[1]]
 * Constraints:
 *   1 <= numRows <= 30
 */
public class LC0118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        for (int i = 1; i <= numRows; i++) {
            List<Integer> newList = new ArrayList<Integer>();

            for (int j = 0; j < i; j++) {
                int val;

                if (j == 0 || j == i - 1) {
                    val = 1;
                } else {
                    val = result.get(i - 2).get(j - 1) + result.get(i - 2).get(j);
                }

                newList.add(val);
            }

            result.add(newList);
        }

        return result;
    }
}
