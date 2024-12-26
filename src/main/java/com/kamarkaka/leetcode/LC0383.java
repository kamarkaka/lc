package com.kamarkaka.leetcode;

/***
 * 383. Ransom Note
 * Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from
 * magazine and false otherwise.
 * Each letter in magazine can only be used once in ransomNote.
 * Example 1:
 *   Input: ransomNote = "a", magazine = "b"
 *   Output: false
 * Example 2:
 *   Input: ransomNote = "aa", magazine = "ab"
 *   Output: false
 * Example 3:
 *   Input: ransomNote = "aa", magazine = "aab"
 *   Output: true
 * Constraints:
 *   1 <= ransomNote.length, magazine.length <= 10^5
 *   ransomNote and magazine consist of lowercase English letters.
 */
public class LC0383 {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] index = new int[128];

        if (ransomNote == null || magazine == null) return false;

        for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            int pos = magazine.indexOf(c, index[c]);

            if (pos < 0) return false;

            index[c] = pos + 1;
        }

        return true;
    }
}
