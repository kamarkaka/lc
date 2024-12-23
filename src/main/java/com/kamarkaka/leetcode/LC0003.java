package com.kamarkaka.leetcode;

import java.util.HashSet;

/***
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *   Input: s = "abcabcbb"
 *   Output: 3
 *   Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 *   Input: s = "bbbbb"
 *   Output: 1
 *   Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 *    Input: s = "pwwkew"
 *    Output: 3
 *    Explanation: The answer is "wke", with the length of 3.
 *    Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 * Example 4:
 *   Input: s = ""
 *   Output: 0
 *
 * Constraints:
 *   0 <= s.length <= 5 * 10^4
 *   s consists of English letters, digits, symbols and spaces.
 */
public class LC0003 {
   public int lengthOfLongestSubstring(String s) {
      if (s == null || s.length() == 0) return 0;

      int startIndex = 0;
      int endIndex = 0;
      int len = 0;
      HashSet<Character> hashSet = new HashSet<>();

      while (endIndex < s.length()) {
         char c = s.charAt(endIndex);
         if (hashSet.contains(c)) {
            int currLen = endIndex - startIndex;
            len = Math.max(currLen, len);

            while (startIndex < endIndex) {
               char c1 = s.charAt(startIndex);
               startIndex++;

               if (c1 == c) {
                  break;
               } else {
                  hashSet.remove(c1);
               }
            }
         } else {
            hashSet.add(c);
         }

         endIndex++;
      }

      return Math.max(len, endIndex - startIndex);
   }

   public static void run() {
      LC0003 solution = new LC0003();
      System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));
      System.out.println(solution.lengthOfLongestSubstring("bbbbb"));
      System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
      System.out.println(solution.lengthOfLongestSubstring(""));
      System.out.println(solution.lengthOfLongestSubstring("abcde"));
      System.out.println(solution.lengthOfLongestSubstring("abbbcdea"));
   }
}
