package com.kamarkaka.leetcode;

import com.kamarkaka.common.Utilities;

/***
 * 1861. Rotating the Box
 * You are given an m x n matrix of characters box representing a side-view of a box. Each cell of the box is one of the following:
 *    A stone '#'
 *    A stationary obstacle '*'
 *    Empty '.'
 * The box is rotated 90 degrees clockwise, causing some of the stones to fall due to gravity. Each stone falls down until it lands on an obstacle, another stone, or the bottom of the box. Gravity does not affect the obstacles' positions, and the inertia from the box's rotation does not affect the stones' horizontal positions.
 * It is guaranteed that each stone in box rests on an obstacle, another stone, or the bottom of the box.
 * Return an n x m matrix representing the box after the rotation described above.
 *
 * Example 1:
 *    Input: box = [["#",".","#"]]
 *    Output: [["."],
 *             ["#"],
 *             ["#"]]
 *
 * Example 2:
 *    Input: box = [["#",".","*","."],
 *                  ["#","#","*","."]]
 *    Output: [["#","."],
 *             ["#","#"],
 *             ["*","*"],
 *             [".","."]]
 *
 * Example 3:
 *    Input: box = [["#","#","*",".","*","."],
 *                  ["#","#","#","*",".","."],
 *                  ["#","#","#",".","#","."]]
 *    Output: [[".","#","#"],
 *             [".","#","#"],
 *             ["#","#","*"],
 *             ["#","*","."],
 *             ["#",".","*"],
 *             ["#",".","."]]
 *
 * Constraints:
 *    m == box.length
 *    n == box[i].length
 *    1 <= m, n <= 500
 *    box[i][j] is either '#', '*', or '.'.
 */
public class LC1861 {
   public char[][] rotateTheBox(char[][] box) {
      dropStones(box);
      return rotate(box);
   }

   private void dropStones(char[][] box) {
      int rows = box.length;
      for (int i = 0; i < rows; i++) {
         dropRow(box[i]);
      }
   }

   private void dropRow(char[] row) {
      int cols = row.length;
      int writer = cols - 1;
      for (int i = cols - 1; i >= 0; i--) {
         if (row[i] == '*') {
            writer = i - 1;
         } else if (row[i] == '#') {
            if (writer >= 0) {
               row[i] = '.';
               row[writer--] = '#';
            }
         }
      }

      while (writer >= 0) {
         row[writer--] = '.';
      }
   }

   private char[][] rotate(char[][] box) {
      int rows = box.length, cols = box[0].length;
      char[][] rotatedBox = new char[cols][rows];

      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < cols; j++) {
            rotatedBox[j][rows-1-i] = box[i][j];
         }
      }

      return rotatedBox;
   }

   public static void run() {
      LC1861 sol = new LC1861();
      Utilities.print(sol.rotateTheBox(new char[][] {
         {'*','#','*','.','.','.','#','.','*','.'}
      }));
   }
}
