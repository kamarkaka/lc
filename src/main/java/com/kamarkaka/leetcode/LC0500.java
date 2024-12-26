package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.List;

/***
 * 500. Keyboard Row
 * Given an array of strings words, return the words that can be typed using letters of the alphabet on only one row of
 * American keyboard like the image below.
 * Note that the strings are case-insensitive, both lowercased and uppercased of the same letter are treated as if they
 * are at the same row.
 * In the American keyboard:
 *   the first row consists of the characters "qwertyuiop",
 *   the second row consists of the characters "asdfghjkl", and
 *   the third row consists of the characters "zxcvbnm".
 * Example 1:
 *   Input: words = ["Hello","Alaska","Dad","Peace"]
 *   Output: ["Alaska","Dad"]
 *   Explanation: Both "a" and "A" are in the 2nd row of the American keyboard due to case insensitivity.
 * Example 2:
 *   Input: words = ["omk"]
 *   Output: []
 * Example 3:
 *   Input: words = ["adsdf","sfd"]
 *   Output: ["adsdf","sfd"]
 * Constraints:
 *   1 <= words.length <= 20
 *   1 <= words[i].length <= 100
 *   words[i] consists of English letters (both lowercase and uppercase).
 */
public class LC0500 {
    public String[] findWords(String[] words) {
        List<String> result = new ArrayList<>();

        for (String word : words) {
            boolean success = true;
            int keyRow = 0;

            for (char c : word.toLowerCase().toCharArray()) {
                if (keyRow == 0) {
                    if (c == 'q' || c == 'w' || c == 'e' || c == 'r' || c == 't' || c == 'y' || c == 'u' || c == 'i' || c == 'o' || c == 'p') {
                        keyRow = 1;
                    } else if (c == 'a' || c == 's' || c == 'd' || c == 'f' || c == 'g' || c == 'h' || c == 'j' || c == 'k' || c == 'l') {
                        keyRow = 2;
                    } else {
                        keyRow = 3;
                    }
                    continue;
                }

                int newKeyRow = 0;

                if (c == 'q' || c == 'w' || c == 'e' || c == 'r' || c == 't' || c == 'y' || c == 'u' || c == 'i' || c == 'o' || c == 'p') {
                    newKeyRow = 1;
                } else if (c == 'a' || c == 's' || c == 'd' || c == 'f' || c == 'g' || c == 'h' || c == 'j' || c == 'k' || c == 'l') {
                    newKeyRow = 2;
                } else {
                    newKeyRow = 3;
                }

                if (newKeyRow != keyRow) {
                    success = false;
                    break;
                }
            }

            if (success) {
                result.add(word);
            }
        }

        String[] strings = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            strings[i] = result.get(i);
        }
        return strings;
    }
}
