package com.kamarkaka.leetcode;

/***
 * 1055. Shortest Way to Form String
 * A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of
 * the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence
 * of "abcde" while "aec" is not).
 * Given two strings source and target, return the minimum number of subsequences of source such that their
 * concatenation equals target. If the task is impossible, return -1.
 * Example 1:
 *   Input: source = "abc", target = "abcbc"
 *   Output: 2
 *   Explanation: The target "abcbc" can be formed by "abc" and "bc", which are subsequences of source "abc".
 * Example 2:
 *   Input: source = "abc", target = "acdbc"
 *   Output: -1
 *   Explanation: The target string cannot be constructed from the subsequences of source string due to the character
 *   "d" in target string.
 * Example 3:
 *   Input: source = "xyz", target = "xzyxz"
 *   Output: 3
 *   Explanation: The target string can be constructed as follows "xz" + "y" + "xz".
 * Constraints:
 *   1 <= source.length, target.length <= 1000
 *   source and target consist of lowercase English letters.
 */
public class LC1055 {
    public int shortestWay(String source, String target) {
        int index = 0, count = 1;
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            index = indexOf(source, c, index);
            if (index == -1) {
                count++;
                index = indexOf(source, c, 0);
                if (index == -1) {
                    return -1;
                }
            }
            index++;
        }
        return count;
    }

    private int indexOf(String s, char c, int start) {
        for (; start < s.length(); start++) {
            if (s.charAt(start) == c) {
                return start;
            }
        }
        return -1;
    }
}
