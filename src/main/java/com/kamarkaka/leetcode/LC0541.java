package com.kamarkaka.leetcode;

/***
 * 541. Reverse String II
 * Given a string s and an integer k, reverse the first k characters for every 2k characters counting from the start of
 * the string.
 * If there are fewer than k characters left, reverse all of them. If there are less than 2k but greater than or equal
 * to k characters, then reverse the first k characters and leave the other as original.
 * Example 1:
 *   Input: s = "abcdefg", k = 2
 *   Output: "bacdfeg"
 * Example 2:
 *   Input: s = "abcd", k = 2
 *   Output: "bacd"
 * Constraints:
 *   1 <= s.length <= 10^4
 *   s consists of only lowercase English letters.
 *   1 <= k <= 10^4
 */
public class LC0541 {
    public String reverseStr(String s, int k) {
        if (s == null || s.length() == 0) return s;

        char[] ca = s.toCharArray();
        int p1 = 0;
        int p2 = Math.min(k - 1, ca.length - 1);

        while (p1 <= ca.length - 1) {
            int p1c = p1;
            while (p1c < p2) {
                char c1 = ca[p1c];
                char c2 = ca[p2];
                ca[p1c] = c2;
                ca[p2] = c1;
                p1c++;
                p2--;
            }

            p1 += k * 2;
            p2 = Math.min(p1 + k - 1, ca.length - 1);
        }

        return new String(ca);
    }
}
