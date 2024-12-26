package com.kamarkaka.leetcode;

/***
 * 251. Flatten 2D Vector
 * Design an iterator to flatten a 2D vector. It should support the next and hasNext operations.
 * Implement the Vector2D class:
 *   Vector2D(int[][] vec) initializes the object with the 2D vector vec.
 *   next() returns the next element from the 2D vector and moves the pointer one step forward. You may assume that all
 *   the calls to next are valid.
 *   hasNext() returns true if there are still some elements in the vector, and false otherwise.
 * Example 1:
 *   Input
 *   ["Vector2D", "next", "next", "next", "hasNext", "hasNext", "next", "hasNext"]
 *   [[[[1, 2], [3], [4]]], [], [], [], [], [], [], []]
 *   Output
 *   [null, 1, 2, 3, true, true, 4, false]
 *   Explanation
 *   Vector2D vector2D = new Vector2D([[1, 2], [3], [4]]);
 *   vector2D.next();    // return 1
 *   vector2D.next();    // return 2
 *   vector2D.next();    // return 3
 *   vector2D.hasNext(); // return True
 *   vector2D.hasNext(); // return True
 *   vector2D.next();    // return 4
 *   vector2D.hasNext(); // return False
 * Constraints:
 *   0 <= vec.length <= 200
 *   0 <= vec[i].length <= 500
 *   -500 <= vec[i][j] <= 500
 *   At most 10^5 calls will be made to next and hasNext.
 * Follow up: As an added challenge, try to code it using only iterators in C++ or iterators in Java.
 */
public class LC0251 {
    class Vector2D {
        int[][] v;
        int col;
        int row;

        public Vector2D(int[][] v) {
            this.v = v;
            col = 0;
            row = 0;

            while (row < v.length && v[row].length == 0) {
                row++;
            }
        }

        public int next() {
            if (!hasNext()) return -1;

            int result = v[row][col];

            col++;
            while (row < v.length && col == v[row].length) {
                col = 0;
                row++;
            }

            return result;
        }

        public boolean hasNext() {
            return v != null && v.length != 0 && row < v.length && v[row].length != 0;
        }
    }
}
