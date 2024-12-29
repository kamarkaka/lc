package com.kamarkaka.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/***
 * 2060. Check if an Original String Exists Given Two Encoded Strings
 * An original string, consisting of lowercase English letters, can be encoded by the following steps:
 *   Arbitrarily split it into a sequence of some number of non-empty substrings.
 *   Arbitrarily choose some elements (possibly none) of the sequence, and replace each with its length (as a numeric
 *   string).
 *   Concatenate the sequence as the encoded string.
 * For example, one way to encode an original string "abcdefghijklmnop" might be:
 *   Split it as a sequence: ["ab", "cdefghijklmn", "o", "p"].
 *   Choose the second and third elements to be replaced by their lengths, respectively. The sequence becomes
 *   ["ab", "12", "1", "p"].
 *   Concatenate the elements of the sequence to get the encoded string: "ab121p".
 * Given two encoded strings s1 and s2, consisting of lowercase English letters and digits 1-9 (inclusive), return true
 * if there exists an original string that could be encoded as both s1 and s2. Otherwise, return false.
 * Note: The test cases are generated such that the number of consecutive digits in s1 and s2 does not exceed 3.
 * Example 1:
 *   Input: s1 = "internationalization", s2 = "i18n"
 *   Output: true
 *   Explanation: It is possible that "internationalization" was the original string.
 *   - "internationalization"
 *     -> Split:       ["internationalization"]
 *     -> Do not replace any element
 *     -> Concatenate:  "internationalization", which is s1.
 *   - "internationalization"
 *     -> Split:       ["i", "nternationalizatio", "n"]
 *     -> Replace:     ["i", "18",                 "n"]
 *     -> Concatenate:  "i18n", which is s2
 * Example 2:
 *   Input: s1 = "l123e", s2 = "44"
 *   Output: true
 *   Explanation: It is possible that "leetcode" was the original string.
 *   - "leetcode"
 *     -> Split:      ["l", "e", "et", "cod", "e"]
 *     -> Replace:    ["l", "1", "2",  "3",   "e"]
 *     -> Concatenate: "l123e", which is s1.
 *   - "leetcode"
 *     -> Split:      ["leet", "code"]
 *     -> Replace:    ["4",    "4"]
 *     -> Concatenate: "44", which is s2.
 * Example 3:
 *   Input: s1 = "a5b", s2 = "c5b"
 *   Output: false
 *   Explanation: It is impossible.
 *   - The original string encoded as s1 must start with the letter 'a'.
 *   - The original string encoded as s2 must start with the letter 'c'.
 * Constraints:
 *   1 <= s1.length, s2.length <= 40
 *   s1 and s2 consist of digits 1-9 (inclusive), and lowercase English letters only.
 *   The number of consecutive digits in s1 and s2 does not exceed 3.
 */
public class LC2060 {
    private static final int THREE_DIGITS = 1000;

    public boolean possiblyEquals(String s1, String s2) {
        var seen = new boolean[s1.length() + 1][s2.length() + 1][2 * THREE_DIGITS];

        // if a (partial) search has been recorded as seen, there was no match,
        // as the first successful match is returned immediately
        return match(0, 0, 0, s1.toCharArray(), s2.toCharArray(), seen);
    }

    private boolean match(int i, int j, int diff, char[] text1, char[] text2, boolean[][][] seen) {
        int m = text1.length;
        int n = text2.length;
        if (i == m && j == n) return diff == 0;
        if (seen[i][j][diff + THREE_DIGITS]) return false;

        if (i < m && text1[i] >= '0' && text1[i] <= '9') {
            int num = 0;
            int idx = i;

            do {
                num = num * 10 + text1[idx] - '0';
                ++idx;
                if (match(idx, j, diff - num, text1, text2, seen)) return true;
            } while (idx < m && text1[idx] >= '0' && text1[idx] <= '9');
        } else if (j < n && text2[j] >= '0' && text2[j] <= '9') {
            int num = 0;
            int idx = j;
            do {
                num = num * 10 + text2[idx] - '0';
                ++idx;
                if (match(i, idx, diff + num, text1, text2, seen)) return true;
            } while (idx < n && text2[idx] >= '0' && text2[idx] <= '9');
        } else if (diff > 0) {
            if (i < m) return match(i + 1, j, diff - 1, text1, text2, seen);
        } else if (diff < 0) {
            if (j < n) return match(i, j + 1, diff + 1, text1, text2, seen);
        } else {
            if (i < m && j < n && text1[i] == text2[j]) return match(i + 1, j + 1, 0, text1, text2, seen);
        }

        seen[i][j][diff + THREE_DIGITS] = true;
        return false;
    }
}
