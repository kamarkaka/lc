package com.kamarkaka;

import java.util.PriorityQueue;

/***
 * 767. Reorganize String
 * Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
 * Return any possible rearrangement of s or return "" if not possible.
 *
 * Example 1:
 *    Input: s = "aab"
 *    Output: "aba"
 *
 * Example 2:
 *    Input: s = "aaab"
 *    Output: ""
 *
 * Constraints:
 *    1 <= s.length <= 500
 *    s consists of lowercase English letters.
 */
public class LC0767 {
   public String reorganizeString(String s) {
      int[] map = new int[26];
      for(int i = 0; i < s.length(); i++){
         map[s.charAt(i)-'a']++;
      }

      PriorityQueue<Pair> pq = new PriorityQueue<>((a, b)->(b.f - a.f));
      for(int i  = 0; i < 26; i++){
         if (map[i] > 0) pq.add(new Pair((char)('a'+i), map[i]));
      }

      StringBuilder res = new StringBuilder();
      Pair block = pq.poll();
      res.append(block.ch);
      block.f--;

      while (pq.size() > 0) {
         Pair temp = pq.poll();
         res.append(temp.ch);
         temp.f--;

         if (block.f > 0) {
            pq.add(block);
         }
         block = temp;
      }

      if (block.f > 0) return "";
      return res.toString();
   }

   public class Pair {
      char ch;
      int f;

      Pair(char ch, int f) {
         this.ch = ch;
         this.f = f;
      }
   }

   public static void run() {
      LC0767 sol = new LC0767();
      System.out.println(sol.reorganizeString("aass"));
   }
}
