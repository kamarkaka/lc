package com.kamarkaka.leetcode;

import java.util.*;

/***
 * 301. Remove Invalid Parentheses
 * Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the
 * input string valid.
 * Return all the possible results. You may return the answer in any order.
 * Example 1:
 *   Input: s = "()())()"
 *   Output: ["(())()","()()()"]
 * Example 2:
 *   Input: s = "(a)())()"
 *   Output: ["(a())()","(a)()()"]
 * Example 3:
 *   Input: s = ")("
 *   Output: [""]
 * Constraints:
 *   1 <= s.length <= 25
 *   s consists of lowercase English letters and parentheses '(' and ')'.
 *   There will be at most 20 parentheses in s.
 */
public class LC0301 {
   private final Set<String> validExpressions = new HashSet<>();
   private int minimumRemoved;

   public List<String> removeInvalidParentheses(String s) {
      int open = 0, close = 0;
      for (char c : s.toCharArray()) {
         if (c == '(') {
            open++;
         } else if (c == ')') {
            if (open > 0) {
               open--;
            } else {
               close++;
            }
         }
      }
      this.minimumRemoved = open + close;

      helper(s, 0, 0, 0, new StringBuilder(), 0);
      return new ArrayList<>(validExpressions);
   }

   private void helper(String s, int index, int openCount, int closeCount, StringBuilder sb, int removedCount) {
      if (index == s.length()) {
         if (openCount == closeCount && removedCount == this.minimumRemoved) {
            this.validExpressions.add(sb.toString());
         }
         return;
      }

      char c = s.charAt(index);
      if (c != '(' && c != ')') {
         sb.append(c);
         helper(s, index + 1, openCount, closeCount, sb, removedCount);
         sb.deleteCharAt(sb.length() - 1);
         return;
      }

      // remove whatever encountered
      helper(s, index + 1, openCount, closeCount, sb, removedCount + 1);

      if (c == '(') {
         sb.append(c);
         helper(s, index + 1, openCount + 1, closeCount, sb, removedCount);
         sb.deleteCharAt(sb.length() - 1);
         return;
      }

      if (c == ')' && closeCount < openCount) {
         sb.append(c);
         helper(s, index + 1, openCount, closeCount + 1, sb, removedCount);
         sb.deleteCharAt(sb.length() - 1);
         return;
      }
   }

   public List<String> removeInvalidParentheses2(String s) {
      List<String> results = new ArrayList<>();
      if (s == null || s.isEmpty()) return results;
      Set<String> visited = new HashSet<>();
      Queue<String> queue = new LinkedList<>();
      queue.add(s);
      visited.add(s);
      boolean found = false;

      while (!queue.isEmpty()) {
         for (int i = queue.size(); i > 0; --i) {
            String cur = queue.poll();

            if (isValid(cur)) {
               results.add(cur);
               found = true;
            }

            if(found) continue;

            for (int j = 0; j < cur.length(); j++) {
               if (cur.charAt(j) != '(' && cur.charAt(j) != ')') continue;
               String next = cur.substring(0, j) + cur.substring(j + 1);
               if (visited.contains(next)) continue;
               queue.add(next);
               visited.add(next);
            }
         }
         if (found) break;
      }
      return results;
   }

   private boolean isValid(String s) {
      int count = 0;
      for (char c : s.toCharArray()) {
         if (c == '(') count++;
         else if (c == ')') {
            count--;
            if (count < 0) return false;
         }
      }
      return count == 0;
   }

   public List<String> removeInvalidParentheses3(String s) {
      List<String> ans = new ArrayList<>();
      remove(s, ans, 0, 0, new char[]{'(', ')'});
      return ans;
   }

   private void remove(String s, List<String> ans, int last_i, int last_j,  char[] par) {
      for (int stack = 0, i = last_i; i < s.length(); ++i) {
         if (s.charAt(i) == par[0]) stack++;
         if (s.charAt(i) == par[1]) stack--;
         if (stack >= 0) continue;
         for (int j = last_j; j <= i; ++j)
            if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))
               remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
         return;
      }
      String reversed = new StringBuilder(s).reverse().toString();
      if (par[0] == '(') // finished left to right
         remove(reversed, ans, 0, 0, new char[]{')', '('});
      else // finished right to left
         ans.add(reversed);
   }

}
