package com.kamarkaka.twosigma;

import com.kamarkaka.common.Utilities;

import java.util.HashMap;
import java.util.Map;

public class DistinctCount {
   public int[] subarray(int[] nums, int count) {
      Map<Integer, Integer> freqMap = new HashMap<>();
      int distinctCount = 0;
      int read = 0, write = 0;

      while (write < nums.length) {
         if (distinctCount == count || read == nums.length) {
            int num = nums[write];

            if (distinctCount == count) {
               nums[write] = read - write;
            } else {
               nums[write] = -1;
            }

            freqMap.put(num, freqMap.get(num) - 1);
            if (freqMap.get(num) == 0) distinctCount--;
            write++;
         }

         if (read < nums.length) {
            int num = nums[read];
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
            if (freqMap.get(num) == 1) {
               distinctCount++;
            }
            read++;
         }
      }

      return nums;
   }

   public static void run() {
      DistinctCount sol = new DistinctCount();
      Utilities.print(sol.subarray(new int[] {1,2,3,3,1,2}, 3));
   }
}
