package com.kamarkaka.leetcode;

/***
 * 409. Longest Palindrome
 * Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that
 * can be built with those letters.
 * Letters are case sensitive, for example, "Aa" is not considered a palindrome.
 * Example 1:
 *   Input: s = "abccccdd"
 *   Output: 7
 *   Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.
 * Example 2:
 *   Input: s = "a"
 *   Output: 1
 *   Explanation: The longest palindrome that can be built is "a", whose length is 1.
 * Constraints:
 *   1 <= s.length <= 2000
 *   s consists of lowercase and/or uppercase English letters only.
 */
public class LC0409 {
    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) return 0;

        int[] counts = new int[128];

        for (char c : s.toCharArray()) {
            counts[c]++;
        }

        int result = 0;
        boolean hasOdd = false;
        for (int count : counts) {
            result += count - count % 2;

            if (!hasOdd && count % 2 == 1) hasOdd = true;
        }

        return hasOdd ? result + 1 : result;
    }
}
