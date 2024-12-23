package com.kamarkaka.marshallwace;

/***
 * In an even word, each letter occurs an even number of times.
 * Write a function that, given a string S consisting of N characters, returns the minimum number of letters that must
 * be deleted to obtain an even word.
 *
 * Examples:
 * 1. Given S = "acbcbba", the function should return 1 (one letter b must be deleted).
 * 2. Given S = "axxaxa", your function should return 2 (one letter a and one latter x must be deleted).
 * 3. Given S = "aaaa", your function should return 0 (there is no need to delete any letters).
 *
 * Write an efficient algorithm for the following assumptions:
 * - N is an integer within the range [0..200,000];
 * - string S consists only of lowercase letters (a-z).
 */
public class EvenWord {
    public int minimumNumberOfLettersToRemove(String S) {
        int result = 0;

        int[] letterCount = new int[26];

        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            letterCount[c - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (letterCount[i] % 2 == 1) {
                result++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        EvenWord solution = new EvenWord();

        System.out.println(solution.minimumNumberOfLettersToRemove("acbcbba"));
        System.out.println(solution.minimumNumberOfLettersToRemove("axxaxa"));
        System.out.println(solution.minimumNumberOfLettersToRemove("aaaa"));
    }
}
