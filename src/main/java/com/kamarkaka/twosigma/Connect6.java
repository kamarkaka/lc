package com.kamarkaka.twosigma;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Connect6 {
   private final Map<Point, Integer> board;
   private final int N;
   private int currPlayer;
   private final static int[][] dirs = new int[][] {{1,1},{0,1},{1,0}};

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
      int[] counts = new int[3];
      for (int i = -(N-1); i < N; i++) {
         for (int j = 0; j < 3; j++) {
            int[] dir = dirs[j];
            Point np = new Point(p.x + i * dir[0], p.y + i * dir[1]);

            if (board.containsKey(np) && board.get(np) == currPlayer) {
               counts[j]++;
            } else {
               counts[j] = 0;
            }
         }

         if (counts[0] == N || counts[1] == N || counts[2] == N) return true;
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
