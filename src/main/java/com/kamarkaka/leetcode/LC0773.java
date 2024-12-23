package com.kamarkaka.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/***
 * 773. Sliding Puzzle
 * On an 2 x 3 board, there are five tiles labeled from 1 to 5, and an empty square represented by 0. A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
 * The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
 * Given the puzzle board board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.
 *
 * Example 1:
 *    Input: board = [[1,2,3],[4,0,5]]
 *    Output: 1
 *    Explanation: Swap the 0 and the 5 in one move.
 *
 * Example 2:
 *    Input: board = [[1,2,3],[5,4,0]]
 *    Output: -1
 *    Explanation: No number of moves will make the board solved.
 *
 * Example 3:
 *    Input: board = [[4,1,2],[5,0,3]]
 *    Output: 5
 *    Explanation: 5 is the smallest number of moves that solves the board.
 *    An example path:
 *    After move 0: [[4,1,2],[5,0,3]]
 *    After move 1: [[4,1,2],[0,5,3]]
 *    After move 2: [[0,1,2],[4,5,3]]
 *    After move 3: [[1,0,2],[4,5,3]]
 *    After move 4: [[1,2,0],[4,5,3]]
 *    After move 5: [[1,2,3],[4,5,0]]
 *
 * Constraints:
 *    board.length == 2
 *    board[i].length == 3
 *    0 <= board[i][j] <= 5
 *    Each value board[i][j] is unique.
 */
public class LC0773 {
   private final static int swap[][]={{1,3},{0,2,4},{1,5},{0,4},{1,3,5},{2,4}};

   public int slidingPuzzle(int[][] board) {
      String target = "123450";
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < board.length; i++) {
         for (int j = 0; j < board[0].length; j++) {
            sb.append(board[i][j]);
         }
      }
      String state = sb.toString();

      Set<String> visited = new HashSet<>();
      visited.add(state);

      Queue<Pair> queue = new LinkedList<>();
      queue.add(new Pair(state, 0));

      while (!queue.isEmpty()) {
         Pair curr = queue.poll();
         String s = curr.pattern;
         if (s.equals(target)) return curr.level;

         int idx = s.indexOf('0');
         for (int i = 0; i < swap[idx].length; i++) {
            String p = helper(s, swap[idx][i], idx);
            if (p.equals(target)) return curr.level + 1;

            if (visited.contains(p)) continue;
            visited.add(p);
            queue.add(new Pair(p, curr.level + 1));
         }
      }

      return -1;
   }

   private String helper(String str, int i, int j) {
      StringBuilder sb = new StringBuilder(str);
      sb.setCharAt(i, str.charAt(j));
      sb.setCharAt(j, str.charAt(i));
      return sb.toString();
   }

   private class Pair {
      String pattern;
      int level;

      public Pair(String pattern, int level) {
         this.pattern = pattern;
         this.level = level;
      }
   }
}
