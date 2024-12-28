package com.kamarkaka.leetcode;

/***
 * 844. Backspace String Compare
 * Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a
 * backspace character.
 * Note that after backspacing an empty text, the text will continue empty.
 * Example 1:
 *   Input: s = "ab#c", t = "ad#c"
 *   Output: true
 *   Explanation: Both s and t become "ac".
 * Example 2:
 *   Input: s = "ab##", t = "c#d#"
 *   Output: true
 *   Explanation: Both s and t become "".
 * Example 3:
 *   Input: s = "a#c", t = "b"
 *   Output: false
 *   Explanation: s becomes "c" while t becomes "b".
 * Constraints:
 *   1 <= s.length, t.length <= 200
 *   s and t only contain lowercase letters and '#' characters.
 * Follow up: Can you solve it in O(n) time and O(1) space?
 */
public class LC0844 {
    public boolean backspaceCompare(String S, String T) {
        return output(S).equals(output(T));
    }

    private String output(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (Character.isLetter(c)) {
                sb.append(c);
            } else if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        return sb.toString();
    }
}
