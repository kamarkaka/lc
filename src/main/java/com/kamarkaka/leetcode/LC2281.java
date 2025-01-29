package com.kamarkaka.leetcode;

import java.util.Arrays;
import java.util.Stack;

/***
 * 2281. Sum of Total Strength of Wizards
 * As the ruler of a kingdom, you have an army of wizards at your command.
 * You are given a 0-indexed integer array strength, where strength[i] denotes the strength of the ith wizard. For a
 * contiguous group of wizards (i.e. the wizards' strengths form a subarray of strength), the total strength is defined
 * as the product of the following two values:
 *   The strength of the weakest wizard in the group.
 *   The total of all the individual strengths of the wizards in the group.
 * Return the sum of the total strengths of all contiguous groups of wizards. Since the answer may be very large, return
 * it modulo 10^9 + 7.
 * A subarray is a contiguous non-empty sequence of elements within an array.
 * Example 1:
 *   Input: strength = [1,3,1,2]
 *   Output: 44
 *   Explanation: The following are all the contiguous groups of wizards:
 *   - [1] from [1,3,1,2] has a total strength of min([1]) * sum([1]) = 1 * 1 = 1
 *   - [3] from [1,3,1,2] has a total strength of min([3]) * sum([3]) = 3 * 3 = 9
 *   - [1] from [1,3,1,2] has a total strength of min([1]) * sum([1]) = 1 * 1 = 1
 *   - [2] from [1,3,1,2] has a total strength of min([2]) * sum([2]) = 2 * 2 = 4
 *   - [1,3] from [1,3,1,2] has a total strength of min([1,3]) * sum([1,3]) = 1 * 4 = 4
 *   - [3,1] from [1,3,1,2] has a total strength of min([3,1]) * sum([3,1]) = 1 * 4 = 4
 *   - [1,2] from [1,3,1,2] has a total strength of min([1,2]) * sum([1,2]) = 1 * 3 = 3
 *   - [1,3,1] from [1,3,1,2] has a total strength of min([1,3,1]) * sum([1,3,1]) = 1 * 5 = 5
 *   - [3,1,2] from [1,3,1,2] has a total strength of min([3,1,2]) * sum([3,1,2]) = 1 * 6 = 6
 *   - [1,3,1,2] from [1,3,1,2] has a total strength of min([1,3,1,2]) * sum([1,3,1,2]) = 1 * 7 = 7
 *   The sum of all the total strengths is 1 + 9 + 1 + 4 + 4 + 4 + 3 + 5 + 6 + 7 = 44.
 * Example 2:
 *   Input: strength = [5,4,6]
 *   Output: 213
 *   Explanation: The following are all the contiguous groups of wizards:
 *   - [5] from [5,4,6] has a total strength of min([5]) * sum([5]) = 5 * 5 = 25
 *   - [4] from [5,4,6] has a total strength of min([4]) * sum([4]) = 4 * 4 = 16
 *   - [6] from [5,4,6] has a total strength of min([6]) * sum([6]) = 6 * 6 = 36
 *   - [5,4] from [5,4,6] has a total strength of min([5,4]) * sum([5,4]) = 4 * 9 = 36
 *   - [4,6] from [5,4,6] has a total strength of min([4,6]) * sum([4,6]) = 4 * 10 = 40
 *   - [5,4,6] from [5,4,6] has a total strength of min([5,4,6]) * sum([5,4,6]) = 4 * 15 = 60
 *   The sum of all the total strengths is 25 + 16 + 36 + 36 + 40 + 60 = 213.
 * Constraints:
 *   1 <= strength.length <= 10^5
 *   1 <= strength[i] <= 10^9
 */
public class LC2281 {
   public static int totalStrength(int[] strength) {
      int mod = 1_000_000_007, n = strength.length;

      // get the first index of non-larger value to strength[i]'s right
      Stack<Integer> stack = new Stack<>();
      int[] rightIndex = new int[n];
      Arrays.fill(rightIndex, n);
      for (int i = 0; i < n; i++) {
         while (!stack.isEmpty() && strength[stack.peek()] >= strength[i]) {
            rightIndex[stack.pop()] = i;
         }
         stack.push(i);
      }

      // get the first index of smaller value to strenght[i]'s left
      stack.clear();
      int[] leftIndex = new int[n];
      Arrays.fill(leftIndex, -1);
      for (int i = n - 1; i >= 0; i--) {
         while (!stack.isEmpty() && strength[stack.peek()] > strength[i]) {
            leftIndex[stack.pop()] = i;
         }
         stack.push(i);
      }

      // get the prefix sum of the prefix sum array of strength
      long result = 0;
      long[] presumOfPresum = new long[n + 2];
      for (int i = 0; i < n; i++) {
         presumOfPresum[i + 2] = (presumOfPresum[i + 1] + strength[i]) % mod;
      }
      for (int i = 1; i <= n; i++) {
         presumOfPresum[i + 1] = (presumOfPresum[i + 1] + presumOfPresum[i]) % mod;
      }

      // for each element, we get the value of R_term - L_term
      for (int i = 0; i < n; i++) {
         int left = leftIndex[i], right = rightIndex[i];
         int leftCount = i - left, rightCount = right - i;

         long leftSum = presumOfPresum[i + 1] - presumOfPresum[Math.max(0, left + 1)];
         long rightSum = presumOfPresum[right + 1] - presumOfPresum[i + 1];

         long contribution = ((rightSum * leftCount) % mod - (leftSum * rightCount) % mod) % mod;
         result = (result + (strength[i] * contribution) % mod + mod) % mod;
      }

      return (int)result;
   }

   public static void main(String[] args) {
      int res = LC2281.totalStrength(new int[] {1,3,1,2});
      System.out.println(res);
   }
}
