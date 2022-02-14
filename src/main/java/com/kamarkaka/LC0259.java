package main.java.com.kamarkaka;

import java.util.Arrays;

public class LC0259 {
   public int threeSumSmaller(int[] nums, int target) {
      Arrays.sort(nums);
      int sum = 0;
      for (int i = 0; i < nums.length - 2; i++) {
         sum += twoSumSmaller(nums, i + 1, target - nums[i]);
      }
      return sum;
   }

   private int twoSumSmaller(int[] nums, int startIndex, int target) {
      int sum = 0;
      int left = startIndex;
      int right = nums.length - 1;
      while (left < right) {
         if (nums[left] + nums[right] < target) {
            sum += right - left;
            left++;
         } else {
            right--;
         }
      }
      return sum;
   }

   public static void run() {
      LC0259 solution = new LC0259();
      System.out.println(solution.threeSumSmaller(new int[] {3,2,-2,6,2,-2,6,-2,-4,2,3,0,4,4,1}, 3));
      System.out.println(solution.threeSumSmaller(new int[] {1,-1,2,0,3,-2}, 2));
   }
}
