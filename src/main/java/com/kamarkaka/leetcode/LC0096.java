package com.kamarkaka.leetcode;

/***
 * 96. Unique Binary Search Trees
 * Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of
 * unique values from 1 to n.
 * Example 1:
 *   Input: n = 3
 *   Output: 5
 * Example 2:
 *   Input: n = 1
 *   Output: 1
 * Constraints:
 *   1 <= n <= 19
 */
public class LC0096 {
    public int numTrees(int n) {
        if (n<=1) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= i - 1; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }
}
