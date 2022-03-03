package com.kamarkaka.stripe;

public class PtoDays {
   public int findMaxVac(char[] calendar, int pto) {
      int maxVac = 0;
      int i = 0, j = 0, currVac = 0;
      while (j < calendar.length) {
         if (calendar[j] == 'H') {
            currVac++;
            j++;
         } else {
            if (pto == 0) {
               maxVac = Math.max(maxVac, currVac);
               while (pto == 0) {
                  if (calendar[i] == 'W') {
                     pto++;
                  }
                  currVac--;
                  i++;
               }
            } else {
               currVac++;
               j++;
               pto--;
            }
         }
      }
      return maxVac;
   }

   public static void run() {
      PtoDays sol = new PtoDays();
      System.out.println(sol.findMaxVac(new char[] {'W','H','H','W','W','H','W'}, 2));
   }
}
