package com.kamarkaka.leetcode;

/***
 * 796. Rotate String
 * Given two strings s and goal, return true if and only if s can become goal after some number of shifts on s.
 * A shift on s consists of moving the leftmost character of s to the rightmost position.
 *   For example, if s = "abcde", then it will be "bcdea" after one shift.
 * Example 1:
 *   Input: s = "abcde", goal = "cdeab"
 *   Output: true
 * Example 2:
 *   Input: s = "abcde", goal = "abced"
 *   Output: false
 * Constraints:
 *   1 <= s.length, goal.length <= 100
 *   s and goal consist of lowercase English letters.
 */
public class LC0796 {
    public boolean rotateString(String A, String B) {
        if (A == null && B == null) return true;
        if (A == null || B == null) return false;
        if (A.length() == 0) return A.equals(B);

        for (int i = 0; i < A.length(); i++) {
            if (A.equals(B)) return true;

            A = A.substring(1) + A.charAt(0);
        }

        return false;
    }
}
