package com.kamarkaka.leetcode;

/***
 * 214. Shortest Palindrome
 * You are given a string s. You can convert s to a palindrome by adding characters in front of it.
 * Return the shortest palindrome you can find by performing this transformation.
 * Example 1:
 *   Input: s = "aacecaaa"
 *   Output: "aaacecaaa"
 * Example 2:
 *   Input: s = "abcd"
 *   Output: "dcbabcd"
 * Constraints:
 *   0 <= s.length <= 5 * 10^4
 *   s consists of lowercase English letters only.
 */
public class LC0214 {
    public String shortestPalindrome(String s) {
        int j = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(j) == s.charAt(i)) j++;
        }
        if (j == s.length()) return s;
        return new StringBuilder(s.substring(j)).reverse().append(shortestPalindrome(s.substring(0, j))).append(s.substring(j)).toString();
    }
}
