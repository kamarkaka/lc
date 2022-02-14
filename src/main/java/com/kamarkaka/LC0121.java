package main.java.com.kamarkaka;

public class LC0121 {
   public int maxProfit(int[] prices) {
      int min = Integer.MAX_VALUE;
      int max = 0;

      for (int i = 0; i < prices.length; i++) {
         if (prices[i] < min)
            min = prices[i];
         else if (prices[i] - min > max)
            max = prices[i] - min;
      }

      return max;
   }

   public static void run() {
      LC0121 solution = new LC0121();
      System.out.println(solution.maxProfit(new int[] {7,1,5,3,6,4}));
      System.out.println(solution.maxProfit(new int[] {7,6,4,3,1}));
      System.out.println(solution.maxProfit(new int[] {5,4,-1,7,8}));
      System.out.println(solution.maxProfit(new int[] {-2,1}));
      System.out.println(solution.maxProfit(new int[] {-2,-1}));
   }
}
