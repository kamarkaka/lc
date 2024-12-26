package com.kamarkaka.leetcode;

import java.util.HashMap;

/***
 * 290. Word Pattern
 * Given a pattern and a string s, find if s follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.
 * Specifically:
 *   Each letter in pattern maps to exactly one unique word in s.
 *   Each unique word in s maps to exactly one letter in pattern.
 *   No two letters map to the same word, and no two words map to the same letter.
 * Example 1:
 *   Input: pattern = "abba", s = "dog cat cat dog"
 *   Output: true
 *   Explanation: The bijection can be established as:
 *   'a' maps to "dog".
 *   'b' maps to "cat".
 * Example 2:
 *   Input: pattern = "abba", s = "dog cat cat fish"
 *   Output: false
 * Example 3:
 *   Input: pattern = "aaaa", s = "dog cat cat dog"
 *   Output: false
 * Constraints:
 *   1 <= pattern.length <= 300
 *   pattern contains only lower-case English letters.
 *   1 <= s.length <= 3000
 *   s contains only lowercase English letters and spaces ' '.
 *   s does not contain any leading or trailing spaces.
 *   All the words in s are separated by a single space.
 */
public class LC0290 {
    public boolean wordPattern(String pattern, String str) {
        String[] pieces = str.split(" ");
        HashMap<Character, String> map = new HashMap<>();
        HashMap<String, Character> map2 = new HashMap<>();

        if (pieces.length != pattern.length()) return false;

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String s = pieces[i];

            if (map.containsKey(c)) {
                if (!s.equals(map.get(c))) return false;
            } else {
                map.put(c, s);
            }

            if (map2.containsKey(s)) {
                if (c != map2.get(s)) return false;
            } else {
                map2.put(s, c);
            }
        }

        return true;
    }
}
