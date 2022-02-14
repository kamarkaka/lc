package main.java.com.kamarkaka;

import java.util.Arrays;

/***
 * 532. K-diff Pairs in an Array
 * Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.
 * A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true:
 *   0 <= i < j < nums.length
 *   |nums[i] - nums[j]| == k
 * Notice that |val| denotes the absolute value of val.
 *
 * Example 1:
 *   Input: nums = [3,1,4,1,5], k = 2
 *   Output: 2
 *     Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
 *     Although we have two 1s in the input, we should only return the number of unique pairs.
 *
 * Example 2:
 *   Input: nums = [1,2,3,4,5], k = 1
 *   Output: 4
 *   Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
 *
 * Example 3:
 *   Input: nums = [1,3,1,5,4], k = 0
 *   Output: 1
 *   Explanation: There is one 0-diff pair in the array, (1, 1).
 *
 * Constraints:
 *   1 <= nums.length <= 10^4
 *   -10^7 <= nums[i] <= 10^7
 *   0 <= k <= 10^7
 */
public class LC0532 {
   public int findPairs(int[] nums, int k) {
      Arrays.sort(nums);
      int p0 = 0, p1 = 1, count = 0;

      while (p1 < nums.length) {
         int diff = nums[p1] - nums[p0];
         if (diff > k) {
            p0++;
         } else if (diff < k) {
            p1++;
         } else {
            if (p0 == 0 || nums[p0] != nums[p0 - 1]) count++;
            p0++;
         }

         if (p0 == p1) p1++;
      }

      return count;
   }

   public static void run() {
      LC0532 solution = new LC0532();
      System.out.println(solution.findPairs(new int[] {3,1,4,1,5}, 2));
      System.out.println(solution.findPairs(new int[] {1,2,3,4,5}, 1));
      System.out.println(solution.findPairs(new int[] {1,3,1,5,4}, 0));
   }
}
