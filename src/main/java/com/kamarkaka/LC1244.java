package main.java.com.kamarkaka;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/***
 * Design a Leaderboard class, which has 3 functions:
 *   addScore(playerId, score): Update the leaderboard by adding score to the given player's score. If there is no player with such id in the leaderboard, add him to the leaderboard with the given score.
 *   top(K): Return the score sum of the top K players.
 *   reset(playerId): Reset the score of the player with the given id to 0 (in other words erase it from the leaderboard). It is guaranteed that the player was added to the leaderboard before calling this function.
 * Initially, the leaderboard is empty.
 *
 * Example 1:
 *   Input:
 *     ["Leaderboard","addScore","addScore","addScore","addScore","addScore","top","reset","reset","addScore","top"]
 *     [[],[1,73],[2,56],[3,39],[4,51],[5,4],[1],[1],[2],[2,51],[3]]
 *   Output:
 *     [null,null,null,null,null,null,73,null,null,null,141]
 *   Explanation:
 *     Leaderboard leaderboard = new Leaderboard ();
 *     leaderboard.addScore(1,73);   // leaderboard = [[1,73]];
 *     leaderboard.addScore(2,56);   // leaderboard = [[1,73],[2,56]];
 *     leaderboard.addScore(3,39);   // leaderboard = [[1,73],[2,56],[3,39]];
 *     leaderboard.addScore(4,51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
 *     leaderboard.addScore(5,4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
 *     leaderboard.top(1);           // returns 73;
 *     leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
 *     leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
 *     leaderboard.addScore(2,51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
 *     leaderboard.top(3);           // returns 141 = 51 + 51 + 39;
 *
 * Constraints:
 *   1 <= playerId, K <= 10000
 *   It's guaranteed that K is less than or equal to the current number of players.
 *   1 <= score <= 100
 *   There will be at most 1000 function calls.
 */
public class LC1244 {
   class Leaderboard {

      Map<Integer, Integer> scores;
      TreeMap<Integer, Integer> sortedScores;

      public Leaderboard() {
         this.scores = new HashMap<>();
         this.sortedScores = new TreeMap<>(Collections.reverseOrder());
      }

      public void addScore(int playerId, int score) {

         // The scores dictionary simply contains the mapping from the
         // playerId to their score. The sortedScores contain a BST with
         // key as the score and value as the number of players that have
         // that score.
         if (!scores.containsKey(playerId)) {
            scores.put(playerId, score);
            sortedScores.put(score, this.sortedScores.getOrDefault(score, 0) + 1);
         } else {

            // Since the current player's score is changing, we need to
            // update the sortedScores map to reduce count for the old
            // score.
            int preScore = this.scores.get(playerId);
            int playerCount = this.sortedScores.get(preScore);

            // If no player has this score, remov it from the tree.
            if (playerCount == 1) {
               this.sortedScores.remove(preScore);
            } else {
               this.sortedScores.put(preScore, playerCount - 1);
            }

            // Updated score
            int newScore = preScore + score;
            this.scores.put(playerId, newScore);
            this.sortedScores.put(newScore, this.sortedScores.getOrDefault(newScore, 0) + 1);
         }
      }

      public int top(int K) {
         int count = 0;
         int sum = 0;

         // In-order traversal over the scores in the TreeMap
         for (Map.Entry<Integer, Integer> entry: this.sortedScores.entrySet()) {

            // Number of players that have this score.
            int times = entry.getValue();
            int key = entry.getKey();

            for (int i = 0; i < times; i++) {
               sum += key;
               count++;

               // Found top-K scores, break.
               if (count == K) {
                  break;
               }
            }

            // Found top-K scores, break.
            if (count == K) {
               break;
            }
         }

         return sum;
      }

      public void reset(int playerId) {
         int score = this.scores.get(playerId);
         this.sortedScores.put(score, this.sortedScores.get(score) - 1);
         if (this.sortedScores.get(score) == 0) {
            this.sortedScores.remove(score);
         }

         this.scores.remove(playerId);
      }
   }
}
