package com.kamarkaka.bloomberg;

import java.util.Stack;

/***
 * (d(c(ab)))((((cd))))
 */
public class DeepestString {
   public String find(String str) {
      Stack<Integer> stack = new Stack<>();
      StringBuilder sb = new StringBuilder();
      int maxDepth = 0;
      String res = "";

      for (char c : str.toCharArray()) {
         if (c == '(') {
            stack.push(1);
            if (sb.length() > 0) sb = new StringBuilder();
         } else if (c == ')') {
            if (stack.size() > maxDepth) {
               maxDepth = stack.size();
               res = sb.toString();
            }
            stack.pop();
         } else {
            sb.append(c);
         }
      }

      return res;
   }

   public static void run() {
      DeepestString sol = new DeepestString();
      System.out.println(sol.find("(d(c(ab)))((((cd))))"));
   }
}
