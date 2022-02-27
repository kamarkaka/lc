package com.kamarkaka.twosigma;

public class Monday {
   private final Date anchorDate = new Date(2022, 1, 1, 6);

   public int getPreviousMonday(int date) {
      Date input = parseDate(date);

      int weekday = getWeekday(input);
      int diff = weekday - 1;
      if (diff <= 0) diff += 7;

      Date output = new Date(input.year, input.month, input.day, 1);
      output.day -= diff;
      if (output.day < 0) {
         output.month = getPreviousMonth(output.month);
         output.year = output.month == 12 ? output.year - 1 : output.year;
         output.day += getDaysPerMonth(output.year, output.month);
      }

      return outputDate(output);
   }

   private int getWeekday(Date date) {
      int weekday = anchorDate.weekday;

      // align year
      int year = anchorDate.year;
      int dir = date.year > anchorDate.year ? 1 : -1;
      while (date.year != year) {
         year += dir;
         int daysPerYear = isLeapYear(year) ? 366 : 365;
         weekday += dir * (daysPerYear % 7);
         if (weekday > 6) weekday -= 7;
         if (weekday < 0) weekday += 7;
      }

      // align month
      int month = anchorDate.month;
      while (date.month != month) {
         int daysPerMonth = getDaysPerMonth(date.year, month);
         weekday += daysPerMonth % 7;
         if (weekday > 6) weekday -= 7;
         month++;
      }

      // align day
      int day = anchorDate.day;
      weekday += (date.day - day) % 7;
      if (weekday > 6) weekday -= 7;

      // update date object and return
      date.weekday = weekday;
      return weekday;
   }

   private boolean isLeapYear(int year) {
      if (year % 100 == 0) return year % 400 == 0;
      return year % 4 == 0;
   }

   private int getDaysPerMonth(int year, int month) {
      return switch (month) {
         case 1,3,5,7,8,10,12 -> 31;
         case 2 -> isLeapYear(year) ? 29 : 28;
         default -> 30;
      };
   }

   private int getPreviousMonth(int month) {
      return month == 1 ? 12 : month - 1;
   }

   private Date parseDate(int date) {
      int day = date % 100;

      date /= 100;
      int month = date % 100;

      date /= 100;
      int year = date;

      return new Date(year, month, day, -1);
   }

   private int outputDate(Date date) {
      return date.year * 10000 + date.month * 100 + date.day;
   }

   private class Date {
      int year;
      int month;
      int day;
      int weekday;

      public Date(int year, int month, int day, int weekday) {
         this.year = year;
         this.month = month;
         this.day = day;
         this.weekday = weekday;
      }
   }

   public static void run() {
      Monday sol = new Monday();
      System.out.println(sol.getPreviousMonday(20200202));
   }
}
