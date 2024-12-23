package com.kamarkaka.leetcode;

/***
 * 97. Interleaving String
 * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
 * An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:
 *    s = s1 + s2 + ... + sn
 *    t = t1 + t2 + ... + tm
 *    |n - m| <= 1
 *    The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
 * Note: a + b is the concatenation of strings a and b.
 *
 * Example 1:
 *    Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 *    Output: true
 *
 * Example 2:
 *    Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 *    Output: false
 *
 * Example 3:
 *    Input: s1 = "", s2 = "", s3 = ""
 *    Output: true
 *
 * Constraints:
 *    0 <= s1.length, s2.length <= 100
 *    0 <= s3.length <= 200
 *    s1, s2, and s3 consist of lowercase English letters.
 *
 * Follow up: Could you solve it using only O(s2.length) additional memory space?
 */
public class LC0097 {
   public boolean isInterleave(String s1, String s2, String s3) {
      if (s3.length() != s1.length() + s2.length()) return false;

      boolean dp[] = new boolean[s2.length() + 1];

      for (int i = 0; i <= s1.length(); i++) {
         for (int j = 0; j <= s2.length(); j++) {
            if (i == 0 && j == 0) {
               dp[j] = true;
            } else if (i == 0) {
               dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
            } else if (j == 0) {
               dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i - 1);
            } else {
               dp[j] =
                  (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) ||
                  (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
         }
      }

      return dp[s2.length()];
   }
}
