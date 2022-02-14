package com.kamarkaka;

/***
 * 859. Buddy Strings
 * Given two strings s and goal, return true if you can swap two letters in s so the result is equal to goal, otherwise, return false.
 * Swapping letters is defined as taking two indices i and j (0-indexed) such that i != j and swapping the characters at s[i] and s[j].
 * For example, swapping at indices 0 and 2 in "abcd" results in "cbad".
 *
 * Example 1:
 *    Input: s = "ab", goal = "ba"
 *    Output: true
 *    Explanation: You can swap s[0] = 'a' and s[1] = 'b' to get "ba", which is equal to goal.
 *
 * Example 2:
 *    Input: s = "ab", goal = "ab"
 *    Output: false
 *    Explanation: The only letters you can swap are s[0] = 'a' and s[1] = 'b', which results in "ba" != goal.
 *
 * Example 3:
 *    Input: s = "aa", goal = "aa"
 *    Output: true
 *    Explanation: You can swap s[0] = 'a' and s[1] = 'a' to get "aa", which is equal to goal.
 *
 * Constraints:
 *    1 <= s.length, goal.length <= 2 * 10^4
 *    s and goal consist of lowercase letters.
 */
public class LC0859 {
   public boolean buddyStrings(String s, String goal) {
      if (s == null || s.length() < 2) return false;
      if (goal == null || goal.length() < 2) return false;
      if (s.length() != goal.length()) return false;

      if (s.equals(goal)) {
         int[] count = new int[26];
         for (int i = 0; i < s.length(); ++i) {
            count[s.charAt(i) - 'a']++;
         }

         for (int c: count) {
            if (c > 1) return true;
         }
         return false;
      }

      int[] diff = new int[2];
      int diffCount = 0;

      for (int idx = 0; idx < s.length(); idx++) {
         char c1 = s.charAt(idx), c2 = goal.charAt(idx);
         if (c1 == c2) continue;;

         if (diffCount == 2) return false;
         diff[diffCount++] = idx;
      }

      return s.charAt(diff[0]) == goal.charAt(diff[1]) && s.charAt(diff[1]) == goal.charAt(diff[0]);
   }
}
