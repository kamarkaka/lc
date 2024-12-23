package com.kamarkaka.leetcode;

import java.util.HashMap;
import java.util.Map;

/***
 * 2352. Equal Row and Column Pairs
 * Given a 0-indexed n x n integer matrix grid, return the number of pairs (ri, cj) such that row ri and column cj are
 * equal.
 * A row and column pair is considered equal if they contain the same elements in the same order (i.e., an equal array).
 * Example 1:
 *   Input: grid = [[3,2,1],[1,7,6],[2,7,7]]
 *   Output: 1
 *   Explanation: There is 1 equal row and column pair:
 *   - (Row 2, Column 1): [2,7,7]
 * Example 2:
 *   Input: grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
 *   Output: 3
 *   Explanation: There are 3 equal row and column pairs:
 *   - (Row 0, Column 0): [3,1,2,2]
 *   - (Row 2, Column 2): [2,4,2,2]
 *   - (Row 3, Column 2): [2,4,2,2]
 * Constraints:
 *   n == grid.length == grid[i].length
 *   1 <= n <= 200
 *   1 <= grid[i][j] <= 10^5
 */
public class LC2352 {
    public int equalPairs(int[][] grid) {
        Map<String, Integer> rowCount = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            int[] row = grid[i];
            String rowStr = arrayToString(row);
            rowCount.put(rowStr, rowCount.getOrDefault(rowStr, 0) + 1);
        }

        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            int[] col = new int[grid.length];

            for (int j = 0; j < grid.length; j++) {
                col[j] = grid[j][i];
            }
            String colStr = arrayToString(col);
            result += rowCount.getOrDefault(colStr, 0);
        }
        return result;
    }

    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int num : arr) {
            sb.append(num).append(',');
        }

        return sb.toString();
    }
}