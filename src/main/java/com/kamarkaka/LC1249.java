package main.java.com.kamarkaka;

import java.util.Stack;

/***
 * 1249. Minimum Remove to Make Valid Parentheses
 * Given a string s of '(' , ')' and lowercase English characters.
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
 * Formally, a parentheses string is valid if and only if:
 *   It is the empty string, contains only lowercase characters, or
 *   It can be written as AB (A concatenated with B), where A and B are valid strings, or
 *   It can be written as (A), where A is a valid string.
 *
 * Example 1:
 *   Input: s = "lee(t(c)o)de)"
 *   Output: "lee(t(c)o)de"
 *   Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 *
 * Example 2:
 *   Input: s = "a)b(c)d"
 *   Output: "ab(c)d"
 *
 * Example 3:
 *   Input: s = "))(("
 *   Output: ""
 *   Explanation: An empty string is also valid.
 *
 * Constraints:
 *   1 <= s.length <= 10^5
 *   s[i] is either'(' , ')', or lowercase English letter.
 */
public class LC1249 {
   public String minRemoveToMakeValid(String s) {
      StringBuilder sb = new StringBuilder(s);
      if (sb.length() == 0) return "";

      Stack<Integer> stack = new Stack<>();
      int i = 0;
      while (i < sb.length()) {
         char c = sb.charAt(i);
         if (c == '(') {
            stack.push(i);
            i++;
         }
         else if (c == ')') {
            if (!stack.empty()) {
               stack.pop();
               i++;
            }
            else sb.deleteCharAt(i);
         } else {
            i++;
         }
      }

      while (!stack.isEmpty()) {
         i = stack.pop();
         sb.deleteCharAt(i);
      }

      return sb.toString();
   }

   public static void run() {
      LC1249 solution = new LC1249();
      System.out.println(solution.minRemoveToMakeValid("))(("));
      System.out.println(solution.minRemoveToMakeValid("a)b(c)d"));
      System.out.println(solution.minRemoveToMakeValid("lee(t(c)o)de)"));
   }
}
