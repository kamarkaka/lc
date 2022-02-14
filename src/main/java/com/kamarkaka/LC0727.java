package main.java.com.kamarkaka;

/***
 * 727. Minimum Window Subsequence
 * Given strings s1 and s2, return the minimum contiguous substring part of s1, so that s2 is a subsequence of the part.
 * If there is no such window in s1 that covers all characters in s2, return the empty string "". If there are multiple such minimum-length windows, return the one with the left-most starting index.
 *
 * Example 1:
 *   Input: s1 = "abcdebdde", s2 = "bde"
 *   Output: "bcde"
 *   Explanation:
 *     "bcde" is the answer because it occurs before "bdde" which has the same length.
 *     "deb" is not a smaller window because the elements of s2 in the window must occur in order.
 *
 * Example 2:
 *   Input: s1 = "jmeqksfrsdcmsiwvaovztaqenprpvnbstl", s2 = "u"
 *   Output: ""
 *
 * Constraints:
 *   1 <= s1.length <= 2 * 10^4
 *   1 <= s2.length <= 100
 *   s1 and s2 consist of lowercase English letters.
 */
public class LC0727 {
   public String minWindow(String s1, String s2) {
      if (s1 == null || s1.length() == 0) return "";
      if (s2 == null || s2.length() == 0) return "";
      if (s1.length() < s2.length()) return "";
      if (s1.length() == s2.length()) return s1.equals(s2) ? s1 : "";

      int minStart = -1, minLen = Integer.MAX_VALUE;
      int i = -1;

      while (i < s1.length()) {
         for (int j = 0; j < s2.length(); j++) {
            i = s1.indexOf(s2.charAt(j), i + 1);
            if (i == -1) return minStart == -1 ? "" : s1.substring(minStart, minStart + minLen);
         }

         int end = ++i;
         for (int j = s2.length() - 1; j > -1; j--) {
            i = s1.lastIndexOf(s2.charAt(j), i - 1);
         }

         if (end - i < minLen) {
            minLen = end - i;
            minStart = i;
         }
         i++;
      }
      return minStart == -1 ? "" : s1.substring(minStart, minStart + minLen);
   }
}
