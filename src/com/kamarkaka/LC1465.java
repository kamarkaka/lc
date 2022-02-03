package com.kamarkaka;

import java.util.Arrays;

public class LC1465 {
   public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
      int[] vSpaces = new int[horizontalCuts.length + 1];
      int[] hSpaces = new int[verticalCuts.length + 1];

      Arrays.sort(horizontalCuts);
      for (int i = 0; i < horizontalCuts.length; i++) {
         if (i == 0) {
            vSpaces[i] = horizontalCuts[i];
         } else {
            vSpaces[i] = horizontalCuts[i] - horizontalCuts[i - 1];
         }
      }
      vSpaces[vSpaces.length - 1] = h - horizontalCuts[horizontalCuts.length - 1];

      Arrays.sort(verticalCuts);
      for (int i = 0; i < verticalCuts.length; i++) {
         if (i == 0) {
            hSpaces[i] = verticalCuts[i];
         } else {
            hSpaces[i] = verticalCuts[i] - verticalCuts[i - 1];
         }
      }
      hSpaces[hSpaces.length - 1] = w - verticalCuts[verticalCuts.length - 1];

      Arrays.sort(vSpaces);
      Arrays.sort(hSpaces);

      long max = (long) vSpaces[vSpaces.length - 1] * hSpaces[hSpaces.length - 1];
      return (int) (max % 1_000_000_007);
   }
}
