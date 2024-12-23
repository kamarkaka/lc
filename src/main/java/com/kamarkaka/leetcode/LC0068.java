package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.List;

/***
 * 68. Text Justification
 * Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
 * For the last line of text, it should be left-justified and no extra space is inserted between words.
 *
 * Note:
 *   A word is defined as a character sequence consisting of non-space characters only.
 *   Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 *   The input array words contains at least one word.
 *
 * Example 1:
 *   Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
 *   Output:
 *   [
 *      "This    is    an",
 *      "example  of text",
 *      "justification.  "
 *   ]
 *
 * Example 2:
 *   Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
 *   Output:
 *   [
 *     "What   must   be",
 *     "acknowledgment  ",
 *     "shall be        "
 *   ]
 *   Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
 *   Note that the second line is also left-justified becase it contains only one word.
 *
 * Example 3:
 *   Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
 *   Output:
 *   [
 *     "Science  is  what we",
 *     "understand      well",
 *     "enough to explain to",
 *     "a  computer.  Art is",
 *     "everything  else  we",
 *     "do                  "
 *   ]
 *
 * Constraints:
 *   1 <= words.length <= 300
 *   1 <= words[i].length <= 20
 *   words[i] consists of only English letters and symbols.
 *   1 <= maxWidth <= 100
 *   words[i].length <= maxWidth
 */
public class LC0068 {
   public List<String> fullJustify(String[] words, int maxWidth) {
      List<String> result = new ArrayList<>();
      List<Line> lines = new ArrayList<>();

      Line currLine = new Line();
      for (int i = 0; i < words.length; i++) {
         currLine.addWord(words[i]);

         if (i < words.length - 1) {
            if (currLine.nextSize(words[i + 1]) > maxWidth) {
               lines.add(new Line(currLine));
               currLine = new Line();
            }
         } else {
            lines.add(new Line(currLine));
         }
      }

      for (int i = 0; i < lines.size(); i++) {
         Line line = lines.get(i);

         if (i < lines.size() - 1) result.add(line.justify(maxWidth, false));
         else result.add(line.justify(maxWidth, true));
      }

      return result;
   }

   private class Line {
      List<String> words;
      int size;

      public Line() {
         words = new ArrayList<>();
         size = 0;
      }

      public Line(Line line) {
         this.words = new ArrayList<>(line.words);
         this.size = line.size;
      }

      public void addWord(String word) {
         if (words.size() > 0) {
            size++;
         }

         words.add(word);
         size += word.length();
      }

      public int nextSize(String word) {
         if (words.size() == 0) return word.length();
         else return size + word.length() + 1;
      }

      public String justify(int maxWidth, boolean isLastLine) {
         StringBuilder sb = new StringBuilder();
         if (isLastLine || words.size() == 1) {
            for (int i = 0; i < words.size(); i++) {
               if (i == 0) {
                  sb.append(words.get(i));
               } else {
                  sb.append(" ").append(words.get(i));
               }
            }

            for (int i = sb.length() + 1; i <= maxWidth; i++) {
               sb.append(" ");
            }
         } else {
            int extraSpaces = maxWidth - size;
            int space = extraSpaces / (words.size() - 1);
            int extra = extraSpaces % (words.size() - 1);

            for (int i = 0; i < words.size(); i++) {
               if (i == 0) {
                  sb.append(words.get(i));
               } else {
                  for (int j = 0; j <= space; j++) {
                     sb.append(" ");
                  }

                  if (extra > 0) {
                     sb.append(" ");
                     extra--;
                  }

                  sb.append(words.get(i));
               }
            }
         }
         return sb.toString();
      }
   }
}
