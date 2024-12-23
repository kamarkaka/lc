package com.kamarkaka.marshallwace;

/***
 * Given a string, count lower case and upper case, make sure all lower cases happen before upper case
 *
 * Example:
 * 1. abcABC -> 3
 * 2. abABCdef -> 2
 * 3. abcABCabc -> 0
 */
public class LowerCaseBeforeUpperCase {
    public int count(String S) {
        boolean[] lowerCase = new boolean[26];
        boolean[] upperCase = new boolean[26];

        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if ('a' <= c && c <= 'z') {
                lowerCase[c - 'a'] = true;
                upperCase[c - 'a'] = false;
            } else if ('A' <= c && c <= 'Z') {
                if (lowerCase[c - 'A']) {
                    upperCase[c - 'A'] = true;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < 26; i++) {
            if (lowerCase[i] && upperCase[i]) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        LowerCaseBeforeUpperCase solution = new LowerCaseBeforeUpperCase();
        System.out.println(solution.count("abcABC"));
        System.out.println(solution.count("abABCdef"));
        System.out.println(solution.count("abcABCabc"));

    }
}
