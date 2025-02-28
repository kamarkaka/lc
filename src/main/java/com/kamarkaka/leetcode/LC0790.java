package com.kamarkaka.leetcode;

/***
 * 790. Domino and Tromino Tiling
 * You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.
 * ++            ++
 *               +
 * Domino tile | Tromino tile
 * Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large, return it
 * modulo 109 + 7.
 * In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two
 * 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.
 * Example 1:
 *   Input: n = 3
 *   Output: 5
 *   Explanation: The five different ways are show above.
 * Example 2:
 *   Input: n = 1
 *   Output: 1
 */
public class LC0790 {
    public int numTilings(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        long[][] dp = new long[n][3];

        dp[0][0] = 1;
        dp[0][1] = 0;
        dp[0][2] = 0;

        dp[1][0] = 2;
        dp[1][1] = 1;
        dp[1][2] = 1;

        for (int i = 2; i < n; i++) {
            dp[i][0] = dp[i - 1][0] % 1_000_000_007 + dp[i - 2][0] % 1_000_000_007 + dp[i - 1][1] % 1_000_000_007 + dp[i - 1][2] % 1_000_000_007;
            dp[i][1] = dp[i - 2][0] % 1_000_000_007 + dp[i - 1][2] % 1_000_000_007;
            dp[i][2] = dp[i - 2][0] % 1_000_000_007 + dp[i - 1][1] % 1_000_000_007;
        }

        return (int) (dp[n - 1][0] % 1_000_000_007);
    }
}
