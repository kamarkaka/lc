package com.kamarkaka.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/***
 * 291. Word Pattern II
 * Given a pattern and a string s, return true if s matches the pattern.
 * A string s matches a pattern if there is some bijective mapping of single characters to non-empty strings such that
 * if each character in pattern is replaced by the string it maps to, then the resulting string is s. A bijective
 * mapping means that no two characters map to the same string, and no character maps to two different strings.
 * Example 1:
 *   Input: pattern = "abab", s = "redblueredblue"
 *   Output: true
 *   Explanation: One possible mapping is as follows:
 *   'a' -> "red"
 *   'b' -> "blue"
 * Example 2:
 *   Input: pattern = "aaaa", s = "asdasdasdasd"
 *   Output: true
 *   Explanation: One possible mapping is as follows:
 *   'a' -> "asd"
 * Example 3:
 *   Input: pattern = "aabb", s = "xyzabcxzyabc"
 *   Output: false
 * Constraints:
 *   1 <= pattern.length, s.length <= 20
 *   pattern and s consist of only lowercase English letters.
 */
public class LC0291 {
    Map<Character, String> cache = new HashMap<>();
    Set<String> visited = new HashSet<>();

    public boolean wordPatternMatch(String pattern, String str) {
        return helper(0, pattern, 0, str);
    }

    private boolean helper(int i, String pattern, int j, String str) {
        if (i == pattern.length() && j == str.length()) return true;
        if (i == pattern.length() || j == str.length()) return false;

        char ch = pattern.charAt(i);

        if (cache.containsKey(ch)) {
            String val = cache.get(ch);

            if (j + val.length() > str.length()) return false;

            String substr = str.substring(j, j + val.length());
            return val.equals(substr) && helper(i + 1, pattern, j + val.length(), str);
        }

        for (int k = j + 1; k <= str.length(); k++) {
            String substr = str.substring(j, k);

            if (visited.contains(substr)) continue;

            cache.put(ch, substr);
            visited.add(substr);

            if (helper(i + 1, pattern, k, str)) return true;

            cache.remove(ch);
            visited.remove(substr);
        }

        return false;
    }
}
