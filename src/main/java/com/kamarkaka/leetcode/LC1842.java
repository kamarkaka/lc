package com.kamarkaka.leetcode;

/***
 * 1842. Next Palindrome Using Same Digits
 * You are given a numeric string num, representing a very large palindrome.
 * Return the smallest palindrome larger than num that can be created by rearranging its digits. If no such palindrome exists, return an empty string "".
 * A palindrome is a number that reads the same backward as forward.
 *
 * Example 1:
 *    Input: num = "1221"
 *    Output: "2112"
 *    Explanation: The next palindrome larger than "1221" is "2112".
 *
 * Example 2:
 *    Input: num = "32123"
 *    Output: ""
 *    Explanation: No palindromes larger than "32123" can be made by rearranging the digits.
 *
 * Example 3:
 *    Input: num = "45544554"
 *    Output: "54455445"
 *    Explanation: The next palindrome larger than "45544554" is "54455445".
 *
 * Constraints:
 *    1 <= num.length <= 10^5
 *    num is a palindrome.
 */
public class LC1842 {
   public String nextPalindrome(String num) {
      String half = num.substring(0, num.length() / 2);
      char[] halfLetters = half.toCharArray();

      int i = half.length() - 2;
      while (i >= 0 && halfLetters[i] >= halfLetters[i + 1]) {
         i--;
      }

      if (i < 0) return "";

      int j = half.length() - 1;
      while (halfLetters[j] <= halfLetters[i]) {
         j--;
      }

      swap(halfLetters, i, j);
      reverse(halfLetters, i + 1, halfLetters.length - 1);

      String left = new String(halfLetters);
      String right = new StringBuilder(left).reverse().toString();

      if (num.length() % 2 == 1) {
         return left + num.charAt(num.length() / 2) + right;
      }
      return left + right;
   }

   private void swap(char[] letters, int i, int j) {
      char temp = letters[i];
      letters[i] = letters[j];
      letters[j] = temp;
   }

   private void reverse(char[] letters, int i, int j) {
      while (i < j) {
         swap(letters, i++, j--);
      }
   }
}
