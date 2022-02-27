package com.kamarkaka.twosigma;

import java.util.TreeMap;

/***
 * A cloud service provider offers quantity discounts based on the number of virtual machines a customer needs.
 * Their offering vary from 2 to 2000 instances.
 * They maintain a database of past pricing in the format of (quantity, price_per_instance) tuples.
 * When pricing is requested by a new customer, the customer representative refers to the past quantity/price_per_instance tuples in the database and determines the per-instance price for the customer.
 *
 * The method for determining the per-instance price is as follows:
 *    Sometimes, price quotes lapse. when that happens, the old price is overwritten with either a 0 or a negative number.
 *    The tuple with zero or negative unit prices must be disregarded.
 *    If the database has only 1 valid tuple, then the price of the tuple is used.
 *    If the number of instances needed is exactly the same as the quantity of a tuple, the price of that tuple is used.
 *    If there is a price for a larger number and a price for a smaller number of instances, linearly interpolate the price of the quantity needed from the unit prices for the closest smaller and larger quantities.
 *    If the quantities of the past data are all smaller or all larger than the amount needed, then linearly extrapolate the unit price from the 2 points closest to the quantity needed.
 */
public class QuantityDiscount {
   TreeMap<Double, Double> priceMap;

   public double findPrice(double[][] tuples, int qty) {
      priceMap = new TreeMap<>();

      for (double[] tuple : tuples) {
         double quantity = tuple[0];
         double pricePerInstance = tuple[1];

         if (pricePerInstance > 0) {
            priceMap.put(quantity, pricePerInstance);
         }
      }

      if (priceMap.isEmpty()) return -1;
      if (priceMap.size() == 1) {
         double price = priceMap.get(priceMap.firstKey());
         return price;
      }

      if (priceMap.containsKey((double)qty)) {
         return priceMap.get((double)qty);
      }

      Double q1 = priceMap.lowerKey((double) qty);
      Double q2 = priceMap.higherKey((double) qty);
      if (q1 == null) {
         q1 = priceMap.firstKey();
         q2 = priceMap.higherKey(q1);
      } else if (q2 == null) {
         q2 = priceMap.lastKey();
         q1 = priceMap.lowerKey(q2);
      }

      return interpolate(q1, q2, qty);
   }

   private double interpolate(double q1, double q2, double qty) {
      double p1 = priceMap.get(q1);
      double p2 = priceMap.get(q2);

      double a = (p2 - p1) / (q2 - q1);
      double b = p1 - a * q1;

      return a * qty + b;
   }

   public static void run() {
      QuantityDiscount sol = new QuantityDiscount();
      System.out.println(sol.findPrice(new double[][] {{50,100},{60,90}}, 40));
      System.out.println(sol.findPrice(new double[][] {{50,100},{60,90}}, 55));
      System.out.println(sol.findPrice(new double[][] {{50,100},{60,90}}, 70));
   }
}
