package com.kamarkaka.leetcode;

/***
 * 242. Valid Anagram
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * Example 1:
 *   Input: s = "anagram", t = "nagaram"
 *   Output: true
 * Example 2:
 *   Input: s = "rat", t = "car"
 *   Output: false
 * Constraints:
 *   1 <= s.length, t.length <= 5 * 10^4
 *   s and t consist of lowercase English letters.
 * Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
 */
public class LC0242 {
    public boolean isAnagram(String s, String t) {
        int[] map = new int[26];

        if (s.length() != t.length()) return false;

        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            map[t.charAt(i) - 'a']--;
            if (map[t.charAt(i) - 'a'] < 0) return false;
        }

        return true;
    }
}
