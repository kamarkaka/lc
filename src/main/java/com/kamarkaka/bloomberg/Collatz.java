package com.kamarkaka.bloomberg;

public class Collatz {
   public void getSequence(int num) {
      System.out.print(num);

      while (num != 1) {
         num = nextNum(num);
         System.out.print(" " + num);
      }
   }

   private int nextNum(int n) {
      if (n % 2 == 0) {
         n /= 2;
      } else {
         n = 3 * n + 1;
      }
      return n;
   }

   public void getSequenceRecursion(int num) {
      System.out.print(" " + num);

      if (num == 1) return;

      if (num % 2 == 0) {
         getSequenceRecursion(num / 2);
      } else {
         getSequenceRecursion(3 * num + 1);
      }
   }

   public static void run() {
      Collatz sol = new Collatz();
      sol.getSequenceRecursion(101);
   }
}
