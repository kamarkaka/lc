package main.java.com.kamarkaka;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/***
 * 900. RLE Iterator
 * We can use run-length encoding (i.e., RLE) to encode a sequence of integers. In a run-length encoded array of even length encoding (0-indexed), for all even i, encoding[i] tells us the number of times that the non-negative integer value encoding[i + 1] is repeated in the sequence.
 *   For example, the sequence arr = [8,8,8,5,5] can be encoded to be encoding = [3,8,2,5]. encoding = [3,8,0,9,2,5] and encoding = [2,8,1,8,2,5] are also valid RLE of arr.
 * Given a run-length encoded array, design an iterator that iterates through it.
 * Implement the RLEIterator class:
 *   RLEIterator(int[] encoded) Initializes the object with the encoded array encoded.
 *   int next(int n) Exhausts the next n elements and returns the last element exhausted in this way. If there is no element left to exhaust, return -1 instead.
 *
 * Example 1:
 *   Input
 *     ["RLEIterator", "next", "next", "next", "next"]
 *     [[[3, 8, 0, 9, 2, 5]], [2], [1], [1], [2]]
 *   Output
 *     [null, 8, 8, 5, -1]
 *   Explanation
 *     RLEIterator rLEIterator = new RLEIterator([3, 8, 0, 9, 2, 5]); // This maps to the sequence [8,8,8,5,5].
 *     rLEIterator.next(2); // exhausts 2 terms of the sequence, returning 8. The remaining sequence is now [8, 5, 5].
 *     rLEIterator.next(1); // exhausts 1 term of the sequence, returning 8. The remaining sequence is now [5, 5].
 *     rLEIterator.next(1); // exhausts 1 term of the sequence, returning 5. The remaining sequence is now [5].
 *     rLEIterator.next(2); // exhausts 2 terms, returning -1. This is because the first term exhausted was 5,
 *     but the second term did not exist. Since the last term exhausted does not exist, we return -1.
 *
 * Constraints:
 *   2 <= encoding.length <= 1000
 *   encoding.length is even.
 *   0 <= encoding[i] <= 10^9
 *   1 <= n <= 10^9
 *   At most 1000 calls will be made to next.
 */
public class LC0900 {
   static class RLEIterator {
      private List<int[]> encoding;
      private int idx;
      private int count;

      public RLEIterator(int[] encoding) {
         this.encoding = new ArrayList<>();
         for (int i = 0; i < encoding.length - 1; i += 2) {
            int times = encoding[i];
            int value = encoding[i+1];

            if (times == 0) continue;
            this.encoding.add(new int[] {value, times});
         }
         this.idx = 0;
         this.count = 0;
      }

      public int next(int n) {
         while (idx < encoding.size() && count <= encoding.get(idx)[1]) {
            if (count + n <= encoding.get(idx)[1]) {
               count += n;
               return encoding.get(idx)[0];
            } else {
               n -= (encoding.get(idx)[1] - count);
               idx++;
               count = 0;
            }
         }
         return -1;
      }
   }

   static class RLEIterator2 {
      TreeMap<Long, Integer> map;
      long currIndex = 0;
      int len;
      long max;

      public RLEIterator2(int[] encoding) {
         len = encoding.length;
         map = new TreeMap();
         currIndex = 0;
         long count = 0;
         for (int i = 0; i < len-1; i += 2) {
            if (encoding[i] <= 0)
               continue;
            map.put(count, encoding[i+1]);
            count += encoding[i];
            max = count;
         }
      }

      public int next(int n) {
         if (n <= 0)
            return -1;

         currIndex += n-1;
         if (currIndex >= max)
            return -1;

         return map.get(map.floorKey(currIndex++));
      }
   }

   public static void run() {
      LC0900.RLEIterator iter = new RLEIterator(new int[] {3,8,2,5});
      System.out.println(iter.next(2));
      System.out.println(iter.next(1));
      System.out.println(iter.next(1));
      System.out.println(iter.next(2));
   }
}
