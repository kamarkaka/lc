package com.kamarkaka;

import com.kamarkaka.common.Utilities;

/***
 * 283. Move Zeroes
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * Note that you must do this in-place without making a copy of the array.
 *
 * Example 1:
 *    Input: nums = [0,1,0,3,12]
 *    Output: [1,3,12,0,0]
 *
 * Example 2:
 *    Input: nums = [0]
 *    Output: [0]
 *
 * Constraints:
 *    1 <= nums.length <= 10^4
 *    -2^31 <= nums[i] <= 2^31 - 1
 *
 * Follow up: Could you minimize the total number of operations done?
 */
public class LC0283 {
   public void moveZeroes(int[] nums) {
      if (nums == null || nums.length == 0) return;

      int writePointer = 0;
      int readPointer = 0;

      while (readPointer < nums.length) {
         int num = nums[readPointer++];
         if (num != 0) {
            nums[writePointer++] = num;
         }
      }

      while (writePointer < nums.length) {
         nums[writePointer++] = 0;
      }
   }

   public static void run() {
      LC0283 sol = new LC0283();
      int[] nums = new int[] {0,1,0,3,12};
      Utilities.print(nums);
      sol.moveZeroes(nums);
      Utilities.print(nums);
   }
}
