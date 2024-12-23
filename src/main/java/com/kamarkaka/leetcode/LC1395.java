package com.kamarkaka.leetcode;

/***
 * 1395. Count Number of Teams
 * There are n soldiers standing in a line. Each soldier is assigned a unique rating value.
 * You have to form a team of 3 soldiers amongst them under the following rules:
 *    Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
 *    A team is valid if: (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k]) where (0 <= i < j < k < n).
 * Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).
 *
 * Example 1:
 *    Input: rating = [2,5,3,4,1]
 *    Output: 3
 *    Explanation: We can form three teams given the conditions. (2,3,4), (5,4,1), (5,3,1).
 *
 * Example 2:
 *    Input: rating = [2,1,3]
 *    Output: 0
 *    Explanation: We can't form any team given the conditions.
 *
 * Example 3:
 *    Input: rating = [1,2,3,4]
 *    Output: 4
 *
 * Constraints:
 *    n == rating.length
 *    3 <= n <= 1000
 *    1 <= rating[i] <= 10^5
 *    All the integers in rating are unique.
 */
public class LC1395 {
   public int numTeams(int[] rating) {
      int res = 0;
      for (int mid = 1; mid < rating.length - 1; mid++) {
         int leftSmaller = 0, leftGreater = 0;
         int rightGreater = 0, rightSmaller = 0;
         for (int left = 0; left < mid; left++) {
            if (rating[left] < rating[mid])
               leftSmaller++;
            else
               leftGreater++;
         }

         for (int right = mid + 1; right < rating.length; right++) {
            if (rating[mid] < rating[right])
               rightGreater++;
            else
               rightSmaller++;
         }

         res += leftSmaller * rightGreater;
         res += leftGreater * rightSmaller;
      }
      return res;
   }

   public int numTeams2(int[] rating) {
      int[] idp = new int[rating.length];
      int[] ddp = new int[rating.length];
      int res = 0;
      for (int i = 0; i < rating.length; i++) {
         for (int j = 0; j < i; j++) {
            if (rating[i] > rating[j]) {
               idp[i]++;
               res += idp[j];
            } else {
               ddp[i]++;
               res += ddp[j];
            }
         }
      }
      return res;
   }

   public static void run() {
      LC1395 solution = new LC1395();
      System.out.println(solution.numTeams(new int[] {2,5,3,4,1}));
   }
}
