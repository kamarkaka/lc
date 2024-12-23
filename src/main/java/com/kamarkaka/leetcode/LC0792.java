package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.List;

/***
 * 792. Number of Matching Subsequences
 * Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 * For example, "ace" is a subsequence of "abcde".
 *
 * Example 1:
 *   Input: s = "abcde", words = ["a","bb","acd","ace"]
 *   Output: 3
 *   Explanation: There are three strings in words that are a subsequence of s: "a", "acd", "ace".
 *
 * Example 2:
 *   Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
 *   Output: 2
 *
 * Constraints:
 * 1 <= s.length <= 5 * 10^4
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 50
 * s and words[i] consist of only lowercase English letters.
 */
public class LC0792 {
   public int numMatchingSubseq(String s, String[] words) {
      int count = 0;
      List<Node>[] heads = new ArrayList[26];
      for (int i = 0; i < 26; i++) {
         heads[i] = new ArrayList<>();
      }

      for (String word : words) {
         heads[word.charAt(0) - 'a'].add(new Node(word, 0));
      }

      for (char c : s.toCharArray()) {
         List<Node> oldBucket = heads[c - 'a'];
         heads[c - 'a'] = new ArrayList<>();

         for (Node node : oldBucket) {
            node.index++;
            if (node.index == node.word.length()) {
               count++;
            } else {
               heads[node.word.charAt(node.index) - 'a'].add(node);
            }
         }
         oldBucket.clear();
      }

      return count;
   }

   class Node {
      String word;
      int index;
      public Node(String word, int index) {
         this.word = word;
         this.index = index;
      }
   }

   public static void run() {
      LC0792 sol = new LC0792();
      System.out.println(sol.numMatchingSubseq("dsahjpjauf", new String[] {"ahjpjau","ja","ahbwzgqnuk","tnmlanowax"}));
   }
}
