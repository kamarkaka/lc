package com.kamarkaka.leetcode;

/***
 * 441. Arranging Coins
 * You have n coins and you want to build a staircase with these coins. The staircase consists of k rows where the ith
 * row has exactly i coins. The last row of the staircase may be incomplete.
 * Given the integer n, return the number of complete rows of the staircase you will build.
 * Example 1:
 *   $
 *   $ $
 *   $ $ _
 *   Input: n = 5
 *   Output: 2
 *   Explanation: Because the 3rd row is incomplete, we return 2.
 * Example 2:
 *   $
 *   $ $
 *   $ $ $
 *   $ $ _ _
 *   Input: n = 8
 *   Output: 3
 *   Explanation: Because the 4th row is incomplete, we return 3.
 * Constraints:
 *   1 <= n <= 2^31 - 1
 */
public class LC0441 {
    public int arrangeCoins(int n) {
        if (n <= 0) return 0;
        if (n < 3) return 1;

        int start = 1;
        int end = n;

        while (true) {
            if (start == end) {
                return start;
            } else if (start + 1 == end) {

                if (end / 2 * (end + 1) <= n) {
                    return end;
                } else {
                    return start;
                }
            } else {
                int middle = start + (end - start) / 2;

                int left, right;
                if (middle % 2 == 0) {
                    left = middle / 2;
                    right = n / (1 + middle);
                } else {
                    left = (middle + 1) / 2;
                    right = n / middle;
                }

                if (left == right) {
                    return middle;
                } else if (left < right) {
                    start = middle;
                } else {
                    end = middle;
                }
            }
        }
    }
}
