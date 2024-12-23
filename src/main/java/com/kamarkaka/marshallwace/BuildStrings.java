package com.kamarkaka.marshallwace;

import java.util.ArrayList;
import java.util.List;

/***
 * You are given an array S made of N string and an integer K. Choose at most K letters from the alphabet that will
 * allow you to build as many strings from array S as possible. Any of the chosen letters can be used multiple times
 * when building the strings.
 * What is the maximum number of strings from S that can be built?
 * Write a function that, given an array S and an integer K, returns the maximum number of strings from S that can be
 * built.
 * Examples:
 * 1. Given S = ["abc", "abb", "cb", "a", "bbb"] and K = 2, the function should return 3. Strings "abb", "a" and "bbb"
 * can be built using the two letters 'a' and 'b'.
 * 2. Given S = ["adf", "jjbh", "jcgj", "eijj", "adf"] and K = 3, the function should return 2. Two strings "adf"can be
 * built using the three letters 'a', 'd' and 'f'.
 * 3. Given S = ["abcd", "efgh"] and K = 3, the function should return 0. It is not possible to build any string from S
 * using at most three letters.
 * 4. Given S = ["bc", "edf", "fde", "dge", "abcd"] and K = 4, the function should return 3. Strings "edf", "fde" and
 * "dge" can be built using the four letters 'd', 'e', 'f' and 'g'.
 *
 * Write an efficient algorithm for the following assumptions:
 * - N is an integer within the range [1..50,000];
 * - K is an integer within the range [1..10];
 * - each string in S has a length within the range [1..15];
 * - each string in S is made from only the first ten lowercase letters of the alphabet ('a'-'j').
 */
public class BuildStrings {
    public int maximumNumberOfStrings(String[] S, int K) {
        List<Integer> bitmasks = new ArrayList<>();
        for (String s : S) {
            bitmasks.add(stringToBitMask(s));
        }

        List<int[]> subsets = genereateCombinations(10, K);

        int maxCount = 0;
        for (int[] subset : subsets) {
            int subsetMask = 0;
            for (int letter : subset) {
                subsetMask |= (1 << letter);
            }

            int count = 0;
            for (int bitmask : bitmasks) {
                if ((bitmask & subsetMask) == bitmask) {
                    count++;
                }
            }

            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
    }

    private static int stringToBitMask(String s) {
        int bitmask = 0;
        for (char c : s.toCharArray()) {
            bitmask |= (1 << (c - 'a'));
        }
        return bitmask;
    }

    private static List<int[]> genereateCombinations(int n, int k) {
        List<int[]> combinations = new ArrayList<>();
        genereateCombinationsHelper(n, k, 0, new ArrayList<>(), combinations);
        return combinations;
    }

    private static void genereateCombinationsHelper(int n, int k, int start, List<Integer> current, List<int[]> result) {
        if (current.size() == k) {
            result.add(current.stream().mapToInt(i -> i).toArray());
            return;
        }

        for (int i = start; i < n; i++) {
            current.add(i);
            genereateCombinationsHelper(n, k, i + 1, current, result);
            current.removeLast();
        }
    }

    public static void main(String[] args) {
        BuildStrings solution = new BuildStrings();
        System.out.println(solution.maximumNumberOfStrings(new String[] {"abc", "abb", "cb", "a", "bbb"}, 2));
        System.out.println(solution.maximumNumberOfStrings(new String[] {"adf", "jjbh", "jcgj", "eijj", "adf"}, 3));
        System.out.println(solution.maximumNumberOfStrings(new String[] {"abcd", "efgh"}, 3));
        System.out.println(solution.maximumNumberOfStrings(new String[] {"bc", "edf", "fde", "dge", "abcd"}, 4));
    }
}
