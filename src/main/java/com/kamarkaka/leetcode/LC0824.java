package com.kamarkaka.leetcode;

/***
 * 824. Goat Latin
 * You are given a string sentence that consist of words separated by spaces. Each word consists of lowercase and
 * uppercase letters only.
 * We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.) The rules of Goat
 * Latin are as follows:
 *   If a word begins with a vowel ('a', 'e', 'i', 'o', or 'u'), append "ma" to the end of the word.
 *   For example, the word "apple" becomes "applema".
 *   If a word begins with a consonant (i.e., not a vowel), remove the first letter and append it to the end, then add
 *   "ma".
 *   For example, the word "goat" becomes "oatgma".
 *   Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
 *   For example, the first word gets "a" added to the end, the second word gets "aa" added to the end, and so on.
 * Return the final sentence representing the conversion from sentence to Goat Latin.
 * Example 1:
 *   Input: sentence = "I speak Goat Latin"
 *   Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
 * Example 2:
 *   Input: sentence = "The quick brown fox jumped over the lazy dog"
 *   Output:
 *   "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
 * Constraints:
 *   1 <= sentence.length <= 150
 *   sentence consists of English letters and spaces.
 *   sentence has no leading or trailing spaces.
 *   All the words in sentence are separated by a single space.
 */
public class LC0824 {
    public String toGoatLatin(String S) {
        if (S == null || S.length() == 0) return S;
        String result = "";

        int wordIndex = 0;
        StringBuilder sb = new StringBuilder();
        for (char c : S.toCharArray()) {
            if (c == ' ') {
                if (sb.length() > 0) {
                    String word = sb.toString();
                    wordIndex++;
                    sb = new StringBuilder();

                    word = convert(word, wordIndex);
                    result = result.isEmpty() ? word : result + " " + word;
                }
            } else {
                sb.append(c);
            }
        }

        if (sb.length() > 0) {
            String word = sb.toString();
            wordIndex++;
            word = convert(word, wordIndex);
            result = result.isEmpty() ? word : result + " " + word;
        }

        return result;
    }

    private String convert(String word, int index) {
        char c = word.charAt(0);
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
                || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U'
        ) {
            word += "ma";
        } else {
            word = word.substring(1) + c + "ma";
        }

        StringBuilder sb = new StringBuilder(word);
        for (int i = 0; i < index; i++) {
            sb.append('a');
        }
        return sb.toString();
    }
}
