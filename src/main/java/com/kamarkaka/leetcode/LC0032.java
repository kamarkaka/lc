package com.kamarkaka.leetcode;

/***
 * 32. Longest Valid Parentheses
 * Given a string containing just the characters '(' and ')', return the length of the longest valid (well-formed)
 * parentheses substring
 * Example 1:
 *   Input: s = "(()"
 *   Output: 2
 *   Explanation: The longest valid parentheses substring is "()".
 * Example 2:
 *   Input: s = ")()())"
 *   Output: 4
 *   Explanation: The longest valid parentheses substring is "()()".
 * Example 3:
 *   Input: s = ""
 *   Output: 0
 * Constraints:
 *   0 <= s.length <= 3 * 10^4
 *   s[i] is '(', or ')'.
 */
public class LC0032 {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) return 0;

        int[] dp = new int[s.length()];
        int result = 0;
        int stack = 0;

        for (int i = 0, len = s.length(); i < len; i++) {
            if (s.charAt(i) == '(') {
                stack++;
            } else if (stack > 0) {
                dp[i] = dp[i - 1] + 2;
                dp[i] += (i - dp[i]) >= 0 ? dp[i - dp[i]] : 0;
                result = Math.max(result, dp[i]);
                stack--;
            }
        }
        return result;

    }
}
