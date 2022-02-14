package main.java.com.kamarkaka;

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
