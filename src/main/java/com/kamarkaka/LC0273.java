/***
 * Convert a non-negative integer num to its English words representation.
 *
 * Example 1:
 *   Input: num = 123
 *   Output: "One Hundred Twenty Three"
 *
 * Example 2:
 *   Input: num = 12345
 *   Output: "Twelve Thousand Three Hundred Forty Five"
 *
 * Example 3:
 *   Input: num = 1234567
 *   Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 *
 * Example 4:
 *   Input: num = 1234567891
 *   Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 *
 * Constraints:
 * 0 <= num <= 2^31 - 1
 */
package com.kamarkaka;

import java.util.ArrayList;
import java.util.List;

public class LC0273 {
    public String numberToWords(int num) {
        if (num == 0) return con(0);

        List<String> res = new ArrayList<>();
        int[] divs = new int[] {1000000000, 1000000, 1000, 1};

        for (int i = 0; i < divs.length; i++) {
            int quotient = num / divs[i];
            num %= divs[i];

            if (quotient > 0) {
                res.addAll(con2(quotient));
                if (divs[i] > 1) res.add(con(divs[i]));
            }
            if (num == 0) return print(res);
        }

        return print(res);
    }

    private String con(int num) {
        return switch (num) {
            case 0 -> "Zero";
            case 1 -> "One";
            case 2 -> "Two";
            case 3 -> "Three";
            case 4 -> "Four";
            case 5 -> "Five";
            case 6 -> "Six";
            case 7 -> "Seven";
            case 8 -> "Eight";
            case 9 -> "Nine";
            case 10 -> "Ten";
            case 11 -> "Eleven";
            case 12 -> "Twelve";
            case 13 -> "Thirteen";
            case 14 -> "Fourteen";
            case 15 -> "Fifteen";
            case 16 -> "Sixteen";
            case 17 -> "Seventeen";
            case 18 -> "Eighteen";
            case 19 -> "Nineteen";
            case 20 -> "Twenty";
            case 30 -> "Thirty";
            case 40 -> "Forty";
            case 50 -> "Fifty";
            case 60 -> "Sixty";
            case 70 -> "Seventy";
            case 80 -> "Eighty";
            case 90 -> "Ninety";
            case 100 -> "Hundred";
            case 1000 -> "Thousand";
            case 1000000 -> "Million";
            case 1000000000 -> "Billion";
            default -> "";
        };
    }

    private List<String> con2(int num) {
        List<String> res = new ArrayList<>();

        int h = num / 100;
        int r = num % 100;

        if (h > 0) {
            res.add(con(h));
            res.add(con(100));
        }

        int t = r / 10;
        int r2 = r % 10;

        if (t > 1) {
            res.add(con(r - r2));

            if (r2 != 0) {
                res.add(con(r2));
            }
        } else if (t == 1) {
            res.add(con(r));
        } else if (t == 0 && r2 != 0) {
            res.add(con(r2));
        }

        return res;
    }

    private String print(List<String> res) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < res.size(); i++) {
            String re = res.get(i);
            if (i > 0) sb.append(" ");
            sb.append(re);
        }

        return sb.toString();
    }

    public static void run() {
        LC0273 solution = new LC0273();
        System.out.println(solution.numberToWords(123));
        System.out.println(solution.numberToWords(12345));
        System.out.println(solution.numberToWords(1234567));
        System.out.println(solution.numberToWords(1234567891));
    }
}
