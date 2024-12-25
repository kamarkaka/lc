package com.kamarkaka.leetcode;

/***
 * 52. N-Queens II
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each
 * other.
 * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 * Example 1:
 *   Input: n = 4
 *   Output: 2
 *   Explanation: There are two distinct solutions to the 4-queens puzzle as shown.
 * Example 2:
 *   Input: n = 1
 *   Output: 1
 * Constraints:
 *   1 <= n <= 9
 */
public class LC0052 {
    public int totalNQueens(int n) {
        return backtrack(0, 0, 0, 0, 0, n);
    }

    private int backtrack(int row, int hills, int next_row, int dales, int count, int n) {
        int columns = (1 << n) - 1;
        if (row == n) count++;
        else {
            int free_columns = columns & ~(hills | next_row | dales);
            while (free_columns != 0) {
                int curr_column = - free_columns & free_columns;
                free_columns ^= curr_column;
                count = backtrack(
                        row + 1,
                        (hills | curr_column) << 1,
                        next_row | curr_column,
                        (dales | curr_column) >> 1,
                        count, n);
            }
        }
        return count;
    }
}
