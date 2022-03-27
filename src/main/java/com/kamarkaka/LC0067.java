package com.kamarkaka;

/***
 * 67. Add Binary
 * Given two binary strings a and b, return their sum as a binary string.
 *
 * Example 1:
 *    Input: a = "11", b = "1"
 *    Output: "100"
 *
 * Example 2:
 *    Input: a = "1010", b = "1011"
 *    Output: "10101"
 *
 * Constraints:
 *    1 <= a.length, b.length <= 10^4
 *    a and b consist only of '0' or '1' characters.
 *    Each string does not contain leading zeros except for the zero itself.
 */
public class LC0067 {
   public String addBinary(String a, String b) {
      int lenA = a.length(), lenB = b.length();
      if (lenA < lenB) return addBinary(b, a);

      StringBuilder sb = new StringBuilder();
      int carry = 0, j = lenB - 1;
      for(int i = lenA - 1; i >= 0; i--) {
         if (a.charAt(i) == '1') carry++;
         if (j >= 0 && b.charAt(j--) == '1') carry++;

         if (carry % 2 == 1) sb.append('1');
         else sb.append('0');

         carry /= 2;
      }

      if (carry == 1) sb.append('1');
      sb.reverse();
      return sb.toString();
   }
}
