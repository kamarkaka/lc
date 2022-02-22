package com.kamarkaka;

import java.util.Stack;

/***
 * 772. Basic Calculator III
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string contains only non-negative integers, '+', '-', '*', '/' operators, and open '(' and closing parentheses ')'. The integer division should truncate toward zero.
 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 * Example 1:
 *    Input: s = "1+1"
 *    Output: 2
 *
 * Example 2:
 *    Input: s = "6-4/2"
 *    Output: 4
 *
 * Example 3:
 *    Input: s = "2*(5+5*2)/3+(6/2+8)"
 *    Output: 21
 *
 * Constraints:
 *    1 <= s <= 10^4
 *    s consists of digits, '+', '-', '*', '/', '(', and ')'.
 *    s is a valid expression.
 */
public class LC0772 {
   public int calculate(String s) {
      if (s == null || s.length() == 0) return 0;

      Stack<Integer> valStack = new Stack<>();
      Stack<Character> opStack = new Stack<>();
      boolean isNegative = false;
      char pre = ' ';

      for (int i = 0 ; i < s.length() ; i++) {
         char c = s.charAt(i);
         if (c == ' ') continue;

         if (c == '+' || c == '-' || c == '/' || c == '*') {
            if (c == '-' && (valStack.isEmpty() || (!Character.isDigit(pre) && pre !=')'))) {
               isNegative = true;
               continue;
            }

            while (!opStack.isEmpty() && predend(opStack.peek(), c)) {
               valStack.push(operation(opStack.pop(), valStack.pop(), valStack.pop()));
            }
            opStack.push(c);
         } else if (c == '(') {
            opStack.push(c);
         } else if (c == ')') {
            while (opStack.peek() != '(') {
               valStack.push(operation(opStack.pop(), valStack.pop(), valStack.pop()));
            }
            opStack.pop();
         } else {
            int num = s.charAt(i) - '0';
            while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
               num = num * 10 + (s.charAt(i + 1) - '0');
               i++;
            }
            if (isNegative) {
               num *= -1;
               isNegative = false;
            }
            valStack.push(num);
         }
         pre = c;
      }

      while (!opStack.isEmpty()) {
         valStack.push(operation(opStack.pop(), valStack.pop(), valStack.pop()));
      }

      return valStack.pop();
   }

   private int operation(char op, int b, int a) {
      return switch (op) {
         case '+' -> a + b;
         case '-' -> a - b;
         case '*' -> a * b;
         case '/' -> a / b;
         default -> a;
      };
   }

   private boolean predend(char c1, char c2) {
      if (c1 == '(') {
         return false;
      }

      if ((c1 == '+' || c1 == '-') && (c2 == '*'|| c2 == '/')) {
         return false;
      }
      return true;
   }
}
