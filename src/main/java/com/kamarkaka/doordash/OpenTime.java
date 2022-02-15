package com.kamarkaka.doordash;

import java.util.*;

public class OpenTime {
   List<String> outputTime(String startTime, String endTime) {
      List<String> res = new ArrayList<>();
      DateTime date = new DateTime(startTime);
      DateTime end = new DateTime(endTime);

      if (date.min % 5 == 0) {
         res.add(date.toString());
      }

      while (true) {
         date = date.getNext();
         if (date.compareTo(end) < 0) {
            res.add(date.toString());
            continue;
         }
         break;
      }

      if (end.min % 5 == 0) {
         res.add(end.toString());
      }

      return res;
   }

   public static void run() {
      OpenTime solution = new OpenTime();
      System.out.println(solution.outputTime("sat 10:06 pm", "sun 00:00 am"));
   }


}

class DateTime implements Comparable<DateTime> {
   static Map<String, Integer> weekdays;
   int weekday;
   int hour;
   int min;

   public DateTime(int weekday, int hour, int min) {
      this.weekday = weekday;
      this.hour = hour;
      this.min = min;
   }

   public DateTime(String datetime) {
      if (weekdays == null || weekdays.isEmpty()) {
         weekdays = new HashMap<>();
         weekdays.put("sun", 0);
         weekdays.put("mon", 1);
         weekdays.put("tue", 2);
         weekdays.put("wed", 3);
         weekdays.put("thu", 4);
         weekdays.put("fri", 5);
         weekdays.put("sat", 6);
      }

      String[] parts = datetime.toLowerCase().split(" ");
      this.weekday = weekdays.get(parts[0]);

      String[] timeParts = parts[1].split(":");
      this.hour = Integer.parseInt(timeParts[0]);
      this.min = Integer.parseInt(timeParts[1]);

      if (parts[2].equals("pm")) {
         this.hour += 12;
      }
   }

   public DateTime getNext() {
      DateTime next = new DateTime(weekday, hour, min);
      next.min += (5 - next.min % 5);
      if (next.min == 60) {
         next.hour++;
         next.min = 0;
      }

      if (next.hour == 24) {
         next.weekday++;
         next.hour = 0;
      }

      if (next.weekday == 7) {
         next.weekday = 0;
      }

      return next;
   }

   public String toString() {
      return weekday + String.format("%1$2s%2$2s", hour, min).replace(' ', '0');
   }

   @Override
   public int compareTo(DateTime that) {
      if (this.weekday != that.weekday) return -1;
      if (this.hour < that.hour) return -1;
      if (this.min < that.min) return -1;

      return 1;
   }
}