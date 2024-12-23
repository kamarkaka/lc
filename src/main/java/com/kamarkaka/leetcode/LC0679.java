package com.kamarkaka.leetcode;

/***
 * 679. 24 Game
 * You are given an integer array cards of length 4. You have four cards, each containing a number in the range [1, 9]. You should arrange the numbers on these cards in a mathematical expression using the operators ['+', '-', '*', '/'] and the parentheses '(' and ')' to get the value 24.
 * You are restricted with the following rules:
 *    The division operator '/' represents real division, not integer division.
 *       For example, 4 / (1 - 2 / 3) = 4 / (1 / 3) = 12.
 *    Every operation done is between two numbers. In particular, we cannot use '-' as a unary operator.
 *       For example, if cards = [1, 1, 1, 1], the expression "-1 - 1 - 1 - 1" is not allowed.
 *    You cannot concatenate numbers together
 *       For example, if cards = [1, 2, 1, 2], the expression "12 + 12" is not valid.
 * Return true if you can get such expression that evaluates to 24, and false otherwise.
 *
 * Example 1:
 *    Input: cards = [4,1,8,7]
 *    Output: true
 *    Explanation: (8-4) * (7-1) = 24
 *
 * Example 2:
 *    Input: cards = [1,2,1,2]
 *    Output: false
 *
 * Constraints:
 *    cards.length == 4
 *    1 <= cards[i] <= 9
 */
public class LC0679 {
   private static final double EPS = 1e-6;

   public boolean judgePoint24(int[] cards) {
      double[] A = new double[cards.length];
      for (int i = 0; i < cards.length; i++) {
         A[i] = cards[i];
      }
      return helper(A, A.length);
   }

   private boolean helper(double[] A, int n) {
      if (n == 1) return Math.abs(A[0] - 24) < EPS;

      for (int i = 0; i < n; i++) {
         for (int j = i + 1; j < n; j++) {
            double a = A[i], b = A[j];
            A[j] = A[n-1];
            A[i] = a + b;
            if (helper(A, n - 1)) return true;

            A[i] = a - b;
            if (helper(A, n - 1)) return true;

            A[i] = b - a;
            if (helper(A, n - 1)) return true;

            A[i] = a * b;
            if (helper(A, n - 1)) return true;

            if (Math.abs(b) > EPS) {
               A[i] = a / b;
               if (helper(A, n - 1)) return true;
            }

            if (Math.abs(a) > EPS) {
               A[i] = b / a;
               if (helper(A, n - 1)) return true;
            }

            A[i] = a;
            A[j] = b;
         }
      }
      return false;
   }
}
