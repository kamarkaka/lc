package main.java.com.kamarkaka;

/***
 * 418. Sentence Screen Fitting
 * Given a rows x cols screen and a sentence represented as a list of strings, return the number of times the given sentence can be fitted on the screen.
 * The order of words in the sentence must remain unchanged, and a word cannot be split into two lines. A single space must separate two consecutive words in a line.
 *
 * Example 1:
 *   Input: sentence = ["hello","world"], rows = 2, cols = 8
 *   Output: 1
 *   Explanation:
 *     hello---
 *     world---
 *     The character '-' signifies an empty space on the screen.
 *
 * Example 2:
 *   Input: sentence = ["a", "bcd", "e"], rows = 3, cols = 6
 *   Output: 2
 *   Explanation:
 *     a-bcd-
 *     e-a---
 *     bcd-e-
 *     The character '-' signifies an empty space on the screen.
 *
 * Example 3:
 *   Input: sentence = ["i","had","apple","pie"], rows = 4, cols = 5
 *   Output: 1
 *   Explanation:
 *     i-had
 *     apple
 *     pie-i
 *     had--
 *     The character '-' signifies an empty space on the screen.
 *
 * Constraints:
 * 1 <= sentence.length <= 100
 * 1 <= sentence[i].length <= 10
 * sentence[i] consists of lowercase English letters.
 * 1 <= rows, cols <= 2 * 10^4
 */
public class LC0418 {
   public int wordsTyping(String[] sentence, int rows, int cols) {
      int currCol = 0, currRow = 0, currWord = 0;
      int count = 0;
      while (true) {
         String word = sentence[currWord];

         if (currCol != 0) currCol++;

         if (currCol + word.length() <= cols) {
            currCol += word.length();
            currWord++;
            if (currWord == sentence.length) {
               count++;
               currWord = 0;
            }
         } else {
            currCol = 0;
            currRow++;
            if (currRow == rows) break;
         }
      }
      return count;
   }

   public int wordsTyping2(String[] sentence, int rows, int cols) {
      int numOfWords = sentence.length; // total words
      int[] wordLength = new int[numOfWords]; // stores len of each word at 'i'
      int[] firstWordNextRowIdx = new int[numOfWords]; // idx of first word for the next row
      int[] numOfSentenceInRow = new int[numOfWords]; // num of sentences in a row

      // find length of each word
      for(int i = 0; i < numOfWords; i++) {
         wordLength[i] = sentence[i].length();
      }

      // each word is treated as potential word to start a row
      for(int i = 0; i < numOfWords; i++) {
         int currWordIdx = i;
         int currLen = 0;
         int totalSentenceInRow = 0;

         // try to fit as many words in a line as you can
         while(currLen + wordLength[currWordIdx] <= cols) {
            currLen += wordLength[currWordIdx] + 1; // + 1 for space
            currWordIdx++;

            if(currWordIdx == numOfWords) {
               currWordIdx = 0;
               totalSentenceInRow++;
            }
         }

         firstWordNextRowIdx[i] = currWordIdx; //  before exiting while() we already increased 'currWordIdx' so next line will start with the word at that idx
         numOfSentenceInRow[i] = totalSentenceInRow;
      }

      int totalSentence = 0;
      int startWordIdxInRow = 0; // first row always start with word at idx 0

      for(int i = 0; i < rows; i++) {
         totalSentence += numOfSentenceInRow[startWordIdxInRow];
         startWordIdxInRow = firstWordNextRowIdx[startWordIdxInRow]; // gets the start word idx for next row
      }

      return totalSentence;
   }

   public static void run() {
      LC0418 solution = new LC0418();
      System.out.println(solution.wordsTyping2(new String[] {"i","had","apple","pie"}, 4, 5));
   }
}
