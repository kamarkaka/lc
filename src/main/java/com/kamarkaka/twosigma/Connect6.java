package com.kamarkaka.twosigma;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Connect6 {
   private final Map<Point, Integer> board;
   private final int N;
   private int currPlayer;

   public Connect6(int N) {
      this.board = new HashMap<>();
      this.currPlayer = 0;
      this.N = N;
   }

   public void reset() {
      board.clear();
      currPlayer = 0;
   }

   public int placePiece(int player, int x, int y) {
      if (player != currPlayer) return -1;

      Point p = new Point(x, y);
      if (board.containsKey(p)) return -1;

      board.put(p, currPlayer);
      if (win(p)) return 1;

      currPlayer = (currPlayer + 1) % 2;
      return 0;
   }

   public boolean win(Point p) {
      int dCount = 0, vCount = 0, hCount = 0;
      for (int i = -(N-1); i < N; i++) {
         Point dp = new Point(p.x + i, p.y + i);
         Point vp = new Point(p.x, p.y + i);
         Point hp = new Point(p.x + i, p.y);
         if (board.containsKey(dp) && board.get(dp) == currPlayer) {
            dCount++;
         } else {
            dCount = 0;
         }

         if (board.containsKey(vp) && board.get(vp) == currPlayer) {
            vCount++;
         } else {
            vCount = 0;
         }

         if (board.containsKey(hp) && board.get(hp) == currPlayer) {
            hCount++;
         } else {
            hCount = 0;
         }

         if (dCount == N || vCount == N || hCount == N) return true;
      }

      return false;
   }

   private class Point {
      int x;
      int y;

      Point(int x, int y) {
         this.x = x;
         this.y = y;
      }

      @Override
      public boolean equals(Object o) {
         if (this == o) return true;
         if (o == null || getClass() != o.getClass()) return false;
         Point point = (Point) o;
         return x == point.x && y == point.y;
      }

      @Override
      public int hashCode() {
         return Objects.hash(x, y);
      }
   }
}
