package com.kamarkaka.leetcode;

/***
 * 186. Reverse Words in a String II
 * Given a character array s, reverse the order of the words.
 * A word is defined as a sequence of non-space characters. The words in s will be separated by a single space.
 * Your code must solve the problem in-place, i.e. without allocating extra space.
 * Example 1:
 *   Input: s = ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
 *   Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
 * Example 2:
 *   Input: s = ["a"]
 *   Output: ["a"]
 * Constraints:
 *   1 <= s.length <= 10^5
 *   s[i] is an English letter (uppercase or lowercase), digit, or space ' '.
 *   There is at least one word in s.
 *   s does not contain leading or trailing spaces.
 *   All the words in s are guaranteed to be separated by a single space.
 */
public class LC0186 {
    public void reverseWords(char[] s) {
        int start = 0, end = s.length - 1;
        while (start < end) {
            char c = s[start];
            s[start] = s[end];
            s[end] = c;
            start++;
            end--;
        }

        start = 0;
        end = 0;

        while (start < s.length) {
            while (end < s.length && s[end] != ' ') {
                end++;
            }

            int nextStart = end + 1;
            end--;
            while (start < end) {
                char c = s[start];
                s[start] = s[end];
                s[end] = c;
                start++;
                end--;
            }
            start = nextStart;
            end = nextStart;
        }
    }
}
