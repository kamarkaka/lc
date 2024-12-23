package com.kamarkaka.leetcode;

import java.util.HashMap;
import java.util.Map;

/***
 * 736. Parse Lisp Expression
 * You are given a string expression representing a Lisp-like expression to return the integer value of.
 * The syntax for these expressions is given as follows.
 *    An expression is either an integer, let expression, add expression, mult expression, or an assigned variable. Expressions always evaluate to a single integer.
 *    (An integer could be positive or negative.)
 *    A let expression takes the form "(let v1 e1 v2 e2 ... vn en expr)", where let is always the string "let", then there are one or more pairs of alternating variables and expressions, meaning that the first variable v1 is assigned the value of the expression e1, the second variable v2 is assigned the value of the expression e2, and so on sequentially; and then the value of this let expression is the value of the expression expr.
 *    An add expression takes the form "(add e1 e2)" where add is always the string "add", there are always two expressions e1, e2 and the result is the addition of the evaluation of e1 and the evaluation of e2.
 *    A mult expression takes the form "(mult e1 e2)" where mult is always the string "mult", there are always two expressions e1, e2 and the result is the multiplication of the evaluation of e1 and the evaluation of e2.
 *    For this question, we will use a smaller subset of variable names. A variable starts with a lowercase letter, then zero or more lowercase letters or digits. Additionally, for your convenience, the names "add", "let", and "mult" are protected and will never be used as variable names.
 *    Finally, there is the concept of scope. When an expression of a variable name is evaluated, within the context of that evaluation, the innermost scope (in terms of parentheses) is checked first for the value of that variable, and then outer scopes are checked sequentially. It is guaranteed that every expression is legal. Please see the examples for more details on the scope.
 *
 * Example 1:
 *    Input: expression = "(let x 2 (mult x (let x 3 y 4 (add x y))))"
 *    Output: 14
 *    Explanation: In the expression (add x y), when checking for the value of the variable x,
 *       we check from the innermost scope to the outermost in the context of the variable we are trying to evaluate.
 *       Since x = 3 is found first, the value of x is 3.
 *
 * Example 2:
 *    Input: expression = "(let x 3 x 2 x)"
 *    Output: 2
 *    Explanation: Assignment in let statements is processed sequentially.
 *
 * Example 3:
 *    Input: expression = "(let x 1 y 2 x (add x y) (add x y))"
 *    Output: 5
 *    Explanation: The first (add x y) evaluates as 3, and is assigned to x.
 *    The second (add x y) evaluates as 3+2 = 5.
 *
 * Constraints:
 *    1 <= expression.length <= 2000
 *    There are no leading or trailing spaces in expression.
 *    All tokens are separated by a single space in expression.
 *    The answer and all intermediate calculations of that answer are guaranteed to fit in a 32-bit integer.
 *    The expression is guaranteed to be legal and evaluate to an integer.
 */
public class LC0736 {
   private int i = 0;
   private String expression;
   private static final String LET = "let";
   private static final String MUL = "mult";
   private static final String ADD = "add";

   public int evaluate(String expression) {
      // every composite expression is enclosed in ()
      // simple expressions: assigned variables or Integer are by themselves
      // expressions on the same level are separated by white spaces
      // a valid input is either a complex expression(could be other expression nested inside) or a single integer
      this.expression = expression;
      Map<String, Integer> vars = new HashMap<>();
      return eval(vars);
   }

   private int eval(Map<String, Integer> vars) {
      int res = 0;
      if (expression.charAt(i) == '(') {
         // skip the open bracket
         i++;
         // get the token
         String op = getStringToken();

         // skip white space
         i++;
         switch (op) {
            case LET -> {
               Map<String, Integer> curVars = new HashMap<>(vars);
               while (i < expression.length() && expression.charAt(i) != ')') {
                  if (expression.charAt(i) == '(') {
                     res = eval(curVars);
                     break;
                  }

                  if (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '-') {
                     res = getNumeric();
                     break;
                  }

                  String varName = getStringToken();
                  if (expression.charAt(i) == ')') {
                     res = curVars.get(varName);
                     break;
                  }

                  // skip white space
                  i++;
                  int val = eval(curVars);
                  curVars.put(varName, val);
                  i++;
               }
            }
            case ADD -> {
               int val1 = eval(vars);
               // skip white space
               i++;
               int val2 = eval(vars);
               res = val1 + val2;
            }
            case MUL -> {
               int num1 = eval(vars);
               // skip white space
               i++;
               int num2 = eval(vars);
               res = num1 * num2;
            }
         }
         i++;
      }
      else if (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '-') {
         res = getNumeric();
      }
      else {
         String varName = getStringToken();
         res = vars.get(varName);
      }
      return res;
   }

   private String getStringToken() {
      int start = i;
      while (Character.isLetterOrDigit(expression.charAt(i))) i++;
      return expression.substring(start, i);
   }

   private int getNumeric() {
      int sign = 1;
      if (expression.charAt(i) == '-') {
         sign = -1;
         i++;
      }
      int start = i;
      while (Character.isDigit(expression.charAt(i))) i++;
      int val = Integer.parseInt(expression.substring(start, i));
      return sign == 1 ? val : -val;
   }
}
