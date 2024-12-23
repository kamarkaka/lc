package com.kamarkaka.leetcode;

import java.util.Map;
import java.util.SortedMap;
import java.util.Stack;
import java.util.TreeMap;

/***
 * 726. Number of Atoms
 * Given a string formula representing a chemical formula, return the count of each atom.
 * The atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.
 * One or more digits representing that element's count may follow if the count is greater than 1. If the count is 1, no digits will follow.
 *    For example, "H2O" and "H2O2" are possible, but "H1O2" is impossible.
 * Two formulas are concatenated together to produce another formula.
 *    For example, "H2O2He3Mg4" is also a formula.
 * A formula placed in parentheses, and a count (optionally added) is also a formula.
 *    For example, "(H2O2)" and "(H2O2)3" are formulas.
 * Return the count of all elements as a string in the following form: the first name (in sorted order), followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.
 * The test cases are generated so that all the values in the output fit in a 32-bit integer.
 *
 * Example 1:
 *    Input: formula = "H2O"
 *    Output: "H2O"
 *    Explanation: The count of elements are {'H': 2, 'O': 1}.
 *
 * Example 2:
 *    Input: formula = "Mg(OH)2"
 *    Output: "H2MgO2"
 *    Explanation: The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.
 *
 * Example 3:
 *    Input: formula = "K4(ON(SO3)2)2"
 *    Output: "K4N2O14S4"
 *    Explanation: The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.
 *
 * Constraints:
 *    1 <= formula.length <= 1000
 *    formula consists of English letters, digits, '(', and ')'.
 *    formula is always valid.
 */
public class LC0726 {
   public String countOfAtoms(String formula) {
      int N = formula.length();
      Stack<Map<String, Integer>> stack = new Stack();
      stack.push(new TreeMap());

      for (int i = 0; i < N;) {
         if (formula.charAt(i) == '(') {
            stack.push(new TreeMap());
            i++;
         } else if (formula.charAt(i) == ')') {
            Map<String, Integer> top = stack.pop();
            int iStart = ++i, multiplicity = 1;
            while (i < N && Character.isDigit(formula.charAt(i))) i++;
            if (i > iStart) multiplicity = Integer.parseInt(formula.substring(iStart, i));
            for (String c: top.keySet()) {
               int v = top.get(c);
               stack.peek().put(c, stack.peek().getOrDefault(c, 0) + v * multiplicity);
            }
         } else {
            int iStart = i++;
            while (i < N && Character.isLowerCase(formula.charAt(i))) i++;
            String name = formula.substring(iStart, i);
            iStart = i;
            while (i < N && Character.isDigit(formula.charAt(i))) i++;
            int multiplicity = i > iStart ? Integer.parseInt(formula.substring(iStart, i)) : 1;
            stack.peek().put(name, stack.peek().getOrDefault(name, 0) + multiplicity);
         }
      }

      StringBuilder sb = new StringBuilder();
      for (String el: stack.peek().keySet()) {
         sb.append(el);

         int multiplicity = stack.peek().get(el);
         if (multiplicity > 1) sb.append(multiplicity);
      }

      return sb.toString();
   }

   private SortedMap<String, Integer> merge(SortedMap<String, Integer> map1, SortedMap<String, Integer> map2) {
      for (Map.Entry<String, Integer> entry : map2.entrySet()) {
         String el = entry.getKey();
         int count = entry.getValue();
         map1.put(el, map1.getOrDefault(el, 0) + count);
      }
      return map1;
   }

   private void multiply(SortedMap<String, Integer> map, int num) {
      if (num == 1) return;
      for (String el : map.keySet()) {
         map.put(el, map.get(el) * num);
      }
   }

   public static void run() {
      LC0726 sol = new LC0726();
      System.out.println(sol.countOfAtoms("(Fe10Ds47)34Nh13Bi21Cd7Tc29Ru45Ne33(Ts9He15Cs38Md30Ne23Gd5)15HAl27Eu36(Ta33Mn25Hg20Ir42Er50Cd5Pa35K)43"));
      System.out.println(sol.countOfAtoms("((N42)24(OB40Li30CHe3O48LiNN26)33(C12Li48N30H13HBe31)21(BHN30Li26BCBe47N40)15(H5)16)14"));
      System.out.println(sol.countOfAtoms("Be32"));
      System.out.println(sol.countOfAtoms("K4(ON(SO3)2)2"));
      System.out.println(sol.countOfAtoms("Mg(OH)2"));
      System.out.println(sol.countOfAtoms("H2O"));

   }
}
