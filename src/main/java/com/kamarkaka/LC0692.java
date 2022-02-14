package com.kamarkaka;

import java.util.*;

/***
 * 692. Top K Frequent Words
 * Given an array of strings words and an integer k, return the k most frequent strings.
 * Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their lexicographical order.
 *
 * Example 1:
 *   Input: words = ["i","love","leetcode","i","love","coding"], k = 2
 *   Output: ["i","love"]
 *   Explanation: "i" and "love" are the two most frequent words.
 *   Note that "i" comes before "love" due to a lower alphabetical order.
 *
 * Example 2:
 *   Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
 *   Output: ["the","is","sunny","day"]
 *   Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.
 *
 * Constraints:
 *   1 <= words.length <= 500
 *   1 <= words[i] <= 10
 *   words[i] consists of lowercase English letters.
 *   k is in the range [1, The number of unique words[i]]
 *
 * Follow-up: Could you solve it in O(n log(k)) time and O(n) extra space?
 */
public class LC0692 {
   public List<String> topKFrequent(String[] words, int k) {
      Map<String, Integer> map = new HashMap<>();

      for (String word : words) {
         int count = map.getOrDefault(word, 0) + 1;
         map.put(word, count);
      }

      PriorityQueue<String> pq = new PriorityQueue<>((s1, s2) -> {
         int c1 = map.get(s1), c2 = map.get(s2);
         if (c1 != c2) return Integer.compare(c1, c2);
         else return s2.compareTo(s1);
      });

      for (String word : map.keySet()) {
         pq.add(word);

         if (pq.size() > k) {
            pq.poll();
         }
      }

      List<String> result = new ArrayList<>();
      for (int i = 0; i < k; i++) {
         result.add(pq.poll());
      }
      Collections.reverse(result);
      return result;
   }
}
