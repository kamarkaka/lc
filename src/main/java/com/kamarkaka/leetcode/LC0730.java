package com.kamarkaka.leetcode;

/***
 * 730. Count Different Palindromic Subsequences
 * Given a string s, return the number of different non-empty palindromic subsequences in s. Since the answer may be
 * very large, return it modulo 10^9 + 7.
 * A subsequence of a string is obtained by deleting zero or more characters from the string.
 * A sequence is palindromic if it is equal to the sequence reversed.
 * Two sequences a1, a2, ... and b1, b2, ... are different if there is some i for which ai != bi.
 * Example 1:
 *   Input: s = "bccb"
 *   Output: 6
 *   Explanation: The 6 different non-empty palindromic subsequences are 'b', 'c', 'bb', 'cc', 'bcb', 'bccb'.
 *   Note that 'bcb' is counted only once, even though it occurs twice.
 * Example 2:
 *   Input: s = "abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba"
 *   Output: 104860361
 *   Explanation: There are 3104860382 different non-empty palindromic subsequences, which is 104860361 modulo 10^9 + 7.
 * Constraints:
 *   1 <= s.length <= 1000
 *   s[i] is either 'a', 'b', 'c', or 'd'.
 */
public class LC0730 {
    public int countPalindromicSubsequences(String s) {
        int n = s.length();
        int[] rightNext = rightNext(s, new int[] {n, n, n, n});
        int[] leftNext = leftNext(s, new int[] {-1, -1, -1, -1});
        int[][] dp = new int[n][n];
        for (int d = 0; d < n; d++) {
            for (int i = 0, j = i+d; j < n; i++, j++) {
                if (i == j) dp[i][j] = 1;
                else if (s.charAt(i) != s.charAt(j)) dp[i][j] = dp[i+1][j] + dp[i][j-1] - dp[i+1][j-1];
                else {
                    int r = rightNext[i], l = leftNext[j];
                    int extra = (r < l) ? - dp[r+1][l-1] : (r == l) ? 1 : 2;
                    dp[i][j] = 2*dp[i+1][j-1] + extra;
                }
                dp[i][j] = dp[i][j] < 0 ? dp[i][j] + 1000000007 : dp[i][j] % 1000000007;
            }
        }
        return dp[0][n - 1];
    }
    private int[] leftNext(String s, int[] rec) {
        int n = s.length(), res[] = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = rec[s.charAt(i) - 'a'];
            rec[s.charAt(i) - 'a'] = i;
        }
        return res;
    }
    private int[] rightNext(String s, int[] rec) {
        int n = s.length(), res[] = new int[n];
        for (int i = n-1; i >= 0; i--) {
            res[i] = rec[s.charAt(i) - 'a'];
            rec[s.charAt(i) - 'a'] = i;
        }
        return res;
    }
}
