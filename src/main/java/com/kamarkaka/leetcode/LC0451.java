package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 451. Sort Characters By Frequency
 * Given a string s, sort it in decreasing order based on the frequency of the characters. The frequency of a character is the number of times it appears in the string.
 * Return the sorted string. If there are multiple answers, return any of them.
 *
 * Example 1:
 *   Input: s = "tree"
 *   Output: "eert"
 *   Explanation: 'e' appears twice while 'r' and 't' both appear once.
 *   So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 *
 * Example 2:
 *   Input: s = "cccaaa"
 *   Output: "aaaccc"
 *   Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.
 *   Note that "cacaca" is incorrect, as the same characters must be together.
 *
 * Example 3:
 *   Input: s = "Aabb"
 *   Output: "bbAa"
 *   Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
 *   Note that 'A' and 'a' are treated as two different characters.
 *
 * Constraints:
 *   1 <= s.length <= 5 * 105
 *   s consists of uppercase and lowercase English letters and digits.
 */
public class LC0451 {
   public String frequencySort(String s) {
      Map<Character, Integer> map = new HashMap<>();
      for (char c : s.toCharArray()) {
         map.put(c, map.getOrDefault(c, 0) + 1);
      }

      List<Node> list = new ArrayList<>();
      for (Map.Entry<Character, Integer> entry : map.entrySet()) {
         list.add(new Node(entry.getKey(), entry.getValue()));
      }

      list.sort((n1, n2) -> {
         if (n1.freq != n2.freq) return n2.freq - n1.freq;
         else return n1.c - n2.c;
      });

      StringBuilder sb = new StringBuilder();
      for (Node node : list) {
         for (int i = 0; i < node.freq; i++) {
            sb.append(node.c);
         }
      }
      return sb.toString();
   }

   class Node {
      char c;
      int freq;

      public Node(char c, int freq) {
         this.c = c;
         this.freq = freq;
      }
   }
}
