package com.kamarkaka.meta;

import com.kamarkaka.common.Utilities;

import java.util.ArrayList;
import java.util.List;

/***
 * given an integer array, find all subarrays of length l, where l is the power of 2 (1,2,4,8,...)
 * that the sum of the subarray is between [k, 2k]
 */
public class SubArrays {
   public List<List<Integer>> findSubarrays(int[] nums, int k) {
      List<List<Integer>> res = new ArrayList<>();

      int[] sums = new int[nums.length + 1];
      sums[0] = 0;
      for (int i = 0; i < nums.length; i++) {
         sums[i+1] = nums[i] + sums[i];
      }

      for (int i = 0; i < nums.length; i++) {
         for (int len = 1; i + len <= nums.length; len *= 2) {
            int sum = sums[i+len] - sums[i];
            if (k <= sum && sum <= 2 * k) {
               List<Integer> list = new ArrayList<>();
               for (int j = i; j < i + len; j++) {
                  list.add(nums[j]);
               }
               res.add(list);
            }
         }
      }

      return res;
   }

   public static void run() {
      SubArrays sol = new SubArrays();
      Utilities.print(sol.findSubarrays(new int[] {1,0,2,0,4,6,0,1}, 7));
   }
}
