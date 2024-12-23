package com.kamarkaka.marshallwace;

/***
 * ...
 * These operations are performed until the value of V becomes 0.
 *
 * For example, if string S = "011100", its value V initially is 28. The value of V would change as follows:
 * - V = 28, which is even: divide by 2 to obtain 14;
 * - V = 14, which is even: divide by 2 to obtain 7;
 * - V = 7, which is odd: subtract 1 to obtain 6;
 * - V = 6, which is even: divide by 2 to obtain 3;
 * - V = 3, which is odd: subtract 1 to obtain 2;
 * - V = 2, which is even: divide by 2 to obtain 1;
 * - V = 1, which is odd: subtract 1 to obtain 0;
 * Seven operations were required to reduce the value of V to 0.
 * Write a function that, given a string S consisting of N characters containing a binary representation of the initial
 * value V, returns the number of operations after which its value will become 0.
 *
 * Examples:
 * 1. Given S = "011100", the function should return 7. String S represents the number 28, which becomes 0 after seven
 * operations, as explained above.
 * 2. Given S = "111", the function should return 5.
 * 2. Given S = "1111010101111", the function should return 22.
 * 4. Given string S consisting of "1" repeated 400,000 times, the function should return 799,999.
 *
 * Write an efficient algorithm for the following assumptions:
 * - string S consists only of the characters '0' and/or '1';
 * - N, which is the length of string S, is an integer within the range [1..1,000,000];
 * - the binary representation is big-endian, i.e., the first character of string S corresponds to the most significant
 * bit;
 * - the binary representation may contain leading zeros.
 *
 */
public class AvalancheNumber {
    public int numberOfOperations(String S) {
        int result = 0;

        char[] bits = S.toCharArray();
        int MSBIndex = 0;
        int LSBIndex = bits.length - 1;

        while (bits[MSBIndex] == '0' && MSBIndex < LSBIndex) {
            MSBIndex++;
        }

        while (MSBIndex < LSBIndex) {
            if (bits[LSBIndex] == '1') {
                bits[LSBIndex] = '0';
            } else {
                LSBIndex--;
            }
            result++;
        }

        return result + 1;
    }

    public static void main(String[] args) {
        AvalancheNumber solution = new AvalancheNumber();
        System.out.println(solution.numberOfOperations("1"));
        System.out.println(solution.numberOfOperations("011100"));
        System.out.println(solution.numberOfOperations("111"));
        System.out.println(solution.numberOfOperations("000001111010101111"));
        System.out.println(solution.numberOfOperations("1".repeat(400_000)));
    }
}
