package com.kamarkaka;

import java.util.Stack;

/***
 * 227. Basic Calculator II
 * Given a string s which represents an expression, evaluate this expression and return its value.
 * The integer division should truncate toward zero.
 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 * Example 1:
 *    Input: s = "3+2*2"
 *    Output: 7
 *
 * Example 2:
 *    Input: s = " 3/2 "
 *    Output: 1
 *
 * Example 3:
 *    Input: s = " 3+5 / 2 "
 *    Output: 5
 *
 * Constraints:
 *    1 <= s.length <= 3 * 10^5
 *    s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
 *    s represents a valid expression.
 *    All the integers in the expression are non-negative integers in the range [0, 2^31 - 1].
 *    The answer is guaranteed to fit in a 32-bit integer.
 */
public class LC0227 {
   public int calculate(String s) {
      Stack<Part> stack = new Stack<>();
      int num = 0, left = 0;
      Character op = null;

      for (int i = 0; i < s.length(); i++) {
         char c = s.charAt(i);
         if (c == ' ') continue;
         if (Character.isDigit(c)) {
            num = num * 10 + (c - '0');
         } else if (c == '+' || c == '-') {
            num = calc(left, num, op);
            while (!stack.isEmpty()) {
               Part part = stack.pop();
               left = part.operand;
               op = part.operator;
               num = calc(left, num, op);
            }
            left = num;
            op = c;
            num = 0;
         } else if (c == '*' || c == '/') {
            if (op == null || op == '*' || op == '/') {
               left = calc(left, num, op);
               op = c;
               num = 0;
            } else {
               stack.push(new Part(left, op));
               left = num;
               op = c;
               num = 0;
            }
         }
      }

      num = calc(left, num, op);
      while (!stack.isEmpty()) {
         Part part = stack.pop();
         left = part.operand;
         op = part.operator;
         num = calc(left, num, op);
      }
      return num;
   }

   private int calc(int left, int right, Character op) {
      if (op == null) return right;

      return switch (op) {
         case '+' -> left + right;
         case '-' -> left - right;
         case '*' -> left * right;
         case '/' -> left / right;
         default -> right;
      };
   }

   public static void run() {
      LC0227 sol = new LC0227();
      System.out.println(sol.calculate("3+2*2"));
   }
}

class Part {
   int operand;
   char operator;

   public Part(int operand, char operator) {
      this.operand = operand;
      this.operator = operator;
   }
}