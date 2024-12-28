package com.kamarkaka.leetcode;

/***
 * 794. Valid Tic-Tac-Toe State
 * Given a Tic-Tac-Toe board as a string array board, return true if and only if it is possible to reach this board
 * position during the course of a valid tic-tac-toe game.
 * The board is a 3 x 3 array that consists of characters ' ', 'X', and 'O'. The ' ' character represents an empty
 * square.
 * Here are the rules of Tic-Tac-Toe:
 *   Players take turns placing characters into empty squares ' '.
 *   The first player always places 'X' characters, while the second player always places 'O' characters.
 *   'X' and 'O' characters are always placed into empty squares, never filled ones.
 *   The game ends when there are three of the same (non-empty) character filling any row, column, or diagonal.
 *   The game also ends if all squares are non-empty.
 *   No more moves can be played if the game is over.
 * Example 1:
 *   Input: board = ["O  ","   ","   "]
 *   Output: false
 *   Explanation: The first player always plays "X".
 * Example 2:
 *   Input: board = ["XOX"," X ","   "]
 *   Output: false
 *   Explanation: Players take turns making moves.
 * Example 3:
 *   Input: board = ["XOX","O O","XOX"]
 *   Output: true
 * Constraints:
 *   board.length == 3
 *   board[i].length == 3
 *   board[i][j] is either 'X', 'O', or ' '.
 */
public class LC0794 {
    public boolean validTicTacToe(String[] board) {
        int p1Moves = 0, p2Moves = 0;
        boolean p1Wins = false, p2Wins = false;
        int[] p1col = new int[3], p2col = new int[3];
        int[] p1d = new int[2], p2d = new int[2];

        for (int i = 0; i < 3; i++) {
            String row = board[i];
            int p1row = 0, p2row = 0;
            for (int j = 0; j < 3; j++) {
                char c = row.charAt(j);

                if (c == 'X') {
                    p1Moves++; p1row++; p1col[j]++;

                    if (i == j) p1d[0]++;
                    if (i + j == 2) p1d[1]++;
                } else if (c == 'O') {
                    p2Moves++; p2row++; p2col[j]++;

                    if (i == j) p2d[0]++;
                    if (i + j == 2) p2d[1]++;
                }
            }

            if (p1row == 3) p1Wins = true;
            if (p2row == 3) p2Wins = true;
        }

        for (int i = 0; i < 3; i++) {
            if (p1col[i] == 3) p1Wins = true;
            if (p2col[i] == 3) p2Wins = true;
        }

        for (int i = 0; i < 2; i++) {
            if (p1d[i] == 3) p1Wins = true;
            if (p2d[i] == 3) p2Wins = true;
        }

        if (p2Moves > p1Moves || p1Moves - p2Moves > 1) return false;
        if (p1Wins && p1Moves - p2Moves != 1) return false;
        if (p2Wins && p1Moves != p2Moves) return false;
        return true;
    }
}
