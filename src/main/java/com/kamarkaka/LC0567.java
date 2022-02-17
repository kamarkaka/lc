package com.kamarkaka;

/***
 * 567. Permutation in String
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 * In other words, return true if one of s1's permutations is the substring of s2.
 *
 * Example 1:
 *    Input: s1 = "ab", s2 = "eidbaooo"
 *    Output: true
 *    Explanation: s2 contains one permutation of s1 ("ba").
 *
 * Example 2:
 *    Input: s1 = "ab", s2 = "eidboaoo"
 *    Output: false
 *
 * Constraints:
 *    1 <= s1.length, s2.length <= 10^4
 *    s1 and s2 consist of lowercase English letters.
 */
public class LC0567 {
   public boolean checkInclusion(String s1, String s2) {
      if (s1.length() > s2.length()) return false;

      int[] s1map = new int[26];
      int[] s2map = new int[26];
      for (int i = 0; i < s1.length(); i++) {
         s1map[s1.charAt(i) - 'a']++;
         s2map[s2.charAt(i) - 'a']++;
      }

      int count = 0;
      for (int i = 0; i < 26; i++) {
         if (s1map[i] == s2map[i]) count++;
      }

      for (int i = 0; i < s2.length() - s1.length(); i++) {
         int left = s2.charAt(i) - 'a';
         int right = s2.charAt(i + s1.length()) - 'a';

         if (count == 26) return true;

         s2map[right]++;
         if (s2map[right] == s1map[right]) {
            count++;
         } else if (s2map[right] == s1map[right] + 1) {
            count--;
         }

         s2map[left]--;
         if (s2map[left] == s1map[left]) {
            count++;
         } else if (s2map[left] == s1map[left] - 1) {
            count--;
         }
      }

      return count == 26;
   }
}
