package com.kamarkaka.leetcode;

import java.util.HashMap;

/***
 * 166. Fraction to Recurring Decimal
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 * If multiple answers are possible, return any of them.
 * It is guaranteed that the length of the answer string is less than 104 for all the given inputs.
 * Example 1:
 *   Input: numerator = 1, denominator = 2
 *   Output: "0.5"
 * Example 2:
 *   Input: numerator = 2, denominator = 1
 *   Output: "2"
 * Example 3:
 *   Input: numerator = 4, denominator = 333
 *   Output: "0.(012)"
 * Constraints:
 *   -2^31 <= numerator, denominator <= 2^31 - 1
 *   denominator != 0
 */
public class LC0166 {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        if (denominator == 0) return "";

        StringBuilder sb = new StringBuilder();
        if ((numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0)) sb.append("-");

        long n = Math.abs((long)numerator);
        long d = Math.abs((long)denominator);

        sb.append(n / d);
        long r = n % d;
        if (r == 0) return sb.toString();

        sb.append(".");
        r *= 10;

        HashMap<Long, Integer> myMap = new HashMap<>();

        while (r > 0) {
            if(myMap.containsKey(r)) {
                int beg = myMap.get(r);
                sb.insert(beg,'(');
                sb.append(')');
                return sb.toString();
            }

            myMap.put(r, sb.length());

            sb.append(r / d);
            r = r % d * 10;
        }

        return sb.toString();
    }
}
