package com.kamarkaka;

/***
 * 1004. Max Consecutive Ones III
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
 *
 * Example 1:
 *    Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 *    Output: 6
 *    Explanation: [1,1,1,0,0,1,1,1,1,1,1]
 *       Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 *
 * Example 2:
 *    Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
 *    Output: 10
 *    Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 *       Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 *
 * Constraints:
 *    1 <= nums.length <= 10^5
 *    nums[i] is either 0 or 1.
 *    0 <= k <= nums.length
 */
public class LC1004 {
   public int longestOnes(int[] nums, int k) {
      int left = 0, right;
      int maxLen = 0;
      for (right = 0; right < nums.length; right++) {
         if (nums[right] == 0) k--;

         if (k < 0) {
            while (nums[left] == 1) {
               left++;
            }
            k++;
            left++;
         }

         if (k >= 0) maxLen = Math.max(maxLen, right - left + 1);
      }
      return maxLen;
   }

   public static void run() {
      LC1004 sol = new LC1004();
      System.out.println(sol.longestOnes(new int[] {1,1,1,0,0,0,1,1,1,1,0}, 2));
   }
}
