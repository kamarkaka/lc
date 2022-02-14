package com.kamarkaka;

import java.util.ArrayList;
import java.util.List;

public class LC0057 {
   public int[][] insert(int[][] intervals, int[] newInterval) {
      List<int[]> resultList = new ArrayList<>();

      boolean newIntervalInserted = false;

      for (int[] interval : intervals) {
         if (interval[1] < newInterval[0]) {
            resultList.add(interval);
         } else if (interval[0] > newInterval[1]) {
            if (!newIntervalInserted) {
               resultList.add(newInterval);
               newIntervalInserted = true;
            }
            resultList.add(interval);
         }
         else {
            newInterval[0] = Math.min(newInterval[0], interval[0]);
            newInterval[1] = Math.max(newInterval[1], interval[1]);
         }
      }

      if (!newIntervalInserted) {
         resultList.add(newInterval);
      }

      int[][] resultArray = new int[resultList.size()][2];
      for (int i = 0; i < resultList.size(); i++) {
         resultArray[i] = resultList.get(i);
      }
      return resultArray;
   }
}
