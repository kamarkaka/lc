package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * 51. N-Queens
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each
 * other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a
 * queen and an empty space, respectively.
 * Example 1:
 *   Input: n = 4
 *   Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 *   Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
 * Example 2:
 *   Input: n = 1
 *   Output: [["Q"]]
 * Constraints:
 *   1 <= n <= 9
 */
public class LC0051 {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        if (n <= 0) return result;

        int[] solution = new int[n];
        Arrays.fill(solution, -1);
        solve(solution, 0, result);

        return result;
    }

    private void solve(int[] solution, int currRow, List<List<String>> result) {
        int n = solution.length;
        if (currRow == n) {
            result.add(printSolution(solution));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (isValid(solution, currRow, i)) {
                solution[currRow] = i;
                solve(solution, currRow + 1, result);
                solution[currRow] = -1;
            }
        }
    }

    private boolean isValid(int[] solution, int currRow, int index) {
        for (int i = 0; i < currRow; i++) {
            if (solution[i] == index || solution[i] - (currRow - i) == index || solution[i] + (currRow - i) == index) return false;
        }
        return true;
    }

    private List<String> printSolution(int[] solution) {
        List<String> result = new ArrayList<>();
        int n = solution.length;

        for (int col : solution) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < col; i++) sb.append('.');
            sb.append('Q');
            for (int i = col + 1; i < n; i++) sb.append('.');
            result.add(sb.toString());
        }

        return result;
    }
}
