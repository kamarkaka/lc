package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.List;

/***
 * 784. Letter Case Permutation
 * Given a string s, you can transform every letter individually to be lowercase or uppercase to create another string.
 * Return a list of all possible strings we could create. Return the output in any order.
 * Example 1:
 *   Input: s = "a1b2"
 *   Output: ["a1b2","a1B2","A1b2","A1B2"]
 * Example 2:
 *   Input: s = "3z4"
 *   Output: ["3z4","3Z4"]
 * Constraints:
 *   1 <= s.length <= 12
 *   s consists of lowercase English letters, uppercase English letters, and digits.
 */
public class LC0784 {
    public List<String> letterCasePermutation(String S) {
        List<String> result = new ArrayList<>();

        if (S == null) return result;
        if (S.equals("")) {
            result.add("");
            return result;
        }

        char c = S.charAt(0);

        if (S.length() == 1) {
            if (isLetter(c)) {
                result.add(S.toLowerCase());
                result.add(S.toUpperCase());
            } else {
                result.add(S);
            }
        } else {
            List<String> strs = letterCasePermutation(S.substring(1));
            for (String str : strs) {
                if (isLetter(c)) {
                    result.add(Character.toString(c).toLowerCase() + str);
                    result.add(Character.toString(c).toUpperCase() + str);
                } else {
                    result.add(c + str);
                }
            }
        }

        return result;
    }

    private boolean isLetter(char c) {
        return 'a' <= c && c <= 'z' || 'A' <= c && c <= 'Z';
    }
}
