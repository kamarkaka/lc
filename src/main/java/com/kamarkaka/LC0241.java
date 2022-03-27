package com.kamarkaka;

import java.util.ArrayList;
import java.util.List;

/***
 * 241. Different Ways to Add Parentheses
 * Given a string expression of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. You may return the answer in any order.
 *
 * Example 1:
 *    Input: expression = "2-1-1"
 *    Output: [0,2]
 *    Explanation:
 *       ((2-1)-1) = 0
 *       (2-(1-1)) = 2
 *
 * Example 2:
 *    Input: expression = "2*3-4*5"
 *    Output: [-34,-14,-10,-10,10]
 *    Explanation:
 *       (2*(3-(4*5))) = -34
 *       ((2*3)-(4*5)) = -14
 *       ((2*(3-4))*5) = -10
 *       (2*((3-4)*5)) = -10
 *       (((2*3)-4)*5) = 10
 *
 * Constraints:
 *    1 <= expression.length <= 20
 *    expression consists of digits and the operator '+', '-', and '*'.
 *    All the integer values in the input expression are in the range [0, 99].
 */
public class LC0241 {
   public List<Integer> diffWaysToCompute(String expression) {
      List<Integer> res = new ArrayList<>();
      if (expression.length() == 1) {
         res.add(Integer.valueOf(expression));
         return res;
      }

      for (int i = 0; i < expression.length(); i++) {
         char c = expression.charAt(i);
         if (c == '-' || c == '*' || c == '+') {
            String a = expression.substring(0, i);
            String b = expression.substring(i + 1);
            List<Integer> a1 = diffWaysToCompute(a);
            List<Integer> b1 = diffWaysToCompute(b);

            for (int x : a1) {
               for (int y : b1) {
                  if (c == '+') {
                     res.add(x + y);
                  } else if (c=='-') {
                     res.add(x - y);
                  } else {
                     res.add(x * y);
                  }
               }
            }
         }
      }

      if (res.size() == 0) {
         res.add(Integer.valueOf(expression));
      }
      return res;
   }

   public static void run() {
      LC0241 solution = new LC0241();
      System.out.println(solution.diffWaysToCompute("2-1-1"));
   }
}
