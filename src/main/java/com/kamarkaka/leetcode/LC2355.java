package com.kamarkaka.leetcode;

import java.util.Arrays;
import java.util.Stack;

/***
 * 2355. Maximum Number of Books You Can Take
 * You are given a 0-indexed integer array books of length n where books[i] denotes the number of books on the ith shelf
 * of a bookshelf.
 * You are going to take books from a contiguous section of the bookshelf spanning from l to r where 0 <= l <= r < n.
 * For each index i in the range l <= i < r, you must take strictly fewer books from shelf i than shelf i + 1.
 * Return the maximum number of books you can take from the bookshelf.
 * Example 1:
 *   Input: books = [8,5,2,7,9]
 *   Output: 19
 *   Explanation:
 *   - Take 1 book from shelf 1.
 *   - Take 2 books from shelf 2.
 *   - Take 7 books from shelf 3.
 *   - Take 9 books from shelf 4.
 *   You have taken 19 books, so return 19.
 *   It can be proven that 19 is the maximum number of books you can take.
 * Example 2:
 *   Input: books = [7,0,3,4,5]
 *   Output: 12
 *   Explanation:
 *   - Take 3 books from shelf 2.
 *   - Take 4 books from shelf 3.
 *   - Take 5 books from shelf 4.
 *   You have taken 12 books so return 12.
 *   It can be proven that 12 is the maximum number of books you can take.
 * Example 3:
 *   Input: books = [8,2,3,7,3,4,0,1,4,3]
 *   Output: 13
 * Explanation:
 *   - Take 1 book from shelf 0.
 *   - Take 2 books from shelf 1.
 *   - Take 3 books from shelf 2.
 *   - Take 7 books from shelf 3.
 *   You have taken 13 books so return 13.
 *   It can be proven that 13 is the maximum number of books you can take.
 * Constraints:
 *   1 <= books.length <= 10^5
 *   0 <= books[i] <= 10^5
 */
public class LC2355 {
   public long maximumBooks(int[] books) {
      int n = books.length;

      Stack<Integer> s = new Stack<>();
      long[] dp = new long[n];

      for (int i = 0; i < n; i++) {
         // While we cannot push i, we pop from the stack
         while (!s.isEmpty() && books[s.peek()] - s.peek() >= books[i] - i) {
            s.pop();
         }

         // Compute dp[i]
         if (s.isEmpty()) {
            dp[i] = calculateSum(books, 0, i);
         } else {
            int j = s.peek();
            dp[i] = dp[j] + calculateSum(books, j + 1, i);
         }

         // Push the current index onto the stack
         s.push(i);
      }


      // Return the maximum element in dp array
      return Arrays.stream(dp).max().getAsLong();
   }

   // Helper function to calculate the sum of books in a given range [l, r]
   private long calculateSum(int[] books, int l, int r) {
      long cnt = Math.min(books[r], r - l + 1);
      return (2 * books[r] - (cnt - 1)) * cnt / 2;
   }

   public long maximumBooks2(int[] books) {
      int len = books.length;
      long[] maxEndingAt = new long[len];
      maxEndingAt[0] = books[0];
      long result = books[0];
      for (int i = 1; i < len; i++) {
         int prevTaken = books[i];
         long maxEndingAti = prevTaken;
         for (int j = i - 1; j >= 0; j--) {
            if (books[j] <= prevTaken - 1) {
               maxEndingAti += maxEndingAt[j];
               break;
            }

            int curr = prevTaken - 1;
            if (curr < 1) break;
            maxEndingAti += curr;
            prevTaken = curr;
         }
         maxEndingAt[i] = maxEndingAti;
         result = Math.max(result, maxEndingAti);
      }
      return result;
   }
}
