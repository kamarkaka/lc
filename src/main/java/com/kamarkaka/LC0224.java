package com.kamarkaka;

import java.util.Stack;

/***
 * 224. Basic Calculator
 * Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 * Example 1:
 *    Input: s = "1 + 1"
 *    Output: 2
 *
 * Example 2:
 *    Input: s = " 2-1 + 2 "
 *    Output: 3
 *
 * Example 3:
 *    Input: s = "(1+(4+5+2)-3)+(6+8)"
 *    Output: 23
 *
 * Constraints:
 *    1 <= s.length <= 3 * 105
 *    s consists of digits, '+', '-', '(', ')', and ' '.
 *    s represents a valid expression.
 *    '+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
 *    '-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
 *    There will be no two consecutive operators in the input.
 *    Every number and running calculation will fit in a signed 32-bit integer.
 */
public class LC0224 {
   public int calculate2(String s) {
      Stack<Integer> stack = new Stack<>();
      int operand = 0;
      int result = 0; // For the on-going result
      int sign = 1;  // 1 means positive, -1 means negative

      for (int i = 0; i < s.length(); i++) {
         char ch = s.charAt(i);
         if (Character.isDigit(ch)) {
            operand = 10 * operand + (ch - '0');
         } else if (ch == '+') {
            result += sign * operand;
            sign = 1;
            operand = 0;
         } else if (ch == '-') {
            result += sign * operand;
            sign = -1;
            operand = 0;
         } else if (ch == '(') {
            stack.push(result);
            stack.push(sign);
            sign = 1;
            result = 0;
         } else if (ch == ')') {
            // Evaluate the expression to the left
            // with result, sign and operand
            result += sign * operand;

            result *= stack.pop();
            result += stack.pop();
            operand = 0;
         }
      }
      return result + sign * operand;
   }

   public int calculate(String s) {
      Stack<Part> stack = new Stack<>();
      int operand = 0, leftOperand = 0;
      char operator = 'X';

      for (char c : s.toCharArray()) {
         if (c == ' ') continue;

         if ('0' <= c && c <= '9') {
            operand = operand * 10 + (c - '0');
         } else if (c == '+' || c == '-') {
            if (operator == 'X') {
               leftOperand = operand;
               operand = 0;
               operator = c;
            } else {
               leftOperand = calc(leftOperand, operand, operator);
               operand = 0;
               operator = c;
            }

         } else if (c == '(') {
            stack.push(new Part(leftOperand, operator));
            leftOperand = 0;
            operand = 0;
            operator = 'X';
         } else if (c == ')') {
            operand = calc(leftOperand, operand, operator);
            Part part = stack.pop();
            leftOperand = part.leftOperand;
            operator = part.operator;

            if (operator != 'X') {
               operand = calc(leftOperand, operand, operator);
               operator = 'X';
            }
         }
      }

      if (operator == 'X') return operand;

      return calc(leftOperand, operand, operator);
   }

   private int calc(int left, int right, char op) {
      return switch (op) {
         case '+' -> left + right;
         case '-' -> left - right;
         case 'X' -> right;
         default -> 0;
      };
   }

   private class Part {
      int leftOperand;
      char operator;

      public Part(int leftOperand, char operator) {
         this.leftOperand = leftOperand;
         this.operator = operator;
      }
   }

   public static void run() {
      LC0224 solution = new LC0224();
      System.out.println(solution.calculate("(1)"));
      System.out.println(solution.calculate("(1+(4+5+2)-3)+(6+8)"));
   }
}
