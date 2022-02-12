package com.kamarkaka;

/***
 * 76. Minimum Window Substring
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
 * The testcases will be generated such that the answer is unique.
 * A substring is a contiguous sequence of characters within the string.
 *
 * Example 1:
 *  Input: s = "ADOBECODEBANC", t = "ABC"
 *  Output: "BANC"
 *  Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 *
 * Example 2:
 *  Input: s = "a", t = "a"
 *  Output: "a"
 *  Explanation: The entire string s is the minimum window.
 *
 * Example 3:
 *  Input: s = "a", t = "aa"
 *  Output: ""
 *  Explanation: Both 'a's from t must be included in the window.
 *  Since the largest window of s only has one 'a', return empty string.
 *
 * Constraints:
 *  m == s.length
 *  n == t.length
 *  1 <= m, n <= 10^5
 *  s and t consist of uppercase and lowercase English letters.
 */
public class LC0076 {
    public String minWindow(String s, String t) {
        if (s == null || t == null) {
            return "";
        }

        int result = Integer.MAX_VALUE;
        int end = 0, begin = 0, head = 0, count = t.length();
        int[] hash = new int[256];

        for (int i = 0; i < t.length(); i++) {
            hash[t.charAt(i) - '0']++;
        }

        while (end < s.length()) {
            if (hash[s.charAt(end++) - '0']-- > 0) {
                count--;
            }

            while (count == 0) {
                if (end - begin < result) {
                    head = begin;
                    result = end - begin;
                }

                if (hash[s.charAt(begin++) - '0']++ == 0) {
                    count++;
                }
            }
        }

        if (result != Integer.MAX_VALUE) {
            return s.substring(head, head + result);
        }
        return "";
    }

    public static void run() {
        LC0076 solution = new LC0076();
        System.out.println(solution.minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(solution.minWindow("a", "a"));
        System.out.println(solution.minWindow("a", "aa"));
    }
}
