package com.kamarkaka.leetcode;

/***
 * 1185. Day of the Week
 * Given a date, return the corresponding day of the week for that date.
 * The input is given as three integers representing the day, month and year respectively.
 * Return the answer as one of the following values {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
 * "Saturday"}.
 * Example 1:
 *   Input: day = 31, month = 8, year = 2019
 *   Output: "Saturday"
 * Example 2:
 *   Input: day = 18, month = 7, year = 1999
 *   Output: "Sunday"
 * Example 3:
 *   Input: day = 15, month = 8, year = 1993
 *   Output: "Sunday"
 * Constraints:
 *   The given dates are valid dates between the years 1971 and 2100.
 */
public class LC1185 {
    public String dayOfTheWeek(int day, int month, int year) {
        String[] dayOfWeekStr = new String[] {
                "Sunday",
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday"
        };

        int start = 5; // 1970.01.01 is Friday
        int days = 0;

        for (int i = 1971; i < year; i++) {
            if (isLeapYear(i)) days += 366;
            else days += 365;
        }

        for (int i = 1; i < month; i++) {
            if (i == 2) {
                if (isLeapYear(year)) days += 29;
                else days += 28;
            } else if (i == 4 || i == 6 || i == 9 || i == 11) {
                days += 30;
            } else {
                days += 31;
            }
        }

        days += (day - 1);
        return dayOfWeekStr[(start + days) % 7];
    }

    private boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 100 != 0 && year % 4 == 0);
    }
}
