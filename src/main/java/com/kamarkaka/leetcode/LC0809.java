package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.List;

/***
 * 809. Expressive Words
 * Sometimes people repeat letters to represent extra feeling. For example:
 *   "hello" -> "heeellooo"
 *   "hi" -> "hiiii"
 * In these strings like "heeellooo", we have groups of adjacent letters that are all the same: "h", "eee", "ll", "ooo".
 * You are given a string s and an array of query strings words. A query word is stretchy if it can be made to be equal to s by any number of applications of the following extension operation: choose a group consisting of characters c, and add some number of characters c to the group so that the size of the group is three or more.
 *   For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get "helloo" since the group "oo" has a size less than three. Also, we could do another extension like "ll" -> "lllll" to get "helllllooo". If s = "helllllooo", then the query word "hello" would be stretchy because of these two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = s.
 * Return the number of query strings that are stretchy.
 *
 * Example 1:
 *   Input: s = "heeellooo", words = ["hello", "hi", "helo"]
 *   Output: 1
 *   Explanation:
 *     We can extend "e" and "o" in the word "hello" to get "heeellooo".
 *     We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.
 *
 * Example 2:
 *   Input: s = "zzzzzyyyyy", words = ["zzyy","zy","zyy"]
 *   Output: 3
 *
 * Constraints:
 *   1 <= s.length, words.length <= 100
 *   1 <= words[i].length <= 100
 *   s and words[i] consist of lowercase letters.
 */
public class LC0809 {
   List<CharCount> sList;
   public int expressiveWords(String s, String[] words) {
      sList = buildCharCount(s);

      int count = 0;
      for (String word : words) {
         if (canExpress(word)) {
            count++;
         }
      }
      return count;
   }

   private boolean canExpress(String word) {
      List<CharCount> wList = buildCharCount(word);
      if (wList.size() != sList.size()) return false;

      for (int i = 0; i < wList.size(); i++) {
         if (sList.get(i).c != wList.get(i).c) return false;
         if (sList.get(i).count < wList.get(i).count) return false;
         if (sList.get(i).count != wList.get(i).count && sList.get(i).count < 3) return false;
      }

      return true;
   }

   private List<CharCount> buildCharCount(String str) {
      List<CharCount> res = new ArrayList<>();
      char c = str.charAt(0);
      int count = 0;
      for (int i = 0; i < str.length(); i++) {
         if (str.charAt(i) == c) {
            count++;
         } else {
            res.add(new CharCount(c, count));
            c = str.charAt(i);
            count = 1;
         }
      }
      res.add(new CharCount(c, count));
      return res;
   }

   private class CharCount {
      char c;
      int count;
      public CharCount(char c, int count) {
         this.c = c;
         this.count = count;
      }
   }
}
