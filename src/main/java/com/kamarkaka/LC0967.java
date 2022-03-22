package com.kamarkaka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/***
 * 967. Numbers With Same Consecutive Differences
 * Return all non-negative integers of length n such that the absolute difference between every two consecutive digits is k.
 * Note that every number in the answer must not have leading zeros. For example, 01 has one leading zero and is invalid.
 * You may return the answer in any order.
 *
 * Example 1:
 *    Input: n = 3, k = 7
 *    Output: [181,292,707,818,929]
 *    Explanation: Note that 070 is not a valid number, because it has leading zeroes.
 *
 * Example 2:
 *    Input: n = 2, k = 1
 *    Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 *
 * Constraints:
 *    2 <= n <= 9
 *    0 <= k <= 9
 */
public class LC0967 {
   public int[] numsSameConsecDiff(int n, int k) {
      if (n == 1) return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

      Queue<Integer> queue = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
      for (int level = 1; level < n; level++) {
         Queue<Integer> nextQueue = new LinkedList<>();

         for (Integer num : queue) {
            int tailDigit = num % 10;

            ArrayList<Integer> nextDigits = new ArrayList<>();
            nextDigits.add(tailDigit + k);
            if (k != 0) nextDigits.add(tailDigit - k);

            for (int nextDigit : nextDigits) {
               if (0 <= nextDigit && nextDigit < 10) {
                  int newNum = num * 10 + nextDigit;
                  nextQueue.add(newNum);
               }
            }
         }

         queue = nextQueue;
      }

      return queue.stream().mapToInt(i->i).toArray();
   }
}
