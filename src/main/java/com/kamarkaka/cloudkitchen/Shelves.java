package com.kamarkaka.cloudkitchen;

import com.kamarkaka.cloudkitchen.orderstream.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Semaphore;

public class Shelves {
   private static final Logger LOGGER = LoggerFactory.getLogger(Shelves.class);

   private final ConcurrentMap<ShelfType, Shelf> shelves;
   private final Semaphore lock;

   public Shelves() {
      this.shelves = new ConcurrentHashMap<>();
      this.shelves.put(ShelfType.HOT, new Shelf(10, 1));
      this.shelves.put(ShelfType.COLD, new Shelf(10, 1));
      this.shelves.put(ShelfType.FROZEN, new Shelf(10, 1));
      this.shelves.put(ShelfType.OVERFLOW, new Shelf(15, 2));
      this.lock = new Semaphore(1);
   }

   public void acquireLock() throws InterruptedException {
      this.lock.acquire();
   }

   public void releaseLock() {
      this.lock.release();
   }

   public boolean isEmpty() {
      return shelves.get(ShelfType.HOT).isEmpty() &&
         shelves.get(ShelfType.COLD).isEmpty() &&
         shelves.get(ShelfType.FROZEN).isEmpty() &&
         shelves.get(ShelfType.OVERFLOW).isEmpty();
   }

   public void addOrder(Order order) {
      ShelfType shelfKey;
      if (!this.shelves.get(order.getType()).isFull()) {
         shelfKey = order.getType();
         addOrder(shelfKey, order);
         return;
      }

      LOGGER.warn("Shelf {} is full", order.getType());
      shelfKey = ShelfType.OVERFLOW;
      if (!this.shelves.get(ShelfType.OVERFLOW).isFull()) {
         addOrder(shelfKey, order);
         return;
      }

      LOGGER.warn("Shelf {} is full", ShelfType.OVERFLOW);

      Order orderToMove = null;
      Order orderToWaste = null;
      for (Order orderOnOverflowShelf : shelves.get(ShelfType.OVERFLOW).getOrders()) {
         if (!this.shelves.get(orderOnOverflowShelf.getType()).isFull()) {
            orderToMove = orderOnOverflowShelf;
            break;
         }

         if (orderToWaste == null || orderToWaste.getRemainingValue() > orderOnOverflowShelf.getRemainingValue()) {
            orderToWaste = orderOnOverflowShelf;
         }
      }

      if (orderToMove != null) {
         shelves.get(ShelfType.OVERFLOW).removeOrder(orderToMove.getId());
         shelves.get(orderToMove.getType()).addOrder(orderToMove);
         LOGGER.info("Moved order {} from {} to {}", orderToMove, ShelfType.OVERFLOW, orderToMove.getType());
      } else if (orderToWaste != null) {
         shelves.get(ShelfType.OVERFLOW).removeOrder(orderToWaste.getId());
         LOGGER.warn("Wasted order {}", orderToWaste);
      }

      addOrder(shelfKey, order);
   }

   private void addOrder(ShelfType type, Order order) {
      this.shelves.get(type).addOrder(order);
      LOGGER.info("Put order {} on shelf {}",order, type);
   }

   public boolean pickupOrder(UUID orderId) {
      boolean orderPickedUp = false;
      for (Shelf shelf : this.shelves.values()) {
         if (shelf.hasOrder(orderId)) {
            shelf.removeOrder(orderId);
            orderPickedUp = true;
            break;
         }
      }

      return orderPickedUp;
   }

   public void decay() throws InterruptedException {
      for (Shelf shelf : shelves.values()) {
         shelf.decay();
      }
   }
}
