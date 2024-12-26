package com.kamarkaka.leetcode;

/***
 * 557. Reverse Words in a String III
 * Given a string s, reverse the order of characters in each word within a sentence while still preserving whitespace
 * and initial word order.
 * Example 1:
 *   Input: s = "Let's take LeetCode contest"
 *   Output: "s'teL ekat edoCteeL tsetnoc"
 * Example 2:
 *   Input: s = "Mr Ding"
 *   Output: "rM gniD"
 * Constraints:
 *   1 <= s.length <= 5 * 10^4
 *   s contains printable ASCII characters.
 *   s does not contain any leading or trailing spaces.
 *   There is at least one word in s.
 *   All the words in s are separated by a single space.
 */
public class LC0557 {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) return s;

        char[] ca = s.toCharArray();

        int p1 = 0;
        int p2 = 0;

        while(true) {
            p1 = findWordStart(ca, p1);
            if (p1 == -1) break;

            p2 = findWordEnd(ca, p1 + 1);
            reverse(ca, p1, p2);
            p1 = p2 + 1;
        }

        return new String(ca);
    }

    private int findWordStart(char[] ca, int start) {
        while (start < ca.length) {
            if (ca[start] != ' ') return start;
            else start++;
        }
        return -1;
    }

    private int findWordEnd(char[] ca, int start) {
        while (start < ca.length) {
            if (ca[start] == ' ') return start - 1;
            else start++;
        }
        return ca.length - 1;
    }

    private void reverse(char[] ca, int start, int end) {
        while (start < end) {
            char c1 = ca[start];
            ca[start] = ca[end];
            ca[end] = c1;
            start++;
            end--;
        }
    }
}
