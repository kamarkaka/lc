package com.kamarkaka.datadog;

import java.util.HashMap;
import java.util.Map;

/***
 * Given a string, find the total number of repetitions.
 * Repetition means if a word appear more than once, and repetition number is the times minus one.
 * For example: The sun is the largest object in the solar system. It is the only star.
 * "the" appears 4 times, "is" appears 2 time so return value is 3 + 1 = 4
 */
public class RepeatedWords {
    public int repetitionNumber(String paragraph) {
        Map<String, Integer> wordCount = new HashMap<>();

        String[] words = paragraph.split(" ");
        for (String word : words) {
            word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        int sum = 0;
        for (int num : wordCount.values()) {
            sum += num - 1;
        }
        return sum;
    }

    public static void main(String[] args) {
        RepeatedWords solution = new RepeatedWords();
        System.out.println(solution.repetitionNumber("The sun is the largest object in the solar system. It is the only star."));
    }
}
