package main.java.com.kamarkaka;

import main.java.com.kamarkaka.common.Utilities;

public class LC0167 {
   public int[] twoSum(int[] numbers, int target) {
      int i = 0;
      int j = numbers.length - 1;
      int ans[] = new int[2];
      while (i < j) {
         int sum = numbers[i] + numbers[j];
         if (sum < target) {
            i++;
         }
         else if (sum > target) {
            j--;
         }
         else {
            ans[0] = i+1;
            ans[1] = j+1;
            break;
         }
      }
      return ans;
   }

   public static void run() {
      LC0167 solution = new LC0167();
      int[] res = solution.twoSum(new int[] {3,24,50,79,88,150,345}, 200);
      Utilities.print(res);
   }
}
