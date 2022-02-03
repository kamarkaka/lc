/***
 * Implement strStr().
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *
 * Clarification:
 *   What should we return when needle is an empty string? This is a great question to ask during an interview.
 *   For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 *
 * Example 1:
 *   Input: haystack = "hello", needle = "ll"
 *   Output: 2
 *
 * Example 2:
 *   Input: haystack = "aaaaa", needle = "bba"
 *   Output: -1
 *
 * Example 3:
 *   Input: haystack = "", needle = ""
 *   Output: 0
 *
 * Constraints:
 *   0 <= haystack.length, needle.length <= 5 * 10^4
 *   haystack and needle consist of only lower-case English characters.
 */

package com.kamarkaka;

public class LC0028 {
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) return 0;
        if (haystack == null || haystack.length() == 0) return -1;

        int m = haystack.length(), n = needle.length();
        if (m == n) return haystack.equals(needle) ? 0 : -1;
        if (m < n) return -1;

        for (int i = 0; i < m - n + 1; i++) {
            boolean found = true;
            for (int j = 0; j < n; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    found = false;
                }
            }
            if (found) return i;
        }
        return -1;
    }

    public static void run() {
        LC0028 solution = new LC0028();
        System.out.println(solution.strStr("hello", "ll"));
        System.out.println(solution.strStr("aaaaa", "bba"));
        System.out.println(solution.strStr("", ""));
    }
}
