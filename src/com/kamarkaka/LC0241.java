package com.kamarkaka;

import java.util.ArrayList;
import java.util.List;

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
