package com.kamarkaka.leetcode;

/***
 * 6. Zigzag Conversion
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to
 * display this pattern in a fixed font for better legibility)
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:
 *   string convert(string s, int numRows);
 * Example 1:
 *   Input: s = "PAYPALISHIRING", numRows = 3
 *   Output: "PAHNAPLSIIGYIR"
 * Example 2:
 *   Input: s = "PAYPALISHIRING", numRows = 4
 *   Output: "PINALSIGYAHRPI"
 *   Explanation:
 *   P     I    N
 *   A   L S  I G
 *   Y A   H R
 *   P     I
 * Example 3:
 *   Input: s = "A", numRows = 1
 *   Output: "A"
 * Constraints:
 *   1 <= s.length <= 1000
 *   s consists of English letters (lower-case and upper-case), ',' and '.'.
 *   1 <= numRows <= 1000
 */
public class LC0006 {
    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0) return s;
        if (numRows == 0) return "";
        if (numRows == 1) return s;
        StringBuilder[] sbs = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            sbs[i] = new StringBuilder();
        }

        int index = 0;
        boolean isIncremental = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            sbs[index].append(c);

            if (isIncremental) {
                index++;
                if (index == numRows) {
                    index = numRows - 2;
                    isIncremental = false;
                }
            } else {
                index--;

                if (index == -1) {
                    index = 1;
                    isIncremental = true;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder sb : sbs) {
            result.append(sb);
        }
        return result.toString();
    }
}
