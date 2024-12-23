package com.kamarkaka.marshallwace;

import com.kamarkaka.common.Utilities;

/***
 * Write a function that, given an integer N (1 <= N <= 100), returns an array containing N unique integers that sum up
 * to 0. The function can return any such array.
 *
 * For example, given N = 4, the function should return [1, 0, -3, -2] or [-2, 1, -4, 5]. The answer [1, -1, 1, 3] would
 * be incorrect (because value 1 occurs twice). For N = 3 one of the possible answers is [-1, 0, 1] (but there are many
 * more correct answers).
 */
public class NUniqueIntegers {
    public int[] output(int N) {
        int[] result = new int[N];

        for (int i = 0, n = 1; n <= N / 2; n++) {
            result[i++] = n;
            result[i++] = -n;
        }

        if (N % 2 == 1) {
            result[N - 1] = 0;
        }

        return result;
    }

    public static void main(String[] args) {
        NUniqueIntegers solution = new NUniqueIntegers();
        Utilities.print(solution.output(4));
        Utilities.print(solution.output(3));
        Utilities.print(solution.output(1));
        Utilities.print(solution.output(100));
    }
}
