package com.kamarkaka.leetcode;

/***
 * 1216. Valid Palindrome III
 * Given a string s and an integer k, return true if s is a k-palindrome.
 * A string is k-palindrome if it can be transformed into a palindrome by removing at most k characters from it.
 * Example 1:
 *   Input: s = "abcdeca", k = 2
 *   Output: true
 *   Explanation: Remove 'b' and 'e' characters.
 * Example 2:
 *   Input: s = "abbababa", k = 1
 *   Output: true
 * Constraints:
 *   1 <= s.length <= 1000
 *   s consists of only lowercase English letters.
 *   1 <= k <= s.length
 */
public class LC1216 {
    public boolean isValidPalindrome(String s, int k) {
        int[][] dp = new int[s.length()][s.length()];
        for (int j = 1; j < s.length(); j++) {
            int row = 0;
            int col = j;
            while (col < s.length()) {
                if (s.charAt(col) == s.charAt(col - j)) {
                    dp[row][col] = dp[row + 1][col - 1];
                } else {
                    dp[row][col] = Math.min(dp[row][col - 1], dp[row + 1][col]) + 1;
                }
                row++;
                col++;
            }

        }
        return dp[0][s.length() - 1] <= k;
    }
}
