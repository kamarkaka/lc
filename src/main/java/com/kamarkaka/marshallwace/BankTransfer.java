package com.kamarkaka.marshallwace;

import com.kamarkaka.common.Utilities;

/***
 * You are given a list of N transfers (numbered from 0 to N-1) between two banks: bank A and bank B. The K-th transfer
 * is described by two values:
 * - R[K] (either "A" or "B") representing the recipient (the bank the transfer is sent to);
 * - V[K] denoting the value sent via the transfer.
 * All transfers are completed in the order they appear on the list. The banks do not want to go into debt (in other
 * words, their account balance may not drop below 0). What minimum initial account balance in each bank is necessary in
 * order to complete the transfers?
 * Write a function that, given a string R and an array of integers V, both of length N, returns an array of two
 * integers. The integers should represent the minimum initial account balance for banks A and B in the following order:
 * [bank A, bank B].
 * Result array should be returned as an array of integers.
 *
 * Examples:
 * 1. Given R = "BAABA" and V = {2,4,1,1,2}, the function should return [2,4].
 * 2. Given R = "ABAB" and V = {10,5,10,15}, the function should return [0,15].
 * 3. Given R = "B" and V = {100}, the function should return [100,0].
 * Write an efficient algorithm for the following assumptions:
 * - string R and array V are both of length N;
 * - N is an integer within the range [1..100,000];
 * - each element of array V is an integer within the range [1..100,000];
 * - string R consists only of the characters "A" and/or "B".
 */
public class BankTransfer {
    public int[] minBalance(String R, int[] V) {
        int[] minBalance = new int[]{0, 0};
        int[] curBalance = new int[]{0, 0};

        for (int i = 0; i < R.length(); i++) {
            if (R.charAt(i) == 'A') {
                curBalance[0] += V[i];
                curBalance[1] -= V[i];

                if (curBalance[1] < 0) {
                    minBalance[1] -= curBalance[1];
                    curBalance[1] = 0;
                }
            } else {
                curBalance[0] -= V[i];
                curBalance[1] += V[i];

                if (curBalance[0] < 0) {
                    minBalance[0] -= curBalance[0];
                    curBalance[0] = 0;
                }
            }
        }

        return minBalance;
    }

    public static void main(String[] args) {
        BankTransfer solution = new BankTransfer();
        Utilities.print(solution.minBalance("BAABA", new int[]{2,4,1,1,2}));
        Utilities.print(solution.minBalance("ABAB", new int[]{10,5,10,15}));
        Utilities.print(solution.minBalance("B", new int[]{100}));
    }
}
