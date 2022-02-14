package main.java.com.kamarkaka.stripe;

import java.util.Arrays;

public class StableMatching {
   public int[] match(int[][] prefrences) {
      int n = prefrences.length;
      int[] wPartner = new int[n];
      boolean[] mFree = new boolean[n];

      Arrays.fill(wPartner, -1);
      Arrays.fill(mFree, true);

      int freeCount = n;
      while (freeCount > 0) {
         int m;
         for (m = 0; m < n; m++) {
            if (mFree[m]) break;
         }

         for (int i = 0; i < n && mFree[m]; i++) {
            int w = prefrences[m][i];

            if (wPartner[w] == -1) {
               wPartner[w] = m;
               mFree[m] = false;
               freeCount--;
            } else {
               int m1 = wPartner[w];
               if (!wPrefersM1OverM(prefrences, w, m, m1)) {
                  wPartner[w] = m;
                  mFree[m] = false;
                  mFree[m1] = true;
               }
            }
         }
      }
      return wPartner;
   }

   private boolean wPrefersM1OverM(int[][] preferences, int w, int m, int m1) {
      for (int i = 0; i < preferences.length; i++) {
         if (preferences[w][i] == m1) return true;
         if (preferences[w][i] == m) return false;
      }
      return false;
   }
}
