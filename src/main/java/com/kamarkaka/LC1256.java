package com.kamarkaka;

/***
 * 1256. Encode Number
 * Given a non-negative integer num, Return its encoding string.
 * The encoding is done by converting the integer to a string using a secret function that you should deduce from the following table:
 *    n f(n)
 *    0 ""
 *    1 "0"
 *    2 "1"
 *    3 "00"
 *    4 "01"
 *    5 "10"
 *    6 "11"
 *    7 "000"
 *
 * Example 1:
 *    Input: num = 23
 *    Output: "1000"
 *
 * Example 2:
 *    Input: num = 107
 *    Output: "101100"
 *
 * Constraints:
 *    0 <= num <= 10^9
 */
public class LC1256 {
   public String encode(int num) {
      return Integer.toString(num + 1, 2).substring(1);
   }

   public String encode2(int num) {
      StringBuilder sb = new StringBuilder();
      num++;
      while (num > 0) {
         int lastDigit = num % 2;
         sb.append(lastDigit);
         num = num >> 1;
      }

      return sb.reverse().substring(1);
   }
}
