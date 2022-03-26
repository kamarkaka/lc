package com.kamarkaka;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 * 301. Remove Invalid Parentheses
 * Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.
 * Return all the possible results. You may return the answer in any order.
 *
 * Example 1:
 *    Input: s = "()())()"
 *    Output: ["(())()","()()()"]
 *
 * Example 2:
 *    Input: s = "(a)())()"
 *    Output: ["(a())()","(a)()()"]
 *
 * Example 3:
 *    Input: s = ")("
 *    Output: [""]
 *
 * Constraints:
 *    1 <= s.length <= 25
 *    s consists of lowercase English letters and parentheses '(' and ')'.
 *    There will be at most 20 parentheses in s.
 */
public class LC0301 {
   private Set<String> validExpressions = new HashSet<String>();
   private int minimumRemoved;

   public List<String> removeInvalidParentheses(String s) {
      reset();
      helper(s, 0, 0, 0, new StringBuilder(), 0);
      return new ArrayList(validExpressions);
   }

   private void helper(String s, int index, int leftCount, int rightCount, StringBuilder expression, int removedCount) {
      if (index == s.length()) {
         if (leftCount == rightCount) {
            if (removedCount <= this.minimumRemoved) {
               if (removedCount < this.minimumRemoved) {
                  validExpressions.clear();
                  minimumRemoved = removedCount;
               }
               validExpressions.add(expression.toString());
            }
         }
      } else {
         char c = s.charAt(index);
         int length = expression.length();
         if (c != '(' && c != ')') {
            expression.append(c);
            helper(s, index + 1, leftCount, rightCount, expression, removedCount);
            expression.deleteCharAt(length);
         } else {
            helper(s, index + 1, leftCount, rightCount, expression, removedCount + 1);
            expression.append(c);
            if (c == '(') {
               helper(s, index + 1, leftCount + 1, rightCount, expression, removedCount);
            } else if (rightCount < leftCount) {
               helper(s, index + 1, leftCount, rightCount + 1, expression, removedCount);
            }
            expression.deleteCharAt(length);
         }
      }
   }

   private void reset() {
      validExpressions.clear();
      minimumRemoved = Integer.MAX_VALUE;
   }
}
