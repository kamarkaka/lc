package com.kamarkaka;

/***
 * 984. String Without AAA or BBB
 * Given two integers a and b, return any string s such that:
 *    s has length a + b and contains exactly a 'a' letters, and exactly b 'b' letters,
 *    The substring 'aaa' does not occur in s, and
 *    The substring 'bbb' does not occur in s.
 *
 * Example 1:
 *    Input: a = 1, b = 2
 *    Output: "abb"
 *    Explanation: "abb", "bab" and "bba" are all correct answers.
 *
 * Example 2:
 *    Input: a = 4, b = 1
 *    Output: "aabaa"
 *
 * Constraints:
 *    0 <= a, b <= 100
 *    It is guaranteed such an s exists for the given a and b.
 */
public class LC0984 {
   public String strWithout3a3b(int a, int b) {
      StringBuilder sb = new StringBuilder();

      while (a > 0 || b > 0) {
         boolean writeA = false;
         int L = sb.length();
         if (L >= 2 && sb.charAt(L-1) == sb.charAt(L-2)) {
            if (sb.charAt(L-1) == 'b') writeA = true;
         } else {
            if (a >= b) {
               writeA = true;
            }
         }

         if (writeA) {
            sb.append('a');
            a--;
         } else {
            sb.append('b');
            b--;
         }
      }

      return sb.toString();
   }
}
