package com.kamarkaka.leetcode;

/***
 * 520. Detect Capital
 * We define the usage of capitals in a word to be right when one of the following cases holds:
 *   All letters in this word are capitals, like "USA".
 *   All letters in this word are not capitals, like "leetcode".
 *   Only the first letter in this word is capital, like "Google".
 * Given a string word, return true if the usage of capitals in it is right.
 * Example 1:
 *   Input: word = "USA"
 *   Output: true
 * Example 2:
 *   Input: word = "FlaG"
 *   Output: false
 * Constraints:
 *   1 <= word.length <= 100
 *   word consists of lowercase and uppercase English letters.
 */
public class LC0520 {
    public boolean detectCapitalUse(String word) {
        int count = 0;

        for (char c : word.toCharArray()) {
            if ('Z' - c >= 0) count++;
        }

        return count == 0 || count == word.length() || (count == 1 && 'Z' - word.charAt(0) >= 0);
    }
}
