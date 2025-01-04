package com.kamarkaka.leetcode;

/***
 * 227. Basic Calculator II
 * Given a string s which represents an expression, evaluate this expression and return its value.
 * The integer division should truncate toward zero.
 * You may assume that the given expression is always valid. All intermediate results will be in the range of
 * [-2^31, 2^31 - 1].
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as
 * eval().
 * Example 1:
 *   Input: s = "3+2*2"
 *   Output: 7
 * Example 2:
 *   Input: s = " 3/2 "
 *   Output: 1
 * Example 3:
 *   Input: s = " 3+5 / 2 "
 *   Output: 5
 * Constraints:
 *   1 <= s.length <= 3 * 10^5
 *   s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
 *   s represents a valid expression.
 *   All the integers in the expression are non-negative integers in the range [0, 2^31 - 1].
 *   The answer is guaranteed to fit in a 32-bit integer.
 */
public class LC0227 {
   public int calculate(String s) {
      if (s == null || s.isEmpty()) return 0;

      int result = 0;
      int currNum = 0, lastNum = 0;
      char op = '+';

      for (int i = 0; i < s.length(); i++) {
         char c = s.charAt(i);

         if (Character.isDigit(c)) {
            currNum = currNum * 10 + (c - '0');
         }

         if (!Character.isDigit(c) && !Character.isWhitespace(c) || i == s.length() - 1) {
            if (op == '+' || op == '-') {
               result += lastNum;
               lastNum = (op == '+') ? currNum : -currNum;
            } else if (op == '*') {
               lastNum *= currNum;
            } else if (op == '/') {
               lastNum /= currNum;
            }
            op = c;
            currNum = 0;
         }
      }
      result += lastNum;
      return result;
   }

   public static void run() {
      LC0227 sol = new LC0227();
      System.out.println(sol.calculate("3+2*2"));
   }
}
