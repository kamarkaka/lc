package com.kamarkaka.leetcode;

/***
 * 74. Search a 2D Matrix
 * You are given an m x n integer matrix matrix with the following two properties:
 *   Each row is sorted in non-decreasing order.
 *   The first integer of each row is greater than the last integer of the previous row.
 * Given an integer target, return true if target is in matrix or false otherwise.
 * You must write a solution in O(log(m * n)) time complexity.
 * Example 1:
 *   Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 *   Output: true
 * Example 2:
 *   Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 *   Output: false
 * Constraints:
 *   m == matrix.length
 *   n == matrix[i].length
 *   1 <= m, n <= 100
 *   -10^4 <= matrix[i][j], target <= 10^4
 */
public class LC0074 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        if (target < matrix[0][0]) return false;

        int rows = matrix.length, cols = matrix[0].length, i = 0;

        if (rows > 1) i = findRow(matrix, target, 0, rows - 1);
        return findNum(matrix[i], target, 0, cols - 1);
    }

    private int findRow(int[][] matrix, int target, int sr, int er) {
        if (sr == er) {
            if (target >= matrix[sr][0]) return sr;
        }

        int mr = sr + (er - sr) / 2;
        if (target >= matrix[mr][0] && target < matrix[mr + 1][0]) {
            return mr;
        } else if (target < matrix[mr][0]) {
            return findRow(matrix, target, sr, mr - 1);
        } else {
            return findRow(matrix, target, mr + 1, er);
        }
    }

    private boolean findNum(int[] nums, int target, int sc, int ec) {
        if (sc == ec) {
            if (nums[sc] == target) return true;
            else return false;
        }

        int mc = sc + (ec - sc) / 2;
        if (target == nums[mc]) return true;
        else if (target > nums[mc]) {
            if (mc + 1 > ec) return false;
            return findNum(nums, target, mc + 1, ec);
        } else {
            if (sc > mc - 1) return false;
            return findNum(nums, target, sc, mc - 1);
        }
    }
}
