package com.kamarkaka.leetcode;

/***
 * 709. To Lower Case
 * Given a string s, return the string after replacing every uppercase letter with the same lowercase letter.
 * Example 1:
 *   Input: s = "Hello"
 *   Output: "hello"
 * Example 2:
 *   Input: s = "here"
 *   Output: "here"
 * Example 3:
 *   Input: s = "LOVELY"
 *   Output: "lovely"
 * Constraints:
 *   1 <= s.length <= 100
 *   s consists of printable ASCII characters.
 */
public class LC0709 {
    public String toLowerCase(String str) {
        if (str == null || str.length() == 0) return str;

        int[] alphabet = new int[26];
        for (int i = 0; i < 26; i++) {
            alphabet[i] = 'a' + i;
        }

        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (c - 'A' >= 0 && c - 'A' < 26) {
                sb.append((char) alphabet[c - 'A']);
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
