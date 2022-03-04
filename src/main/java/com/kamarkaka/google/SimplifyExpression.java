package com.kamarkaka.google;

import java.util.LinkedList;
import java.util.Stack;

public class SimplifyExpression {
   Stack<LinkedList<Character>> stack = new Stack<>();

   public String simplify(String expression) {
      for (char c : expression.toCharArray()) {
         if (c == ')') {
            LinkedList<Character> subExp = stack.pop();
            if (!stack.isEmpty()) {
               LinkedList<Character> prevExpr = stack.peek();
               if (prevExpr.isEmpty() || prevExpr.peekLast() == '+') {
                  prevExpr.addAll(subExp);
               } else if (prevExpr.peekLast() == '-') {
                  for (int i = 0; i < subExp.size(); i++) {
                     if (subExp.get(i) == '+') {
                        subExp.set(i, '-');
                     } else if (subExp.get(i) == '-') {
                        subExp.set(i, '+');
                     }
                  }
                  prevExpr.addAll(subExp);
               }
            }
         } else if (c == '(') {
            stack.push(new LinkedList<>());
         } else {
            if (stack.isEmpty()) {
               stack.push(new LinkedList<>());
            }
            stack.peek().addLast(c);
         }
      }

      LinkedList<Character> expr = stack.pop();
      StringBuilder sb = new StringBuilder();
      for (char c : expr) {
         sb.append(c);
      }
      return sb.toString();
   }

   public static void run() {
      SimplifyExpression sol = new SimplifyExpression();
      System.out.println(sol.simplify("a+(b+c)"));
      System.out.println(sol.simplify("a-(b+c)"));
      System.out.println(sol.simplify("a-(b-c)"));
      System.out.println(sol.simplify("a-((b+c)-(c-d))"));
   }
}
