package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.List;

/***
 * 14. Longest Common Prefix
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 * Example 1:
 *   Input: strs = ["flower","flow","flight"]
 *   Output: "fl"
 * Example 2:
 *   Input: strs = ["dog","racecar","car"]
 *   Output: ""
 *   Explanation: There is no common prefix among the input strings.
 * Constraints:
 *   1 <= strs.length <= 200
 *   0 <= strs[i].length <= 200
 *   strs[i] consists of only lowercase English letters.
 */
public class LC0014 {
    public String longestCommonPrefix(String[] strs) {
        List<Character> charList = new ArrayList<Character>();

        if (strs.length == 0) return "";

        for (int pos = 0; pos < strs[0].length(); pos++) {
            char c = strs[0].charAt(pos);
            boolean append = true;

            for (int i = 1; i < strs.length; i++) {
                if (strs[i].length() < pos + 1 || c != strs[i].charAt(pos)) {
                    append = false;
                    break;
                }
            }

            if (append) {
                charList.add(c);
            } else {
                break;
            }
        }

        if (charList.size() == 0) {
            return "";
        } else {
            char[] chars = new char[charList.size()];
            for (int i = 0; i < charList.size(); i++) {
                chars[i] = charList.get(i);
            }

            return new String(chars);
        }
    }
}
