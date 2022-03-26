package com.kamarkaka;

import java.util.Arrays;
import java.util.Random;

public class LC0215 {
   public int findKthLargest(int[] nums, int k) {
      Arrays.sort(nums);
      return nums[nums.length - k];
   }

   int [] nums;
   public int findKthLargest2(int[] nums, int k) {
      this.nums = nums;
      int size = nums.length;
      // kth largest is (N - k)th smallest
      return quickselect(0, size - 1, size - k);
   }

   private int quickselect(int left, int right, int k_smallest) {
      if (left == right) return nums[left];

      Random random_num = new Random();
      int pivot_index = left + random_num.nextInt(right - left);
      pivot_index = partition(left, right, pivot_index);

      if (k_smallest == pivot_index) return this.nums[k_smallest];
      if (k_smallest < pivot_index) return quickselect(left, pivot_index - 1, k_smallest);
      return quickselect(pivot_index + 1, right, k_smallest);
   }

   private int partition(int left, int right, int pivot_index) {
      int pivot = nums[pivot_index];
      swap(pivot_index, right);

      int store_index = left;
      for (int i = left; i <= right; i++) {
         if (nums[i] < pivot) {
            swap(store_index, i);
            store_index++;
         }
      }

      swap(store_index, right);
      return store_index;
   }

   private void swap(int a, int b) {
      int tmp = nums[a];
      nums[a] = nums[b];
      nums[b] = tmp;
   }
}
