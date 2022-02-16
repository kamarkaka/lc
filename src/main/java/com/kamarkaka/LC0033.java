package com.kamarkaka;

/***
 * 33. Search in Rotated Sorted Array
 * There is an integer array nums sorted in ascending order (with distinct values).
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 *    Input: nums = [4,5,6,7,0,1,2], target = 0
 *    Output: 4
 *
 * Example 2:
 *    Input: nums = [4,5,6,7,0,1,2], target = 3
 *    Output: -1
 *
 * Example 3:
 *    Input: nums = [1], target = 0
 *    Output: -1
 *
 * Constraints:
 *    1 <= nums.length <= 5000
 *    -10^4 <= nums[i] <= 10^4
 *    All values of nums are unique.
 *    nums is an ascending array that is possibly rotated.
 *    -10^4 <= target <= 10^4
 */
public class LC0033 {
   public int search(int[] nums, int target) {
      int lo = 0, hi = nums.length - 1;
      return search(nums, target, lo, hi);
   }

   private int search(int[] nums, int target, int lo, int hi) {
      if (lo == hi) return target == nums[lo] ? lo : -1;
      if (lo + 1 == hi) return target == nums[lo] ? lo : (target == nums[hi] ? hi : -1);

      int mid = lo + (hi - lo) / 2;
      if (target == nums[mid]) return mid;

      if (nums[lo] < nums[hi]) {
         // pivot not here, do normal binary search
         if (target < nums[mid]) return search(nums, target, lo, mid - 1);
         else return search(nums, target, mid + 1, hi);
      } else {
         // pivot here
         int try1 = search(nums, target, lo, mid - 1);
         int try2 = search(nums, target, mid + 1, hi);
         return try1 != -1 ? try1 : try2;
      }
   }
}
