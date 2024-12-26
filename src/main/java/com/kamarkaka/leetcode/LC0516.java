package com.kamarkaka.leetcode;

/***
 * 516. Longest Palindromic Subsequence
 * Given a string s, find the longest palindromic subsequence's length in s.
 * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without
 * changing the order of the remaining elements.
 * Example 1:
 *   Input: s = "bbbab"
 *   Output: 4
 *   Explanation: One possible longest palindromic subsequence is "bbbb".
 * Example 2:
 *   Input: s = "cbbd"
 *   Output: 2
 *   Explanation: One possible longest palindromic subsequence is "bb".
 * Constraints:
 *   1 <= s.length <= 1000
 *   s consists only of lowercase English letters.
 */
public class LC0516 {
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];
        helper(s, 0, s.length() - 1, dp);
        return dp[0][s.length() - 1];
    }

    public int helper(String s, int start, int end, int[][] dp) {
        if (start > end) return 0;
        if (dp[start][end] > 0) return dp[start][end];
        if (start == end) {
            dp[start][end] = 1;
            return 1;
        }

        int len;
        if (s.charAt(start) == s.charAt(end)) {
            len = 2 + helper(s, start + 1, end - 1, dp);

        } else {
            int p1 = helper(s, start + 1, end, dp);
            int p2 = helper(s, start, end - 1, dp);
            int p3 = helper(s, start + 1, end - 1, dp);
            len = Math.max(p1, Math.max(p2, p3));
        }

        dp[start][end] = len;
        return len;
    }
}
