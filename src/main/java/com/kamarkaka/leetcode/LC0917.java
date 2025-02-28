package com.kamarkaka.leetcode;

/***
 * 917. Reverse Only Letters
 * Given a string s, reverse the string according to the following rules:
 *   All the characters that are not English letters remain in the same position.
 *   All the English letters (lowercase or uppercase) should be reversed.
 * Return s after reversing it.
 * Example 1:
 *   Input: s = "ab-cd"
 *   Output: "dc-ba"
 * Example 2:
 *   Input: s = "a-bC-dEf-ghIj"
 *   Output: "j-Ih-gfE-dCba"
 * Example 3:
 *   Input: s = "Test1ng-Leet=code-Q!"
 *   Output: "Qedo1ct-eeLg=ntse-T!"
 * Constraints:
 *   1 <= s.length <= 100
 *   s consists of characters with ASCII values in the range [33, 122].
 *   s does not contain '\"' or '\\'.
 */
public class LC0917 {
    public String reverseOnlyLetters(String S) {
        char[] arr = S.toCharArray();
        int start = 0, end = arr.length - 1;
        while (start < end) {
            while (start < arr.length && !Character.isLetter(arr[start])) start++;
            while (end >= 0 && !Character.isLetter(arr[end])) end--;
            if (start < end) {
                char c = arr[start];
                arr[start] = arr[end];
                arr[end] = c;
            }
            start++;
            end--;
        }

        return new String(arr);
    }
}
