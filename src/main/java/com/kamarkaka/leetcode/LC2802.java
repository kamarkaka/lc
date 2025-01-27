package com.kamarkaka.leetcode;

/***
 * 2802. Find The K-th Lucky Number
 * We know that 4 and 7 are lucky digits. Also, a number is called lucky if it contains only lucky digits.
 * You are given an integer k, return the kth lucky number represented as a string.
 * Example 1:
 *   Input: k = 4
 *   Output: "47"
 *   Explanation: The first lucky number is 4, the second one is 7, the third one is 44 and the fourth one is 47.
 * Example 2:
 *   Input: k = 10
 *   Output: "477"
 *   Explanation: Here are lucky numbers sorted in increasing order:
 *   4, 7, 44, 47, 74, 77, 444, 447, 474, 477. So the 10th lucky number is 477.
 * Example 3:
 *   Input: k = 1000
 *   Output: "777747447"
 *   Explanation: It can be shown that the 1000th lucky number is 777747447.
 * Constraints:
 *   1 <= k <= 10^9
 */
public class LC2802 {
   public static String kthLuckyNumber(int k) {
      int c = 0; // the number of digits in the kth lucky number
      int numCount = 0; // the number of luck numbers with c or fewer digits
      while (numCount < k) {
         c++;
         numCount += (int)Math.pow(2, c);
      }

      // calculate the number of lucky numbers with c digits before the kth luck number
      int x = k - 1 - (numCount - (int)Math.pow(2, c));

      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < c; i++) {
         char digit;
         if (x % 2 == 1) digit = '7';
         else digit = '4';
         sb.insert(0, digit);
         x /= 2;
      }
      return sb.toString();
   }

   public static void main(String[] args) {
      System.out.println(LC2802.kthLuckyNumber(3));
   }
}
