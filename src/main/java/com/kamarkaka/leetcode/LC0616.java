package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * 616. Add Bold Tag in String
 * You are given a string s and an array of strings words.
 * You should add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in words.
 *   If two such substrings overlap, you should wrap them together with only one pair of closed bold-tag.
 *   If two substrings wrapped by bold tags are consecutive, you should combine them.
 * Return s after adding the bold tags.
 * Example 1:
 *   Input: s = "abcxyz123", words = ["abc","123"]
 *   Output: "<b>abc</b>xyz<b>123</b>"
 *   Explanation: The two strings of words are substrings of s as following: "abcxyz123".
 *   We add <b> before each substring and </b> after each substring.
 * Example 2:
 *   Input: s = "aaabbb", words = ["aa","b"]
 *   Output: "<b>aaabbb</b>"
 *   Explanation:
 *   "aa" appears as a substring two times: "aaabbb" and "aaabbb".
 *   "b" appears as a substring three times: "aaabbb", "aaabbb", and "aaabbb".
 *   We add <b> before each substring and </b> after each substring: "<b>a<b>a</b>a</b><b>b</b><b>b</b><b>b</b>".
 *   Since the first two <b>'s overlap, we merge them: "<b>aaa</b><b>b</b><b>b</b><b>b</b>".
 *   Since now the four <b>'s are consecutive, we merge them: "<b>aaabbb</b>".
 * Constraints:
 *   1 <= s.length <= 1000
 *   0 <= words.length <= 100
 *   1 <= words[i].length <= 1000
 *   s and words[i] consist of English letters and digits.
 *   All the values of words are unique.
 */
public class LC0616 {
    public String addBoldTag(String s, String[] words) {
        List<int[]> intervals = new ArrayList<>();
        for (String word : words) {
            int index = 0;
            while (index < s.length()) {
                int start = s.indexOf(word, index);
                if (start == -1) break;

                int[] interval = new int[] { start, start + word.length() };
                intervals.add(interval);
                index = start + 1;
            }
        }
        if (intervals.size() == 0) {
            return s;
        }

        intervals.sort((i1, i2) -> {
            if (i1[0] == i2[0]) return i1[1] - i2[1];
            else return i1[0] - i2[0];
        });

        List<int[]> mergedIntervals = new ArrayList<>();
        int[] currentInterval = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            int[] interval = intervals.get(i);
            if (interval[0] <= currentInterval[1]) {
                currentInterval[1] = Math.max(interval[1], currentInterval[1]);
            } else {
                mergedIntervals.add(currentInterval);
                currentInterval = interval;
            }
        }
        mergedIntervals.add(currentInterval);

        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (int[] interval : mergedIntervals) {
            if (interval[0] > i) {
                sb.append(s.substring(i, interval[0]));
            }
            sb.append("<b>");
            sb.append(s.substring(interval[0], interval[1]));
            sb.append("</b>");
            i = interval[1];
        }
        sb.append(s.substring(i, s.length()));

        return sb.toString();
    }

    public static void main(String[] args) {
        LC0616 solution = new LC0616();
        System.out.println(solution.addBoldTag("abcxyz123", new String[] { "abc", "123" }));
        System.out.println(solution.addBoldTag("aaabbb", new String[] { "aa", "b" }));
        System.out.println(solution.addBoldTag("aaabbcc", new String[] {}));
    }
}
