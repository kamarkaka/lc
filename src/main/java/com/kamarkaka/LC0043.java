package com.kamarkaka;

/***
 * 43. Multiply Strings
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
 * Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.
 *
 * Example 1:
 *   Input: num1 = "2", num2 = "3"
 *   Output: "6"
 *
 * Example 2:
 *   Input: num1 = "123", num2 = "456"
 *   Output: "56088"
 *
 * Constraints:
 *   1 <= num1.length, num2.length <= 200
 *   num1 and num2 consist of digits only.
 *   Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 */
public class LC0043 {
   public String multiply(String num1, String num2) {
      if (num1.equals("0") || num2.equals("0")) return "0";

      int totalLength = num1.length() + num2.length();
      char[] result = new char[totalLength];
      int offset = 0;

      for (int i = 0; i < totalLength; i++) {
         result[i] = '0';
      }

      for (int i = num2.length() - 1; i >= 0; i--) {
         char c = num2.charAt(i);
         result = add(result, multiply(num1.toCharArray(), c, totalLength, offset));
         offset++;
      }

      StringBuilder sb = new StringBuilder();
      boolean leadingZero = true;
      for (char c : result) {
         if (c == '0' && leadingZero) continue;
         else leadingZero = false;
         sb.append(c);
      }
      return sb.toString().isEmpty() ? "0" : sb.toString();
   }

   char[] multiply(char[] chars, char c, int totalLength, int offset) {
      char[] result = new char[totalLength];
      for (int i = 0; i < totalLength; i++) {
         result[i] = '0';
      }

      if (c == '0') return result;

      int x = 0;

      for (int i = chars.length - 1; i >= 0; i--) {
         int product = (chars[i] - 48) * (c - 48) + x;
         if (product >= 10) {
            x = product / 10;
            product = product % 10;
         } else {
            x = 0;
         }

         product = product + 48;
         result[i + totalLength - chars.length - offset] = (char) product;
      }

      if (x > 0) result[totalLength - chars.length - offset - 1] = (char) (x + 48);
      return result;
   }

   private char[] add(char[] chars1, char[] chars2) {
      char[] result = new char[chars1.length];
      int x = 0;

      for (int i = chars1.length - 1; i >= 0; i--) {
         int sum = (chars1[i] - 48) + (chars2[i] - 48) + x;
         if (sum >= 10) {
            x = 1;
            sum -= 10;
         } else {
            x = 0;
         }

         sum = sum + 48;
         result[i] = (char) sum;
      }

      return result;
   }
}
