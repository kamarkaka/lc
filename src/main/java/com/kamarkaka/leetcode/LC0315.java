package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.List;

/***
 * 315. Count of Smaller Numbers After Self
 * You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 *
 * Example 1:
 *    Input: nums = [5,2,6,1]
 *    Output: [2,1,1,0]
 *    Explanation:
 *       To the right of 5 there are 2 smaller elements (2 and 1).
 *       To the right of 2 there is only 1 smaller element (1).
 *       To the right of 6 there is 1 smaller element (1).
 *       To the right of 1 there is 0 smaller element.
 *
 * Example 2:
 *    Input: nums = [-1]
 *    Output: [0]
 *
 * Example 3:
 *    Input: nums = [-1,-1]
 *    Output: [0,0]
 *
 * Constraints:
 *    1 <= nums.length <= 10^5
 *    -10^4 <= nums[i] <= 10^4
 */
public class LC0315 {
   public List<Integer> countSmaller(int[] nums) {
      int n = nums.length;
      int[] result = new int[n];
      int[] indices = new int[n];

      for (int i = 0; i < n; i++) {
         indices[i] = i;
      }

      mergeSort(indices, 0, n, result, nums);
      List<Integer> resultToReturn = new ArrayList<>(n);
      for (int i : result) {
         resultToReturn.add(i);
      }
      return resultToReturn;
   }

   private void mergeSort(int[] indices, int left, int right, int[] result, int[] nums) {
      if (right - left <= 1) return;

      int mid = (left + right) / 2;
      mergeSort(indices, left, mid, result, nums);
      mergeSort(indices, mid, right, result, nums);
      merge(indices, left, right, mid, result, nums);
   }

   private void merge(int[] indices, int left, int right, int mid, int[] result, int[] nums) {
      int i = left;
      int j = mid;
      List<Integer> temp = new ArrayList<>(right - left);
      while (i < mid && j < right) {
         if (nums[indices[i]] <= nums[indices[j]]) {
            result[indices[i]] += j - mid;
            temp.add(indices[i]);
            i++;
         } else {
            temp.add(indices[j]);
            j++;
         }
      }

      while (i < mid) {
         result[indices[i]] += j - mid;
         temp.add(indices[i]);
         i++;
      }

      while (j < right) {
         temp.add(indices[j]);
         j++;
      }

      for (int k = left; k < right; k++) {
         indices[k] = temp.get(k - left);
      }
   }

   public static void run() {
      LC0315 sol = new LC0315();
      System.out.println(sol.countSmaller(new int[] {5,2,6,1}));
   }
}
