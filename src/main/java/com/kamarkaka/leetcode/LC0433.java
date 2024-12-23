package com.kamarkaka.leetcode;

import java.util.*;

/***
 * 433. Minimum Genetic Mutation
 * A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.
 * Suppose we need to investigate a mutation from a gene string start to a gene string end where one mutation is defined as one single character changed in the gene string.
 *   For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
 * There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to make it a valid gene string.
 * Given the two gene strings start and end and the gene bank bank, return the minimum number of mutations needed to mutate from start to end. If there is no such a mutation, return -1.
 * Note that the starting point is assumed to be valid, so it might not be included in the bank.
 *
 * Example 1:
 *   Input: start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
 *   Output: 1
 *
 * Example 2:
 *   Input: start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
 *   Output: 2
 *
 * Example 3:
 *   Input: start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
 *   Output: 3
 *
 * Constraints:
 *   start.length == 8
 *   end.length == 8
 *   0 <= bank.length <= 10
 *   bank[i].length == 8
 *   start, end, and bank[i] consist of only the characters ['A', 'C', 'G', 'T'].
 */
public class LC0433 {
   private static char[] mutations = new char[] {'A','T','C','G'};
   public int minMutation(String start, String end, String[] bank) {
      Set<String> set = new HashSet<>(Arrays.asList(bank));
      Map<String, Integer> map = new HashMap<>();
      map.put(start, 0);

      Queue<String> queue = new LinkedList<>();
      queue.add(start);

      while (!queue.isEmpty()) {
         StringBuilder gene = new StringBuilder(queue.poll());
         int count = map.get(gene.toString());

         for (int i = 0; i < gene.length(); i++) {
            char c = gene.charAt(i);

            for (char m : mutations) {
               if (m != c) {
                  gene.setCharAt(i, m);
                  String nextGene = gene.toString();

                  if (set.contains(nextGene)) {
                     if (!map.containsKey(nextGene)) {
                        map.put(nextGene, count + 1);
                        queue.add(nextGene);
                     } else {
                        int oldCount = map.get(nextGene);
                        if (oldCount > count + 1) {
                           map.put(nextGene, count + 1);
                           queue.add(nextGene);
                        }
                     }
                  }
               }

               gene.setCharAt(i, c);
            }
         }
      }

      return map.getOrDefault(end, -1);
   }
}