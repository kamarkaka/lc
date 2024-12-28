package com.kamarkaka.leetcode;

/***
 * 696. Count Binary Substrings
 * Given a binary string s, return the number of non-empty substrings that have the same number of 0's and 1's, and all
 * the 0's and all the 1's in these substrings are grouped consecutively.
 * Substrings that occur multiple times are counted the number of times they occur.
 * Example 1:
 *   Input: s = "00110011"
 *   Output: 6
 *   Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's:
 *   "0011", "01", "1100", "10", "0011", and "01".
 *   Notice that some of these substrings repeat and are counted the number of times they occur.
 *   Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.
 * Example 2:
 *   Input: s = "10101"
 *   Output: 4
 *   Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.
 * Constraints:
 *   1 <= s.length <= 10^5
 *   s[i] is either '0' or '1'.
 */
public class LC0696 {
    public int countBinarySubstrings(String s) {
        if (s == null || s.length() <= 1) return 0;

        int count = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) continue;

            for (int j = 0;;j++) {
                if (i - j < 0 || i + 1 + j > s.length() - 1) break;
                if (s.charAt(i - j) == s.charAt(i) && s.charAt(i + 1) == s.charAt(i + 1 + j)) {
                    count++;
                } else {
                    break;
                }
            }
        }

        return count;
    }
}
