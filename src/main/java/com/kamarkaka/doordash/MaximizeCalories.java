package com.kamarkaka.doordash;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MaximizeCalories {
   public Integer maximumMenuCalories(List<Integer> calories, List<Double> prices, Double budget) {
      int[] dp = new int[(int) (budget * 100) + 1];
      dp[0] = 0;

      Queue<Integer> queue = new LinkedList<>();
      for (int i = 0; i < prices.size(); i++) {
         int calorie = calories.get(i);
         int price = (int) (prices.get(i) * 100);
         if (price > (int) (budget * 100)) continue;
         dp[price] = Math.max(dp[price], calorie);
         queue.add(price);
      }

      while (!queue.isEmpty()) {
         int price = queue.poll();
         for (int i = 0; i < prices.size(); i++) {
            int calorie2 = calories.get(i);
            int price2 = (int) (prices.get(i) * 100);
            if (price + price2 > (int) (budget * 100)) continue;
            dp[price + price2] = Math.max(dp[price + price2], dp[price] + calorie2);
            queue.add(price + price2);
         }
      }

      for (int i = (int) (budget * 100); i >= 0; i--) {
         if (dp[i] == 0) continue;
         return dp[i];
      }

      return -1;
   }

   public static void run() {
      MaximizeCalories sol = new MaximizeCalories();
      List<Integer> calories;
      List<Double> prices;
      Double budget;
      Integer max;

      // TEST CASE 1
      calories = Arrays.asList(200, 50);
      prices = Arrays.asList(20.0, 10.0);
      budget = 31.00;
      // maximum item inidices: 0 once and 1 once.
      max = 250;
      if (sol.maximumMenuCalories(calories, prices, budget).equals(max)) {
         System.out.println("Passed TEST CASE 1");
      } else {
         System.out.println("Failed TEST CASE 1");
      }

      // TEST CASE 2
      calories = Arrays.asList(200, 100);
      prices = Arrays.asList(51.00, 10.00);
      budget = 100.00;
      // maximum item inidices: 1 ten times.
      max = 1000;
      if (sol.maximumMenuCalories(calories, prices, budget).equals(max)) {
         System.out.println("Passed TEST CASE 2");
      } else {
         System.out.println("Failed TEST CASE 2");
      }

      // TEST CASE 3
      calories = Arrays.asList(600, 100);
      prices = Arrays.asList(51.00, 10.00);
      budget = 100.00;
      // maximum item inidices: 0 once, 1 four times.
      max = 1000;
      if (sol.maximumMenuCalories(calories, prices, budget).equals(max)) {
         System.out.println("Passed TEST CASE 3");
      } else {
         System.out.println("Failed TEST CASE 3");
      }

      // TEST CASE 4
      calories = Arrays.asList(250, 200, 50);
      prices = Arrays.asList(20.00, 15.00, 10.00);
      budget = 80.00;
      // maximum item inidices: 0 once, 1 four times.
      max = 1050;
      if (sol.maximumMenuCalories(calories, prices, budget).equals(max)) {
         System.out.println("Passed TEST CASE 4");
      } else {
         System.out.println("Failed TEST CASE 4");
      }

      // TEST CASE 5
      calories = Arrays.asList(600, 100);
      prices = Arrays.asList(120.00, 130.00);
      budget = 100.00;
      // Can't buy anything with this budget.
      max = 0;
      if (sol.maximumMenuCalories(calories, prices, budget).equals(max)) {
         System.out.println("Passed TEST CASE 5");
      } else {
         System.out.println("Failed TEST CASE 5");
      }

      // TEST CASE 6
      calories = Arrays.asList(600, 250, 200);
      prices = Arrays.asList(6.00, 4.00, 3.00);
      budget = 10.00;
      // maximum item inidices: 0 once, 1 once.
      max = 850;
      if (sol.maximumMenuCalories(calories, prices, budget).equals(max)) {
         System.out.println("Passed TEST CASE 6");
      } else {
         System.out.println("Failed TEST CASE 6");
      }

      // TEST CASE 7
      calories = Arrays.asList(100);
      prices = Arrays.asList(1.20);
      budget = 6.00;
      // maximum item inidices: 0 five times.
      max = 500;
      if (sol.maximumMenuCalories(calories, prices, budget).equals(max)) {
         System.out.println("Passed TEST CASE 7");
      } else {
         System.out.println("Failed TEST CASE 7");
      }
   }
}
