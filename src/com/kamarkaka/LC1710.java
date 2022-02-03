package com.kamarkaka;

import java.util.Arrays;
import java.util.Comparator;

public class LC1710 {
   public int maximumUnits(int[][] boxTypes, int truckSize) {
      Arrays.sort(boxTypes, Comparator.comparingInt(o -> -o[1]));

      int currSize = 0, units = 0;
      for (int i = 0; i < boxTypes.length; i++) {
         int[] boxType = boxTypes[i];
         int numBox = boxType[0];
         int unit = boxType[1];

         int size = Math.min(numBox, truckSize - currSize);
         units += unit * size;
         currSize += size;

         if (currSize == truckSize) break;
      }

      return units;
   }
}
