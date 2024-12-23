package com.kamarkaka.marshallwace;

import java.util.Arrays;

/***
 * You are given a string S, which consists entirely of decimal digits (0-9). Using digits from S, create a palindromic
 * number with the largest possible decimal value. You should use at least one digit, and you can reorder the digits. A
 * palindromic number remains the same when its digits are reversed; for instance, "7", "44" or "83238". Any palindromic
 * number you create should not, however, have any leading zeros, such as in "0990" or "010".
 * For example, decimal palindromic numbers that can be created from "8199" are: "1", "8", "9", "99", "919" and "989".
 * Among them, "989" has the largest value.
 * Write a function that, given a string S of N digits, returns the string representing the palindromic number with the
 * largest value.
 *
 * Examples:
 * 1. Given "39878", your function should return "898".
 * 2. Given "00900", your function should return "9".
 * 3. Given "0000", your function should return "0".
 * 4. Given "54321", your function should return "5".
 */
public class Palindrom {
    public String generate(String S) {
        int[] digits = new int[10];
        Arrays.fill(digits, 0);

        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            digits[c - '0']++;
        }

        StringBuilder sb = new StringBuilder();
        while (true) {
            boolean appended = false;
            for (int i = 9; i >= 0; i--) {
                if (digits[i] >= 2) {
                    digits[i] -= 2;

                    if (i > 0) {
                        sb.insert(sb.length() / 2, (char)('0' + i));
                        sb.insert(sb.length() / 2, (char)('0' + i));
                        appended = true;
                    }
                    break;
                }
            }
            if (!appended) {
                break;
            }
        }

        for (int i = 9; i >= 0; i--) {
            if (digits[i] >= 1) {
                sb.insert(sb.length() / 2, (char)('0' + i));
                break;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Palindrom solution = new Palindrom();
        System.out.println(solution.generate("39878"));
        System.out.println(solution.generate("00900"));
        System.out.println(solution.generate("0000"));
        System.out.println(solution.generate("54321"));
    }
}
