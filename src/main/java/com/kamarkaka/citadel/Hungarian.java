package com.kamarkaka.citadel;

public class Hungarian {
   private int n;
   private int[][] weight;
   private int[] x;
   private int[] y;
   private int[] xy;
   private int[] yx;

   public Hungarian(int[][] weight) {
      this.n = weight.length;
      this.weight = new int[n][n];
      for (int i = 0; i < n; i++) {
         for (int j = 0; j < n; j++) {
            if (weight[i][j] < 0) throw new IllegalArgumentException("weight must be non-negative");
            this.weight[i][j] = weight[i][j];
         }
      }

      this.x = new int[n];
      this.y = new int[n];
      this.xy = new int[n];
      this.yx = new int[n];
      for (int i = 0; i < n; i++) { xy[i] = -1; }
      for (int i = 0; i < n; i++) { yx[i] = -1; }

      while (true) {

      }
   }
}
