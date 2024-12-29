package com.kamarkaka.leetcode;

import java.util.Map;

/***
 * 266. Palindrome Permutation
 * Given a string s, return true if a permutation of the string could form a palindrome and false otherwise.
 * Example 1:
 *   Input: s = "code"
 *   Output: false
 * Example 2:
 *   Input: s = "aab"
 *   Output: true
 * Example 3:
 *   Input: s = "carerac"
 *   Output: true
 * Constraints:
 *   1 <= s.length <= 5000
 *   s consists of only lowercase English letters.
 */
public class LC0266 {
    public boolean canPermutePalindrome(String s) {
        int[] charCount = new int[26];

        for (char c : s.toCharArray()) {
            charCount[c - 'a']++;
        }

        int oddCount = 0;
        for (int count : charCount) {
            if (count % 2 == 1) {
                oddCount++;

                if (oddCount == 2) return false;
            }
        }
        return true;
    }
}
