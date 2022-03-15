package com.kamarkaka.twosigma;

import java.util.Comparator;
import java.util.PriorityQueue;

/***
 * Implement a stock exchange for a single stock. The exchange receives buy and sell orders one at a time. For each order received, the exchange identifies if any orders are compatible, and if so, the orders are "matched" and a transaction occurs. After receiving each order, return info about any matches in the order they occur.
 * An order has fields:
 *    - int id: unique id for the order
 *    - string side: "buy" or "sell"
 *    - int quantity: the requested quantity
 *    - int price: the offer price
 * Return ; separated matches (if there are multiple). For each match, format it as "id1,id2,quantity,price"
 *    - int id1: id of earlier order
 *    - int id2: id of later order
 *    - int quantity: the matching quantity to be exchanged
 *    - int price: the offer price of the earlier order
 * Matching rules
 *    - Only orders from opposite sides can be matched.&nbsp;Orders are compatible when the sell price is less than or equal to the buy price.
 *    - Orders with a better price get matched first. If two orders have the same price, the earlier order is matched first.
 *    - Orders can be partially matched. The remaining quantity stays on the exchange to be matched by later orders.
 */
public class StockExchange {
   PriorityQueue<Order> sellBacklog = new PriorityQueue<>(Comparator.comparingInt(a -> a.price));
   PriorityQueue<Order> buyBacklog = new PriorityQueue<>(Comparator.comparingInt(a -> -a.price));

   public String handleOrder(String orderStr) {
      StringBuilder sb = new StringBuilder();

      String[] parts = orderStr.split(",");
      int id = Integer.parseInt(parts[0]);
      String side = parts[1];
      int quanity = Integer.parseInt(parts[2]);
      int price = Integer.parseInt(parts[3]);

      Order order = new Order(id, side, quanity, price);
      if (order.side.equalsIgnoreCase("buy")) {
         // buy case
         while (!sellBacklog.isEmpty() && sellBacklog.peek().price <= order.price && order.quantity > 0) {
            Order sellOrder = sellBacklog.peek();

            // remove the min of these
            int toRemove = Math.min(order.quantity, sellOrder.quantity);
            sellOrder.quantity -= toRemove;
            order.quantity -= toRemove;

            if (sb.length() > 0) sb.append(';');
            sb.append(sellOrder.id).append(',')
               .append(order.id).append(',')
               .append(toRemove).append(',')
               .append(sellOrder.price);

            // remove from sell backlog if the amount is 0
            if(sellOrder.quantity == 0) {
               sellBacklog.poll();
            }
         }

         if(order.quantity > 0) {
            buyBacklog.add(order);
         }
      } else {
         // sell case
         while(!buyBacklog.isEmpty() && buyBacklog.peek().price >= order.price && order.quantity > 0) {
            Order buyOrder = buyBacklog.peek();

            // remove the min of these
            int toRemove = Math.min(order.quantity, buyOrder.quantity);
            buyOrder.quantity -= toRemove;
            order.quantity -= toRemove;

            if (sb.length() > 0) sb.append(';');
            sb.append(buyOrder.id).append(',')
                  .append(order.id).append(',')
                  .append(toRemove).append(',')
                  .append(order.price);

            // remove from buy backlog if the amount is 0
            if(buyOrder.quantity == 0) {
               buyBacklog.poll();
            }
         }

         if(order.quantity > 0) {
            sellBacklog.offer(order);
         }
      }

      return sb.toString();
   }

   private class Order {
      int id;
      String side;
      int quantity;
      int price;

      public Order(int id, String side, int quantity, int price) {
         this.id = id;
         this.side = side;
         this.quantity = quantity;
         this.price = price;
      }
   }
}
