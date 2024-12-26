package com.kamarkaka.leetcode;

import java.util.Arrays;

/***
 * 350. Intersection of Two Arrays II
 * Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must
 * appear as many times as it shows in both arrays and you may return the result in any order.
 * Example 1:
 *   Input: nums1 = [1,2,2,1], nums2 = [2,2]
 *   Output: [2,2]
 * Example 2:
 *   Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 *   Output: [4,9]
 *   Explanation: [9,4] is also accepted.
 * Constraints:
 *   1 <= nums1.length, nums2.length <= 1000
 *   0 <= nums1[i], nums2[i] <= 1000
 * Follow up:
 *   What if the given array is already sorted? How would you optimize your algorithm?
 *   What if nums1's size is small compared to nums2's size? Which algorithm is better?
 *   What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into
 *   the memory at once?
 */
public class LC0350 {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return new int[0];

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int[] res = new int[Math.min(nums1.length, nums2.length)];
        int resSize = 0;

        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            int iVal = nums1[i];
            int jVal = nums2[j];

            if (iVal > jVal) {
                while (j < nums2.length && jVal == nums2[j]) j++;

            } else if (iVal < jVal) {
                while (i < nums1.length && iVal == nums1[i]) i++;

            } else { // equal
                res[resSize++] = iVal;

                i++;
                j++;
            }
        }

        if(resSize == 0) return new int[]{};
        int[] result = new int[resSize];
        for(i = 0; i < resSize; i++){
            result[i] = res[i];
        }
        return result;
    }
}
