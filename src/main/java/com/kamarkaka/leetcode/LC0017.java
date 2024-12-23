package com.kamarkaka.leetcode;

import java.util.*;

/***
 * 17. Letter Combinations of a Phone Number
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 *
 * Example 1:
 *    Input: digits = "23"
 *    Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * Example 2:
 *    Input: digits = ""
 *    Output: []
 *
 * Example 3:
 *    Input: digits = "2"
 *    Output: ["a","b","c"]
 *
 * Constraints:
 *    0 <= digits.length <= 4
 *    digits[i] is a digit in the range ['2', '9'].
 */
public class LC0017 {
   public List<String> letterCombinations(String digits) {
      List<String> res = new ArrayList<>();

      Map<Character, List<Character>> map = new HashMap<>();
      map.put('2', Arrays.asList('a','b','c'));
      map.put('3', Arrays.asList('d','e','f'));
      map.put('4', Arrays.asList('g','h','i'));
      map.put('5', Arrays.asList('j','k','l'));
      map.put('6', Arrays.asList('m','n','o'));
      map.put('7', Arrays.asList('p','q','r','s'));
      map.put('8', Arrays.asList('t','u','v'));
      map.put('9', Arrays.asList('w','x','y','z'));

      for (int i = 0; i < digits.length(); i++) {
         char c = digits.charAt(i);

         List<Character> chars = map.get(c);
         List<String> newRes = new ArrayList<>();
         if (i == 0) {
            for (Character c1 : chars) {
               newRes.add(c1.toString());
            }
         } else {
            for (Character c1 : chars) {
               for (String str : res) {
                  newRes.add(str + c1);
               }
            }
         }
         res = newRes;
      }

      return res;
   }
}
