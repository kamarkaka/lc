package com.kamarkaka;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/***
 * 150. Evaluate Reverse Polish Notation
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, and /. Each operand may be an integer or another expression.
 * Note that division between two integers should truncate toward zero.
 * It is guaranteed that the given RPN expression is always valid. That means the expression would always evaluate to a result, and there will not be any division by zero operation.
 *
 * Example 1:
 *    Input: tokens = ["2","1","+","3","*"]
 *    Output: 9
 *    Explanation: ((2 + 1) * 3) = 9
 *
 * Example 2:
 *    Input: tokens = ["4","13","5","/","+"]
 *    Output: 6
 *    Explanation: (4 + (13 / 5)) = 6
 *
 * Example 3:
 *    Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 *    Output: 22
 *    Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 *       = ((10 * (6 / (12 * -11))) + 17) + 5
 *       = ((10 * (6 / -132)) + 17) + 5
 *       = ((10 * 0) + 17) + 5
 *       = (0 + 17) + 5
 *       = 17 + 5
 *       = 22
 *
 * Constraints:
 *    1 <= tokens.length <= 10^4
 *    tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
 */
public class LC0150 {
   public int evalRPN(String[] tokens) {
      Stack<Integer> stack = new Stack<>();
      Set<String> ops = new HashSet<>(Arrays.asList("+", "-", "*", "/"));

      for (String token : tokens) {
         if (ops.contains(token)) {
            int num2 = stack.pop();
            int num1 = stack.pop();
            stack.push(calc(num1, num2, token));
         } else {
            stack.push(Integer.parseInt(token));
         }
      }
      return stack.pop();
   }

   private int calc(int num1, int num2, String op) {
      return switch (op) {
         case "+" -> num1 + num2;
         case "-" -> num1 - num2;
         case "*" -> num1 * num2;
         case "/" -> num1 / num2;
         default -> num1;
      };
   }
}
