package com.kamarkaka.twosigma;

import java.util.Comparator;
import java.util.PriorityQueue;

public class StockMatching {
   private final PriorityQueue<Order> buyOrders;
   private final PriorityQueue<Order> sellOrders;

   public StockMatching() {
      this.buyOrders = new PriorityQueue<>(Comparator.comparingInt(o -> -o.price));
      this.sellOrders = new PriorityQueue<>(Comparator.comparingInt(o -> o.price));
   }

   public void addOrders(String[] orders) {

   }

   private class Order {
      String type;
      int price;

      public Order(String type, int price) {
         this.type = type;
         this.price = price;
      }
   }
}
