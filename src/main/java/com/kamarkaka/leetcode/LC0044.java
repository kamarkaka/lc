package com.kamarkaka.leetcode;

/***
 * 44. Wildcard Matching
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
 *   '?' Matches any single character.
 *   '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 * Example 1:
 *   Input: s = "aa", p = "a"
 *   Output: false
 *   Explanation: "a" does not match the entire string "aa".
 * Example 2:
 *   Input: s = "aa", p = "*"
 *   Output: true
 *   Explanation: '*' matches any sequence.
 * Example 3:
 *   Input: s = "cb", p = "?a"
 *   Output: false
 *   Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 * Constraints:
 *   0 <= s.length, p.length <= 2000
 *   s contains only lowercase English letters.
 *   p contains only lowercase English letters, '?' or '*'.
 */
public class LC0044 {
    public boolean isMatch(String s, String p) {
        if (s.length() == 0 && p.length() == 0) return true;
        int i = 0, j = 0;
        int sp = 0, pp = -1;

        while (i < s.length()) {
            if (j < p.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '?')) {
                i++;
                j++;
            } else if (j < p.length() && p.charAt(j) == '*') {
                sp = i;
                pp = j;
                j++;
            } else if (pp != -1) {
                j = pp + 1;
                sp++;
                i = sp;
            } else {
                return false;
            }
        }

        while (j < p.length() && p.charAt(j) == '*') {
            j++;
        }
        return j == p.length();
    }
}
