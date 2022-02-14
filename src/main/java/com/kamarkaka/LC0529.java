package main.java.com.kamarkaka;

import java.util.LinkedList;
import java.util.Queue;

/***
 * 529. Minesweeper
 * Let's play the minesweeper game (Wikipedia, online game)!
 * You are given an m x n char matrix board representing the game board where:
 *    'M' represents an unrevealed mine,
 *    'E' represents an unrevealed empty square,
 *    'B' represents a revealed blank square that has no adjacent mines (i.e., above, below, left, right, and all 4 diagonals),
 *    digit ('1' to '8') represents how many mines are adjacent to this revealed square, and
 *    'X' represents a revealed mine.
 * You are also given an integer array click where click = [clickr, clickc] represents the next click position among all the unrevealed squares ('M' or 'E').
 * Return the board after revealing this position according to the following rules:
 *    If a mine 'M' is revealed, then the game is over. You should change it to 'X'.
 *    If an empty square 'E' with no adjacent mines is revealed, then change it to a revealed blank 'B' and all of its adjacent unrevealed squares should be revealed recursively.
 *    If an empty square 'E' with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
 *    Return the board when no more squares will be revealed.
 *
 * Example 1:
 *    Input: board = [["E","E","E","E","E"],["E","E","M","E","E"],["E","E","E","E","E"],["E","E","E","E","E"]], click = [3,0]
 *    Output: [["B","1","E","1","B"],["B","1","M","1","B"],["B","1","1","1","B"],["B","B","B","B","B"]]
 *
 * Example 2:
 *    Input: board = [["B","1","E","1","B"],["B","1","M","1","B"],["B","1","1","1","B"],["B","B","B","B","B"]], click = [1,2]
 *    Output: [["B","1","E","1","B"],["B","1","X","1","B"],["B","1","1","1","B"],["B","B","B","B","B"]]
 *
 * Constraints:
 *    m == board.length
 *    n == board[i].length
 *    1 <= m, n <= 50
 *    board[i][j] is either 'M', 'E', 'B', or a digit from '1' to '8'.
 *    click.length == 2
 *    0 <= clickr < m
 *    0 <= clickc < n
 *    board[clickr][clickc] is either 'M' or 'E'.
 */
public class LC0529 {
   private final static int[][] DIRS = {{1,0},{1,1},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1}};
   public char[][] updateBoard(char[][] board, int[] click) {
      int y = click[0], x = click[1];
      if (board[y][x] == 'M') {
         board[y][x] = 'X';
         return board;
      }

      Queue<int[]> queue = new LinkedList<>();
      queue.add(click);

      while (!queue.isEmpty()) {
         int[] position = queue.poll();
         if (board[position[0]][position[1]] == 'E' || (board[position[0]][position[1]] >= '1' && board[position[0]][position[1]] <= '8')) continue;

         int mineCount = getMineCount(board, position);
         if (mineCount == 0) {
            board[position[0]][position[1]] = 'B';
            for (int[] dir : DIRS) {
               y = position[0] + dir[1];
               x = position[1] + dir[0];
               if (y < 0 || y >= board.length) continue;
               if (x < 0 || x >= board[0].length) continue;
               queue.add(new int[] {y, x});
            }
         } else {
            board[position[0]][position[1]] = (char) ('0' + mineCount);
         }
      }

      return board;
   }

   private int getMineCount(char[][] board, int[] position) {
      int count = 0;
      for (int[] dir : DIRS) {
         int x = position[1] + dir[0];
         int y = position[0] + dir[1];

         if (y < 0 || y >= board.length) continue;
         if (x < 0 || x >= board[0].length) continue;

         if (board[y][x] == 'M') count++;
      }
      return count;
   }

}
