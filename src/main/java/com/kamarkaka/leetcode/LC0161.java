package com.kamarkaka.leetcode;

/***
 * 161. One Edit Distance
 * Given two strings s and t, return true if they are both one edit distance apart, otherwise return false.
 * A string s is said to be one distance apart from a string t if you can:
 *   Insert exactly one character into s to get t.
 *   Delete exactly one character from s to get t.
 *   Replace exactly one character of s with a different character to get t.
 * Example 1:
 *   Input: s = "ab", t = "acb"
 *   Output: true
 *   Explanation: We can insert 'c' into s to get t.
 * Example 2:
 *   Input: s = "", t = ""
 *   Output: false
 *   Explanation: We cannot get t from s by only one step.
 * Constraints:
 *   0 <= s.length, t.length <= 10^4
 *   s and t consist of lowercase letters, uppercase letters, and digits.
 */
public class LC0161 {
    public boolean isOneEditDistance(String s, String t) {
        if (s == null || s.length() == 0) return t != null && t.length() == 1;
        if (t == null || t.length() == 0) return s.length() == 1;
        if (Math.abs(s.length() - t.length()) > 1) return false;
        if (s.equals(t)) return false;

        int method;
        if (s.length() > t.length()) {
            method = 0; // delete one char from s
        } else if (s.length() == t.length()) {
            method = 1; // replace one char from s
        } else {
            method = 2; // insert one char into s
        }

        int p1 = 0, p2 = 0;
        boolean found = false;

        while (p1 < s.length() && p2 < t.length()) {
            char c1 = s.charAt(p1);
            char c2 = t.charAt(p2);
            if (c1 == c2) {
                p1++;
                p2++;
            } else {
                if (found) return false;
                else found = true;

                switch (method) {
                    case 0:
                        p1++;
                        break;
                    case 1:
                        p1++;
                        p2++;
                        break;
                    case 2:
                        p2++;
                }
            }
        }

        return true;
    }
}
