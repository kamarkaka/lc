package com.kamarkaka.marshallwace;

import java.util.Arrays;

/***
 * There is a board with 2 rows and N columns, represented by a matrix M. Rows are numbered from 0 to 1 from top to
 * bottom and columns are numbered from 0 to N-1 from left to right. Each cell contains either a 0 or a 1. You know
 * that:
 * - the sum of integers in the 0-th (upper) row is equal to U,
 * - the sum of integers in the 1-st (lower) row is equal to L,
 * - the sum of integers in the K-th column is equal to C[K].
 * Your job is to recover M based on this information. Write a function that, given two integers U, L and an array C of
 * N integers, as described above, returns a string describing the matrix M in the following format. The first part of
 * the string should be the description of the upper row (N characters: 0 or 1), then there should be a comma (,), and
 * finally there should be the description of the lower row (N characters: 0 or 1). The output string should not contain
 * any whitespace.
 * If there exists multiple valid Ms, your function may return any one of them. If no valid M exists, your function
 * should return the word "IMPOSSIBLE".
 *
 * Examples:
 * 1. Given U = 3, L = 2, C = [2,1,1,0,1], your function may, for example, return "11001,10100".
 * 2. Given U = 2, L = 3, C = [0,0,1,1,2], your function should return the word IMPOSSIBLE.
 * 3. Given U = 2, L = 2, C = [2,0,2,0], your function should return "1010,1010".
 *
 * Write an efficient algorithm for the following assumptions:
 * - U and L are integers within the range [0..100,000];
 * - N is an integer within the range [1..100,000];
 * - each element of array C is an integer within the range [0..2].
 */
public class RecoverM {
    public String recover(int U, int L, int[] C) {
        int c = C.length;
        int[][] matrix = new int[2][c];
        Arrays.fill(matrix[0], -1);
        Arrays.fill(matrix[1], -1);

        for (int i = 0; i < c; i++) {
            if (C[i] == 0) {
                matrix[0][i] = 0;
                matrix[1][i] = 0;
            } else if (C[i] == 2) {
                if (U > 0 && L > 0) {
                    matrix[0][i] = 1;
                    matrix[1][i] = 1;

                    U--;
                    L--;
                } else {
                    return "IMPOSSIBLE";
                }
            } else if (C[i] == 1) {
                if (U > 0) {
                    matrix[0][i] = 1;
                    matrix[1][i] = 0;
                    U--;
                } else if (L > 0) {
                    matrix[0][i] = 0;
                    matrix[1][i] = 1;
                    L--;
                } else {
                    return "IMPOSSIBLE";
                }
            }
        }

        if (U != 0 || L != 0) {
            return "IMPOSSIBLE";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < c; i++) {
            sb.append(matrix[0][i]);
        }
        sb.append(',');
        for (int i = 0; i < c; i++) {
            sb.append(matrix[1][i]);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        RecoverM solution = new RecoverM();
        System.out.println(solution.recover(3, 2, new int[]{2,1,1,0,1}));
        System.out.println(solution.recover(2, 3, new int[]{0,0,1,1,2}));
        System.out.println(solution.recover(2, 2, new int[]{2,0,2,0}));
        System.out.println(solution.recover(4, 3, new int[]{2,0,2,0}));
    }
}
