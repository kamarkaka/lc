package com.kamarkaka.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/***
 * 345. Reverse Vowels of a String
 * Given a string s, reverse only all the vowels in the string and return it.
 * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.
 * Example 1:
 *   Input: s = "IceCreAm"
 *   Output: "AceCreIm"
 *   Explanation:
 *   The vowels in s are ['I', 'e', 'e', 'A']. On reversing the vowels, s becomes "AceCreIm".
 * Example 2:
 *   Input: s = "leetcode"
 *   Output: "leotcede"
 * Constraints:
 *   1 <= s.length <= 3 * 10^5
 *   s consist of printable ASCII characters.
 */

public class LC0345 {
    public String reverseVowels(String s) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        StringBuilder sb = new StringBuilder(s);

        int p1 = 0, p2 = s.length() - 1;
        while (p1 < p2) {
            while (p1 < s.length() && !vowels.contains(s.charAt(p1))) {
                p1++;
            }
            while (p2 >= 0 && !vowels.contains(s.charAt(p2))) {
                p2--;
            }

            if (p1 < p2) {
                char c1 = sb.charAt(p1);
                char c2 = sb.charAt(p2);

                sb.setCharAt(p1, c2);
                sb.setCharAt(p2, c1);
                p1++;
                p2--;
            }
        }

        return sb.toString();
    }
}
