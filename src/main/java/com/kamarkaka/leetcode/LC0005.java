package com.kamarkaka.leetcode;

/***
 * 5. Longest Palindromic Substring
 * Given a string s, return the longest palindromic substring in s.
 *
 * Example 1:
 *    Input: s = "babad"
 *    Output: "bab"
 *    Explanation: "aba" is also a valid answer.
 *
 * Example 2:
 *    Input: s = "cbbd"
 *    Output: "bb"
 *
 * Constraints:
 *    1 <= s.length <= 1000
 *    s consist of only digits and English letters.
 */
public class LC0005 {
   private String res = "";

   public String longestPalindrome(String s) {
      if (s == null || s.isEmpty()) return "";
      int N = s.length();

      for (int i = 0; i < N; i++) {
         tryExpand(s, i, i);
         tryExpand(s, i, i+1);
      }
      return res;
   }

   private void tryExpand(String s, int left, int right) {
      if (right == s.length()) {
         return;
      }
      while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
         left--;
         right++;
      }
      int len = right - left - 1;
      if (len > res.length()) {
         res = s.substring(left+1, right);
      }
   }
}
