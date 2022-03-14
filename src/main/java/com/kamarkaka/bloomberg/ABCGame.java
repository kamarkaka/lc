package com.kamarkaka.bloomberg;

import java.util.Arrays;
import java.util.Set;

public class ABCGame {
   public char whoWins(int k) {
      int N = 3;
      boolean[] players = new boolean[N];
      Arrays.fill(players, true);

      while (N > 0) {
         int count = 0, i = 0;
         while (count < k) {
            if (players[i]) {
               System.out.print((char) ('A' + i));
               count++;

               if (count == k) {
                  System.out.println();
                  System.out.println("player " + ((char) ('A' + i)) + " out");
                  players[i] = false;
                  N--;
                  break;
               }
            }
            i++;
            if (i == players.length) i = 0;
         }

         if (N == 1) {
            for (i = 0; i < players.length; i++) {
               if (players[i]) return ((char) ('A' + i));
            }
         }
      }

      return 'X';
   }

   public static void run() {
      ABCGame sol = new ABCGame();
      System.out.println(sol.whoWins(4));
   }
}
