package com.kamarkaka.leetcode;

/***
 * 647. Palindromic Substrings
 * Given a string s, return the number of palindromic substrings in it.
 * A string is a palindrome when it reads the same backward as forward.
 * A substring is a contiguous sequence of characters within the string.
 * Example 1:
 *   Input: s = "abc"
 *   Output: 3
 *   Explanation: Three palindromic strings: "a", "b", "c".
 * Example 2:
 *   Input: s = "aaa"
 *   Output: 6
 *   Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 * Constraints:
 *   1 <= s.length <= 1000
 *   s consists of lowercase English letters.
 */
public class LC0647 {
    public int countSubstrings(String s) {
        int count = 0;

        //odd
        int p = 0;
        while (p < s.length()) {
            int i = 0;
            while (p - i >= 0 && p + i < s.length()) {
                if (s.charAt(p - i) == s.charAt(p + i)) {
                    count++;
                } else break;
                i++;
            }
            p++;
        }

        //even
        int p0 = 0, p1 = 1;
        while (p0 >= 0 && p1 < s.length()) {
            int i = 0;
            while (p0 - i >= 0 && p1 + i < s.length()) {
                if (s.charAt(p0 - i) == s.charAt(p1 + i)) {
                    count++;
                } else break;
                i++;
            }
            p0++;
            p1++;
        }

        return count;
    }
}
