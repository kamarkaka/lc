package com.kamarkaka.leetcode;

/***
 * 1423. Maximum Points You Can Obtain from Cards
 * There are several cards arranged in a row, and each card has an associated number of points. The points are given in the integer array cardPoints.
 * In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
 * Your score is the sum of the points of the cards you have taken.
 * Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
 *
 * Example 1:
 * Input: cardPoints = [1,2,3,4,5,6,1], k = 3
 * Output: 12
 * Explanation: After the first step, your score will always be 1. However, choosing the rightmost card first will maximize your total score. The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.
 * Example 2:
 * Input: cardPoints = [2,2,2], k = 2
 * Output: 4
 * Explanation: Regardless of which two cards you take, your score will always be 4.
 * Example 3:
 * Input: cardPoints = [9,7,7,9,7,7,9], k = 7
 * Output: 55
 * Explanation: You have to take all the cards. Your score is the sum of points of all cards.
 *
 * Constraints:
 *   1 <= cardPoints.length <= 10^5
 *   1 <= cardPoints[i] <= 10^4
 *   1 <= k <= cardPoints.length
 */
public class LC1423 {
   public int maxScore(int[] cardPoints, int k) {
      int[] sumLeft = new int[k + 1];
      int[] sumRight = new int[k + 1];

      for (int i = 0; i < k; i++) {
         sumLeft[i + 1] = sumLeft[i] + cardPoints[i];
         sumRight[i + 1] = sumRight[i] + cardPoints[cardPoints.length - 1 - i];
      }

      int maxScore = Integer.MIN_VALUE;
      for (int left = 0; left <= k; left++) {
         int right = k - left;

         maxScore = Math.max(sumLeft[left] + sumRight[right], maxScore);
      }
      return maxScore;
   }

   public int maxScore2(int[] cardPoints, int k) {
      int frontScore = 0;
      int rearScore = 0;
      int n = cardPoints.length;

      for (int i = 0; i < k; i++) {
         frontScore += cardPoints[i];
      }

      // take all k cards from the beginning
      int maxScore = frontScore;

      // take i from the beginning and k - i from the end
      for (int i = k - 1; i >= 0; i--) {
         rearScore += cardPoints[n - (k - i)];
         frontScore -= cardPoints[i];
         int currentScore = rearScore + frontScore;
         maxScore = Math.max(maxScore, currentScore);
      }

      return maxScore;
   }

   public static void run() {
      LC1423 solution = new LC1423();
      System.out.println(solution.maxScore(new int[] {100,40,17,9,73,75}, 3));
   }
}
