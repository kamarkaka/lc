package com.kamarkaka.leetcode;

/***
 * 686. Repeated String Match
 * Given two strings a and b, return the minimum number of times you should repeat string a so that string b is a
 * substring of it. If it is impossible for b to be a substring of a after repeating it, return -1.
 * Notice: string "abc" repeated 0 times is "", repeated 1 time is "abc" and repeated 2 times is "abcabc".
 * Example 1:
 *   Input: a = "abcd", b = "cdabcdab"
 *   Output: 3
 *   Explanation: We return 3 because by repeating "a" three times "abcdabcdabcd", b is a substring of it.
 * Example 2:
 *   Input: a = "a", b = "aa"
 *   Output: 2
 * Constraints:
 *   1 <= a.length, b.length <= 10^4
 *   a and b consist of lowercase English letters.
 */
public class LC0686 {
    public int repeatedStringMatch(String A, String B) {
        if (A == null || A.length() == 0 || B == null || B.length() == 0) return -1;

        int count = 1;
        int pC = 0;
        int pA = pC;
        int pB = 0;

        while (pB < B.length() && pC < A.length()) {
            if (A.charAt(pA) == B.charAt(pB)) {
                if (pA == A.length() - 1) {
                    pA = 0;
                    if (pB < B.length() - 1) count++;
                } else {
                    pA++;
                }
                pB++;
            } else {
                pC++;
                pA = pC;
                pB = 0;
                count = 1;
            }
        }

        return pC == A.length() ? -1 : count;
    }
}
