package main.java.com.kamarkaka;

public class LC0053 {
   public int maxSubArray(int[] nums) {
      if (nums == null || nums.length == 0) return 0;
      return helper(nums, 0, nums.length - 1);
   }

   private int helper(int[] nums, int start, int end) {
      if (start > end) return Integer.MIN_VALUE;
      if (start == end) return nums[start];

      int mid = start + (end - start) / 2;
      int leftMax = helper(nums, start, mid - 1);
      int rightMax = helper(nums, mid + 1, end);

      int leftExtend = 0;
      for (int i = mid - 1, sum = 0; i >= start; i--) {
         sum += nums[i];
         leftExtend = Math.max(leftExtend, sum);
      }

      int rightExtend = 0;
      for (int i = mid + 1, sum = 0; i <= end; i++) {
         sum += nums[i];
         rightExtend = Math.max(rightExtend, sum);
      }

      return Math.max(Math.max(leftMax, rightMax), leftExtend + nums[mid] + rightExtend);
   }

   public static void run() {
      LC0053 solution = new LC0053();
      System.out.println(solution.maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4}));
      System.out.println(solution.maxSubArray(new int[] {1}));
      System.out.println(solution.maxSubArray(new int[] {5,4,-1,7,8}));
      System.out.println(solution.maxSubArray(new int[] {-2,1}));
      System.out.println(solution.maxSubArray(new int[] {-2,-1}));
   }
}
