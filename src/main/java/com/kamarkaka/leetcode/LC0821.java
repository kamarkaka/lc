package com.kamarkaka.leetcode;

/***
 * 821. Shortest Distance to a Character
 * Given a string s and a character c that occurs in s, return an array of integers answer where
 * answer.length == s.length and answer[i] is the distance from index i to the closest occurrence of character c in s.
 * The distance between two indices i and j is abs(i - j), where abs is the absolute value function.
 * Example 1:
 *   Input: s = "loveleetcode", c = "e"
 *   Output: [3,2,1,0,1,0,0,1,2,2,1,0]
 *   Explanation: The character 'e' appears at indices 3, 5, 6, and 11 (0-indexed).
 *   The closest occurrence of 'e' for index 0 is at index 3, so the distance is abs(0 - 3) = 3.
 *   The closest occurrence of 'e' for index 1 is at index 3, so the distance is abs(1 - 3) = 2.
 *   For index 4, there is a tie between the 'e' at index 3 and the 'e' at index 5, but the distance is still the same:
 *   abs(4 - 3) == abs(4 - 5) = 1.
 *   The closest occurrence of 'e' for index 8 is at index 6, so the distance is abs(8 - 6) = 2.
 * Example 2:
 *   Input: s = "aaab", c = "b"
 *   Output: [3,2,1,0]
 * Constraints:
 *   1 <= s.length <= 10^4
 *   s[i] and c are lowercase English letters.
 *   It is guaranteed that c occurs at least once in s.
 */
public class LC0821 {
    public int[] shortestToChar(String S, char C) {
        int leftCount = 0;
        int[] result = new int[S.length()];

        for (char c : S.toCharArray()) {
            if (c != C) leftCount++;
            else break;
        }

        for (int i = 0; i < leftCount; i++) {
            result[i] = leftCount - i;
        }

        if (leftCount == S.length()) return result;

        result[leftCount] = 0;

        int i = 1;
        int rightCount = leftCount;
        while (leftCount + i < S.length()) {
            char c = S.charAt(leftCount + i);
            if (c != C) i++;
            else {
                rightCount = leftCount + i;

                int mid = (rightCount + leftCount) / 2;
                int count = 1;
                for (int j = leftCount + 1; j <= mid; j++) {
                    result[j] = count++;
                }

                count = 1;
                for (int j = rightCount - 1; j > mid; j--) {
                    result[j] = count++;
                }

                result[rightCount] = 0;
                leftCount = rightCount;
                i = 1;
            }
        }

        int count = 1;
        for (i = 1; i + leftCount < S.length(); i++) {
            result[i + leftCount] = count++;
        }


        return result;
    }
}
