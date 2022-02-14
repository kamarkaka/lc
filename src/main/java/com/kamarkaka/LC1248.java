package com.kamarkaka;

import java.util.HashMap;
import java.util.Map;

public class LC1248 {
   public int numberOfSubarrays(int[] nums, int k) {
      if (k > nums.length) return 0;

      Map<Integer, int[]> map = new HashMap<>();

      int i, oddCount = 0;
      for (i = 0; i < nums.length; i++) {
         if (nums[i] % 2 == 1) {
            oddCount++;

            if (oddCount == k) {
               int evenCount = 0, j;
               for (j = 0; j < i; j++) {
                  if (nums[j] % 2 == 0) {
                     evenCount++;
                  } else {
                     break;
                  }
               }
               map.put(i, new int[] {j, evenCount + 1, evenCount + 1});
               break;
            }
         }
      }

      i++;
      while (i < nums.length) {
         int[] pre = map.get(i - 1);
         if (nums[i] % 2 == 0) {
            map.put(i, new int[] {pre[0], pre[1], pre[1] + pre[2]});
         } else {
            int j = pre[0] + 1, evenCount = 0;
            for (; j < i; j++) {
               if (nums[j] % 2 == 0) {
                  evenCount++;
               } else {
                  break;
               }
            }
            map.put(i, new int[] {j, evenCount + 1, evenCount + 1 + pre[2]});
         }
         i++;
      }

      if (map.containsKey(nums.length - 1)) {
         return map.get(nums.length - 1)[2];
      } else {
         return 0;
      }
   }

   public static void run() {
      LC1248 solution = new LC1248();
      System.out.println(solution.numberOfSubarrays(new int[] {2,2,2,1,2,2,1,1,2,2}, 2));
      System.out.println(solution.numberOfSubarrays(new int[] {2,2,2,1,2,2,1,2,2,2}, 2));
      System.out.println(solution.numberOfSubarrays(new int[] {2,4,6}, 1));
      System.out.println(solution.numberOfSubarrays(new int[] {1,1,2,1,1}, 3));
   }
}
