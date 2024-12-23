package com.kamarkaka.leetcode;

/***
 * 1456. Maximum Number of Vowels in a Substring of Given Length
 * Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
 * Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
 * Example 1:
 *   Input: s = "abciiidef", k = 3
 *   Output: 3
 *   Explanation: The substring "iii" contains 3 vowel letters.
 * Example 2:
 *   Input: s = "aeiou", k = 2
 *   Output: 2
 *   Explanation: Any substring of length 2 contains 2 vowels.
 * Example 3:
 *   Input: s = "leetcode", k = 3
 *   Output: 2
 *   Explanation: "lee", "eet" and "ode" contain 2 vowels.
 * Constraints:
 *   1 <= s.length <= 10^5
 *   s consists of lowercase English letters.
 *   1 <= k <= s.length
 */
public class LC1456 {
    public int maxVowels(String s, int k) {
        int pl = 0, pr = k;
        int num = 0;
        for (int i = 0; i < k; i++) {
            char c = s.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                num++;
            }
        }
        int maxNum = num;

        while (pr < s.length()) {
            char c = s.charAt(pl++);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                num--;
            }
            c = s.charAt(pr++);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                num++;
            }
            maxNum = Math.max(maxNum, num);
        }

        return maxNum;
    }
}
