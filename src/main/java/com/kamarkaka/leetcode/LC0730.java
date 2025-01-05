package com.kamarkaka.leetcode;

import java.util.Arrays;

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
 *
 * Intuition and Algorithm
 * Let dp[x][i][j] be the answer for the substring S[i...j] where S[i] == S[j] == 'a' + x. Note that since we only have
 * 4 characters a, b, c, d, thus 0 <= x < 4. The DP formula goes as follows:
 *   If S[i] != 'a'+x, then dp[x][i][j] = dp[x][i + 1][j], note that here we leave the first character S[i] in the
 *   window out due to our definition of dp[x][i][j].
 *   If S[j] != 'a' + x, then dp[x][i][j] = dp[x][i][j - 1], leaving the last character S[j] out.
 *   If S[i] == S[j] == 'a' + x, then dp[x][i][j] = 2 + dp[0][i + 1][j - 1] + dp[1][i + 1][j - 1] + dp[2][i + 1][j - 1]
 *   + dp[3][i + 1][j - 1]. When the first and last characters are the same, we need to count all the distinct
 *   palindromes (for each of a, b, c, d) within the sub-window S[i + 1][j - 1] plus the 2 palindromes contributed by
 *   the first and last characters.
 * Let n be the length of the input string S, The final answer would be
 * dp[0][0][n - 1] + dp[1][0][n - 1] + dp[2][0][n - 1] + dp[3][0][n - 1] mod 1000000007.
 * Complexity Analysis
 *   Time complexity : O(N^2) where N is the length of the input string S. It takes quadratic time to fill up the DP
 *   table.
 *   Space complexity : O(N^2) where N is the length of the input string S. The DP table takes quadratic space.
 */
public class LC0730 {
    int MOD = 1_000_000_007;

    public int countPalindromicSubsequences(String S) {
        int N = S.length();
        int[][][] dp = new int[4][N][N];


        for (int i = N - 1; i >= 0; i--) {
            for (int j = i; j < N; j++) {
                for (int k = 0; k < 4; k++) {
                    char c = (char) ('a' + k);
                    if (j == i) {
                        if (S.charAt(i) == c) {
                            dp[k][i][j] = 1;
                        } else {
                            dp[k][i][j] = 0;
                        }
                    } else { // j > i
                        if (S.charAt(i) != c) {
                            dp[k][i][j] = dp[k][i + 1][j];
                        } else if (S.charAt(j) != c) {
                            dp[k][i][j] = dp[k][i][j - 1];
                        } else { // S[i] == S[j] == c
                            if (j == i + 1) {
                                dp[k][i][j] = 2; // "aa" : {"a", "aa"}
                            } else { // length is > 2
                                dp[k][i][j] = 2;
                                for (int m = 0; m < 4; m++) { // count each one within subwindows [i + 1][j - 1]
                                    dp[k][i][j] += dp[m][i + 1][j - 1];
                                    dp[k][i][j] %= MOD;
                                }
                            }
                        }
                    }
                }
            }
        }

        int res = 0;
        for (int k = 0; k < 4; ++k) {
            res += dp[k][0][N - 1];
            res %= MOD;
        }
        return res;
    }
}
