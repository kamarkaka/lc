package com.kamarkaka;

import java.util.Arrays;
import java.util.Comparator;

public class LC0973 {
   public int[][] kClosest(int[][] points, int k) {
      Arrays.sort(points, Comparator.comparingInt(o -> o[0] * o[0] + o[1] * o[1]));

      int[][] topK = new int[k][2];
      for (int i = 0; i < k; i++) {
         topK[i] = points[points.length - i - 1];
      }
      return topK;
   }
}
