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
         int num;

         if (distinctCount == count) {
            num = nums[write];
            nums[write] = read - write;

            freqMap.put(num, freqMap.get(num) - 1);
            if (freqMap.get(num) == 0) distinctCount--;
            write++;
         } else if (read == nums.length) {
            num = nums[write];
            nums[write] = -1;

            freqMap.put(num, freqMap.get(num) - 1);
            if (freqMap.get(num) == 0) distinctCount--;
            write++;
         }

         if (read < nums.length) {
            num = nums[read];
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
      Utilities.print(sol.subarray(new int[] {1,2,3,3,2,1}, 3));
   }
}
