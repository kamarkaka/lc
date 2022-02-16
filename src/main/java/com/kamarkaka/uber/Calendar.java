package com.kamarkaka.uber;

public class Calendar {
   public int scheduleMeeting(int[][] schedules) {
      int p1 = 0, p2 = 0;

      while (p1 < schedules[0].length || p2 < schedules[1].length) {
         int p1Start = findNextStart(p1, schedules[0]);
         int p2Start = findNextStart(p2, schedules[1]);
         if (p1Start == p2Start) return p1Start;

         if (p1Start < p2Start) {
            if (p1 < schedules[0].length - 1) p1++;
            else return p2Start;
         } else {
            if (p2 < schedules[1].length - 1) p2++;
            else return p1Start;
         }
      }

      return -1;
   }

   private int findNextStart(int p, int[] schedule) {
      int n = schedule.length;
      while (p < n) {
         int currStart = schedule[p];
         int nextStart = (currStart % 60 == 0) ? currStart + 60 : currStart - currStart % 60 + 60;
         if (p == n - 1 || schedule[p+1] >= nextStart + 60) {
            return nextStart;
         }
         p++;
      }

      int currStart = schedule[n-1];
      return (currStart % 60 == 0) ? currStart + 60 : currStart - currStart % 60 + 60;
   }

   public static void run() {
      Calendar sol = new Calendar();
      System.out.println(sol.scheduleMeeting(new int[][] {
            {0, 60, 180, 600},
            {30, 90, 120}
      }));
   }
}
