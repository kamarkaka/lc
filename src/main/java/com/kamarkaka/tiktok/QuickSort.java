package com.kamarkaka.tiktok;

import com.kamarkaka.common.Utilities;

public class QuickSort {
   public void sort(int[] nums) {
      sort(nums, 0, nums.length - 1);
   }

   private void sort(int[] nums, int lo, int hi) {
      if (lo < hi) {
         int pivot = partition(nums, lo, hi);

         sort(nums, lo, pivot - 1);
         sort(nums, pivot + 1, hi);
      }
   }

   private void swap (int[] arr, int i, int j) {
      int tmp = arr[i];
      arr[i] = arr[j];
      arr[j] = tmp;
   }

   private int partition(int[] arr, int lo, int hi) {
      int pivot = arr[hi];

      int i = lo - 1;
      for (int j = lo; j < hi; j++) {
         if (arr[j] < pivot) {
            i++;
            swap(arr, i, j);
         }
      }
      swap(arr, i + 1, hi);
      return i + 1;
   }

   public static void run() {
      QuickSort sol = new QuickSort();
      int[] nums = new int[] {2,1,3,6,5};
      sol.sort(nums);
      Utilities.print(nums);
   }
}
