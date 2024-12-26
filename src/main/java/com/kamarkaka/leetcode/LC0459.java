package com.kamarkaka.leetcode;

/***
 * 459. Repeated Substring Pattern
 * Given a string s, check if it can be constructed by taking a substring of it and appending multiple copies of the
 * substring together.
 * Example 1:
 *   Input: s = "abab"
 *   Output: true
 *   Explanation: It is the substring "ab" twice.
 * Example 2:
 *   Input: s = "aba"
 *   Output: false
 * Example 3:
 *   Input: s = "abcabcabcabc"
 *   Output: true
 *   Explanation: It is the substring "abc" four times or the substring "abcabc" twice.
 * Constraints:
 *   1 <= s.length <= 10^4
 *   s consists of lowercase English letters.
 */
public class LC0459 {
    public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() < 2) return false;

        for (int i = 1; i <= s.length() / 2; i++) {
            if (s.length() % i == 0) {
                String pattern = s.substring(0, i);

                int j = 0;
                for (; j < s.length(); j+=i) {
                    if (!pattern.equals(s.substring(j, j + i))) break;
                }

                if (j == s.length()) {
                    return true;
                }
            }
        }

        return false;
    }
}
