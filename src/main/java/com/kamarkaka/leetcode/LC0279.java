package com.kamarkaka.leetcode;

import java.util.Arrays;

/***
 * 279. Perfect Squares
 * Given an integer n, return the least number of perfect square numbers that sum to n.
 * A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer
 * with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
 * Example 1:
 *   Input: n = 12
 *   Output: 3
 *   Explanation: 12 = 4 + 4 + 4.
 * Example 2:
 *   Input: n = 13
 *   Output: 2
 *   Explanation: 13 = 4 + 9.
 * Constraints:
 *   1 <= n <= 10^4
 */
public class LC0279 {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i <= n; i++) {
            for (int k = 1; k * k <= i; k++) {
                dp[i] = Math.min(dp[i], 1 + dp[i - k * k]);
            }
        }
        return dp[n];
    }
}
