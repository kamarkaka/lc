package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.List;

/***
 * 282. Expression Add Operators
 * Given a string num that contains only digits and an integer target, return all possibilities to insert the binary
 * operators '+', '-', and/or '*' between the digits of num so that the resultant expression evaluates to the target
 * value.
 * Note that operands in the returned expressions should not contain leading zeros.
 * Example 1:
 *   Input: num = "123", target = 6
 *   Output: ["1*2*3","1+2+3"]
 *   Explanation: Both "1*2*3" and "1+2+3" evaluate to 6.
 * Example 2:
 *   Input: num = "232", target = 8
 *   Output: ["2*3+2","2+3*2"]
 *   Explanation: Both "2*3+2" and "2+3*2" evaluate to 8.
 * Example 3:
 *   Input: num = "3456237490", target = 9191
 *   Output: []
 *   Explanation: There are no expressions that can be created from "3456237490" to evaluate to 9191.
 * Constraints:
 *   1 <= num.length <= 10
 *   num consists of only digits.
 *   -2^31 <= target <= 2^31 - 1
 */
public class LC0282 {
   public List<String> result;
   public String digits;
   public long target;

   public List<String> addOperators(String num, int target) {
      this.result = new ArrayList<>();

      if (num == null || num.isEmpty()) return this.result;

      this.digits = num;
      this.target = target;
      helper(0, 0, 0, 0, new ArrayList<>());
      return result;
   }

   private void helper(int index, long previousOperand, long currentOperand, long value, ArrayList<String> ops) {
      String nums = this.digits;

      // Done processing all the digits in num
      if (index == nums.length()) {
         // If the final value == target expected AND  no operand is left unprocessed
         if (value == target && currentOperand == 0) {
            StringBuilder sb = new StringBuilder();
            ops.subList(1, ops.size()).forEach(sb::append);
            this.result.add(sb.toString());
         }
         return;
      }

      // Extending the current operand by one digit
      currentOperand = currentOperand * 10 + Character.getNumericValue(nums.charAt(index));

      if (currentOperand > 0) {
         helper(index + 1, previousOperand, currentOperand, value, ops);
      }

      ops.add("+");
      ops.add(Long.toString(currentOperand));
      helper(index + 1, currentOperand, 0, value + currentOperand, ops);
      ops.removeLast();
      ops.removeLast();

      if (!ops.isEmpty()) {
         ops.add("-");
         ops.add(Long.toString(currentOperand));
         helper(index + 1, -currentOperand, 0, value - currentOperand, ops);
         ops.removeLast();
         ops.removeLast();

         ops.add("*");
         ops.add(Long.toString(currentOperand));
         helper(index + 1, previousOperand * currentOperand, 0, value - previousOperand + (previousOperand * currentOperand), ops);
         ops.removeLast();
         ops.removeLast();

         if (currentOperand != 0) {
            ops.add("/");
            ops.add(Long.toString(currentOperand));
            helper(index + 1, previousOperand / currentOperand, 0, value - previousOperand + (previousOperand / currentOperand), ops);
            ops.removeLast();
            ops.removeLast();
         }
      }
   }

   public static void main(String[] args) {
      LC0282 solution = new LC0282();
      System.out.println(solution.addOperators("123456789", 100));
   }
}
