package com.kamarkaka.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/***
 * 843. Guess the Word
 * This is an interactive problem.
 * You are given an array of unique strings wordlist where wordlist[i] is 6 letters long, and one word in this list is chosen as secret.
 * You may call Master.guess(word) to guess a word. The guessed word should have type string and must be from the original list with 6 lowercase letters.
 * This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word. Also, if your guess is not in the given wordlist, it will return -1 instead.
 * For each test case, you have exactly 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or fewer calls to Master.guess and at least one of these guesses was secret, then you pass the test case.
 *
 * Example 1:
 *   Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"], numguesses = 10
 *   Output: You guessed the secret word correctly.
 *   Explanation:
 *   master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
 *   master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
 *   master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
 *   master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
 *   master.guess("abcczz") returns 4, because "abcczz" has 4 matches.
 *   We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
 *
 * Example 2:
 *   Input: secret = "hamada", wordlist = ["hamada","khaled"], numguesses = 10
 *   Output: You guessed the secret word correctly.
 *
 * Constraints:
 *   1 <= wordlist.length <= 100
 *   wordlist[i].length == 6
 *   wordlist[i] consist of lowercase English letters.
 *   All the strings of wordlist are unique.
 *   secret exists in wordlist.
 *   numguesses == 10
 */
public class LC0843 {
   private final static int MAX_GUESS_CALLS = 10;

   public void findSecretWord(String[] wordlist, Master master) {
      Set<String> words = new HashSet<>(Arrays.asList(wordlist));

      if(words.size() <= MAX_GUESS_CALLS) {
         for (String word : words) {
            if (master.guess(word) == word.length()) return;
         }
      }

      for (int i = 0; i < MAX_GUESS_CALLS; i++) {
         String guessedWord = words.iterator().next();
         int matches = master.guess(guessedWord);
         if (matches == guessedWord.length()) return;

         reduceWords(words, guessedWord, matches);
      }
   }

   private void reduceWords(Set<String> words, String guessedWord, int matches) {
      Iterator<String> iterator = words.iterator();
      while (iterator.hasNext()) {
         String word = iterator.next();
         int matchCount = 0;
         for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guessedWord.charAt(i)) matchCount++;
            if (matchCount > matches) break;
         }
         if (matchCount != matches) iterator.remove();
      }
   }

   interface Master {
      int guess(String word);
   }
}
