package com.kamarkaka;

import java.util.*;

/***
 * 488. Zuma Game
 * You are playing a variation of the game Zuma.
 * In this variation of Zuma, there is a single row of colored balls on a board, where each ball can be colored red 'R', yellow 'Y', blue 'B', green 'G', or white 'W'. You also have several colored balls in your hand.
 * Your goal is to clear all of the balls from the board. On each turn:
 *   Pick any ball from your hand and insert it in between two balls in the row or on either end of the row.
 *   If there is a group of three or more consecutive balls of the same color, remove the group of balls from the board.
 *     If this removal causes more groups of three or more of the same color to form, then continue removing each group until there are none left.
 *   If there are no more balls on the board, then you win the game.
 *   Repeat this process until you either win or do not have any more balls in your hand.
 * Given a string board, representing the row of balls on the board, and a string hand, representing the balls in your hand, return the minimum number of balls you have to insert to clear all the balls from the board. If you cannot clear all the balls from the board using the balls in your hand, return -1.
 *
 * Example 1:
 *   Input: board = "WRRBBW", hand = "RB"
 *   Output: -1
 *   Explanation: It is impossible to clear all the balls. The best you can do is:
 *   - Insert 'R' so the board becomes WRRRBBW. WRRRBBW -> WBBW.
 *   - Insert 'B' so the board becomes WBBBW. WBBBW -> WW.
 *   There are still balls remaining on the board, and you are out of balls to insert.
 *
 * Example 2:
 *   Input: board = "WWRRBBWW", hand = "WRBRW"
 *   Output: 2
 *   Explanation: To make the board empty:
 *   - Insert 'R' so the board becomes WWRRRBBWW. WWRRRBBWW -> WWBBWW.
 *   - Insert 'B' so the board becomes WWBBBWW. WWBBBWW -> WWWW -> empty.
 *   2 balls from your hand were needed to clear the board.
 *
 * Example 3:
 *   Input: board = "G", hand = "GGGGG"
 *   Output: 2
 *   Explanation: To make the board empty:
 *   - Insert 'G' so the board becomes GG.
 *   - Insert 'G' so the board becomes GGG. GGG -> empty.
 *   2 balls from your hand were needed to clear the board.
 *
 * Constraints:
 *   1 <= board.length <= 16
 *   1 <= hand.length <= 5
 *   board and hand consist of the characters 'R', 'Y', 'B', 'G', and 'W'.
 *   The initial row of balls on the board will not have any groups of three or more consecutive balls of the same color.
 */
public class LC0488 {
   public int findMinStep(String board, String hand) {
      char[] arr = hand.toCharArray();
      Arrays.sort(arr);
      hand = new String(arr);

      // 初始化用队列维护的状态队列：其中的三个元素分别为桌面球状态、手中球状态和回合数
      Queue<State> queue = new ArrayDeque<>();
      queue.offer(new State(board, hand, 0));

      // 初始化用哈希集合维护的已访问过的状态
      Set<String> visited = new HashSet<>();
      visited.add(board + " " + hand);

      while (!queue.isEmpty()) {
         State state = queue.poll();
         String curBoard = state.board;
         String curHand = state.hand;
         int step = state.step;

         for (int i = 0; i < curBoard.length(); i++) {
            for (int j = 0; j < curHand.length(); j++) {
               // 第 1 个剪枝条件: 当前球的颜色和上一个球的颜色相同
               if (j > 0 && curHand.charAt(j) == curHand.charAt(j - 1)) {
                  continue;
               }

               // 第 2 个剪枝条件: 只在连续相同颜色的球的开头位置插入新球
               if (i > 0 && curBoard.charAt(i - 1) == curHand.charAt(j)) {
                  continue;
               }

               // 第 3 个剪枝条件: 只在以下两种情况放置新球
               boolean choose = false;
               //  - 第 1 种情况 : 当前球颜色与后面的球的颜色相同
               if (curBoard.charAt(i) == curHand.charAt(j)) {
                  choose = true;
               }
               //  - 第 2 种情况 : 当前后颜色相同且与当前颜色不同时候放置球
               if (i > 0 && curBoard.charAt(i - 1) == curBoard.charAt(i) && curBoard.charAt(i - 1) != curHand.charAt(j)) {
                  choose = true;
               }

               if (choose) {
                  String newBoard = updateBoard(curBoard.substring(0, i) + curHand.charAt(j) + curBoard.substring(i), i);
                  String newHand = curHand.substring(0, j) + curHand.substring(j + 1);
                  if (newBoard.length() == 0) {
                     return step + 1;
                  }
                  String str = newBoard + " " + newHand;
                  if (visited.add(str)) {
                     queue.offer(new State(newBoard, newHand, step + 1));
                  }
               }
            }
         }
      }
      return -1;
   }

   public String updateBoard(String board, int index) {
      if (board.length() <= 0) {
         return board;
      }
      int left = index;
      int right = index;
      while (left >= 0 && right < board.length()) {
         while (left >0 && board.charAt(left) == board.charAt(left - 1)) {
            left--;
         }
         while (right < board.length() - 1 && board.charAt(right) == board.charAt(right + 1)) {
            right++;
         }
         if (right - left + 1 >= 3) {
            return updateBoard(board.substring(0, left) + board.substring(right + 1), left -1);
         } else {
            return board;
         }
      }
      return board;
   }

   class State {
      String board;
      String hand;
      int step;

      public State(String board, String hand, int step) {
         this.board = board;
         this.hand = hand;
         this.step = step;
      }
   }
}
