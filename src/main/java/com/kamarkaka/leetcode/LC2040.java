package com.kamarkaka.leetcode;

import java.util.HashMap;
import java.util.Map;

/***
 * 2040. Kth Smallest Product of Two Sorted Arrays
 * Given two sorted 0-indexed integer arrays nums1 and nums2 as well as an integer k, return the kth (1-based) smallest
 * product of nums1[i] * nums2[j] where 0 <= i < nums1.length and 0 <= j < nums2.length.
 * Example 1:
 *   Input: nums1 = [2,5], nums2 = [3,4], k = 2
 *   Output: 8
 *   Explanation: The 2 smallest products are:
 *   - nums1[0] * nums2[0] = 2 * 3 = 6
 *   - nums1[0] * nums2[1] = 2 * 4 = 8
 *   The 2nd smallest product is 8.
 * Example 2:
 *   Input: nums1 = [-4,-2,0,3], nums2 = [2,4], k = 6
 *   Output: 0
 *   Explanation: The 6 smallest products are:
 *   - nums1[0] * nums2[1] = (-4) * 4 = -16
 *   - nums1[0] * nums2[0] = (-4) * 2 = -8
 *   - nums1[1] * nums2[1] = (-2) * 4 = -8
 *   - nums1[1] * nums2[0] = (-2) * 2 = -4
 *   - nums1[2] * nums2[0] = 0 * 2 = 0
 *   - nums1[2] * nums2[1] = 0 * 4 = 0
 *   The 6th smallest product is 0.
 * Example 3:
 *   Input: nums1 = [-2,-1,0,1,2], nums2 = [-3,-1,2,4,5], k = 3
 *   Output: -6
 *   Explanation: The 3 smallest products are:
 *   - nums1[0] * nums2[4] = (-2) * 5 = -10
 *   - nums1[0] * nums2[3] = (-2) * 4 = -8
 *   - nums1[4] * nums2[0] = 2 * (-3) = -6
 *   The 3rd smallest product is -6.
 * Constraints:
 *   1 <= nums1.length, nums2.length <= 5 * 10^4
 *   -10^5 <= nums1[i], nums2[j] <= 10^5
 *   1 <= k <= nums1.length * nums2.length
 *   nums1 and nums2 are sorted.
 */
public class LC2040 {
   public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
      long left = (long)-1e11;
      long right = (long)1e11;
      long result = 0l;

      while (left <= right) {
         long mid = (left + right) / 2;
         // check if we have at least k elements whose product is less than the current product
         // if yes, we have to move left
         if (check(mid, nums1, nums2, k)) {
            right = mid - 1;
            result = mid;
         } else {
            left = mid + 1;
         }
      }

      return result;
   }

   private boolean check(long mid, int[] nums1, int[] nums2, long k) {
      long count = 0;
      for (int i = 0; i < nums1.length; i++) {
         long value = (long) nums1[i];
         if (value == 0 && mid >= 0) {
            // if current element is 0 then we can add whole second array
            count += nums2.length;
         } else if (value < 0) {
            // if negative value, find the min index in second array so that element * nums[index] <= mid
            count += findMinIndex(value, mid, nums2);
         } else if (value > 0) {
            // if positive value, find the max index in second array so that element * nums[index] <= mid
            count += findMaxIndex(value, mid, nums2);
         }
      }
      return count >= k;
   }

   private long findMinIndex(long value, long mid, int[] nums2) {
      int left = 0, right = nums2.length - 1;
      long result = right + 1;
      while (left <= right) {
         int m = (left + right) / 2;
         if (value * nums2[m] <= mid) {
            right = m - 1;
            result = (long)m;
         } else {
            left = m + 1;
         }
      }
      return nums2.length - result;
   }

   private long findMaxIndex(long value, long mid, int[] nums2) {
      int left = 0, right = nums2.length - 1;
      long result = -1;
      while (left <= right) {
         int m = (left + right) / 2;
         if (value * nums2[m] <= mid) {
            left = m + 1;
            result = (long)m;
         } else {
            right = m - 1;
         }
      }
      return result + 1;
   }
}
