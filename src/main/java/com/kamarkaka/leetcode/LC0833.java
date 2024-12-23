package com.kamarkaka.leetcode;

import com.kamarkaka.common.Pair;

import java.util.Arrays;

/***
 * 833. Find And Replace in String
 * You are given a 0-indexed string s that you must perform k replacement operations on. The replacement operations are given as three 0-indexed parallel arrays, indices, sources, and targets, all of length k.
 * To complete the ith replacement operation:
 *   Check if the substring sources[i] occurs at index indices[i] in the original string s.
 *   If it does not occur, do nothing.
 *   Otherwise if it does occur, replace that substring with targets[i].
 * For example, if s = "abcd", indices[i] = 0, sources[i] = "ab", and targets[i] = "eee", then the result of this replacement will be "eeecd".
 * All replacement operations must occur simultaneously, meaning the replacement operations should not affect the indexing of each other. The testcases will be generated such that the replacements will not overlap.
 *   For example, a testcase with s = "abc", indices = [0, 1], and sources = ["ab","bc"] will not be generated because the "ab" and "bc" replacements overlap.
 * Return the resulting string after performing all replacement operations on s.
 * A substring is a contiguous sequence of characters in a string.
 *
 * Example 1:
 *   Input: s = "abcd", indices = [0, 2], sources = ["a", "cd"], targets = ["eee", "ffff"]
 *   Output: "eeebffff"
 *   Explanation:
 *     "a" occurs at index 0 in s, so we replace it with "eee".
 *     "cd" occurs at index 2 in s, so we replace it with "ffff".
 *
 * Example 2:
 *   Input: s = "abcd", indices = [0, 2], sources = ["ab","ec"], targets = ["eee","ffff"]
 *   Output: "eeecd"
 *   Explanation:
 *     "ab" occurs at index 0 in s, so we replace it with "eee".
 *     "ec" does not occur at index 2 in s, so we do nothing.
 *
 * Constraints:
 *   1 <= s.length <= 1000
 *   k == indices.length == sources.length == targets.length
 *   1 <= k <= 100
 *   0 <= indexes[i] < s.length
 *   1 <= sources[i].length, targets[i].length <= 50
 *   s consists of only lowercase English letters.
 *   sources[i] and targets[i] consist of only lowercase English letters.
 */
public class LC0833 {
   public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {

      int[] rIndex = new int[s.length()];
      Arrays.fill(rIndex, -1);
      for (int i = 0; i < indices.length; i++) {
         rIndex[indices[i]] = i;
      }

      int offset = 0;
      for (int i = 0; i < rIndex.length; i++) {
         if (rIndex[i] == -1) continue;

         int idx = i + offset;
         String source = sources[rIndex[i]];
         String target = targets[rIndex[i]];

         if (!s.substring(idx, idx + source.length()).equals(source)) {
            continue;
         }

         s = s.substring(0, idx) + target + s.substring(idx + source.length());
         offset += target.length() - source.length();
      }

      return s;
   }

   public String findReplaceString2(String s, int[] indices, String[] sources, String[] targets) {
      Pair<String, String>[] find = new Pair[s.length()];
      for (int i = 0; i < indices.length; i++) {
         if (s.substring(indices[i], indices[i] + sources[i].length()).equals(sources[i])) {
            find[indices[i]] = new Pair<>(sources[i], targets[i]);
         }
      }

      StringBuilder sb = new StringBuilder();
      int i = 0;
      while (i < s.length()) {
         if (find[i] != null) {
            sb.append(find[i].getValue());
            i += find[i].getKey().length();
         } else {
            sb.append(s.charAt(i));
            i++;
         }
      }
      return sb.toString();
   }

   public static void run() {
      LC0833 solution = new LC0833();

      System.out.println(solution.findReplaceString2("vmokgggqzp", new int[] {3,5,1}, new String[] {"kg","ggq","mo"}, new String[] {"s","so","bfr"}));
      System.out.println(solution.findReplaceString2("abcd", new int[] {0, 2}, new String[] {"a","cd"}, new String[] {"eee","fff"}));
   }
}
