package com.kamarkaka.cloudkitchen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Semaphore;

public class Kitchen implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Kitchen.class);
    private final ConcurrentLinkedQueue<Order> orderQueue;
    private final ConcurrentLinkedQueue<Order> pickupQueue;
    private final ConcurrentMap<Temperature, Shelf> shelves;
    private final Semaphore shelfSemaphore;

    public Kitchen(ConcurrentLinkedQueue<Order> orderQueue,
                   ConcurrentLinkedQueue<Order> pickupQueue,
                   ConcurrentMap<Temperature, Shelf> shelves,
                   Semaphore shelfSemaphore) {
        this.orderQueue = orderQueue;
        this.pickupQueue = pickupQueue;
        this.shelves = shelves;
        this.shelfSemaphore = shelfSemaphore;
    }

    @Override
    public void run() {
        while (true) {
            if (this.orderQueue.isEmpty()) {
                continue;
            }

            Order order = this.orderQueue.poll();
            LOGGER.info("Kitchen gets order {}", order);
            LOGGER.info("Kitchen is processing order {}", order);
            LOGGER.info("Kitchen completed order {}", order);
            LOGGER.info("Order {} is ready for pickup", order);
            this.pickupQueue.add(order);

            try {
                this.shelfSemaphore.acquire();
                Temperature shelfKey;
                if (this.shelves.get(order.getTemp()).isFull()) {
                    LOGGER.warn("Shelf {} is full", order.getTemp());
                    shelfKey = Temperature.OVERFLOW;

                    if (this.shelves.get(Temperature.OVERFLOW).isFull()) {
                        LOGGER.warn("Shelf {} is full", Temperature.OVERFLOW);

                        Order orderToMove = null;
                        Order orderToWaste = null;
                        for (Order orderOnOverflowShelf : shelves.get(Temperature.OVERFLOW).getOrders()) {
                            if (orderToWaste == null || orderToWaste.getRemainingValue() > orderOnOverflowShelf.getRemainingValue()) {
                                orderToWaste = orderOnOverflowShelf;
                            }

                            if (this.shelves.get(orderOnOverflowShelf.getTemp()).isFull()) {
                                continue;
                            }
                            orderToMove = orderOnOverflowShelf;
                            break;
                        }

                        if (orderToMove != null) {
                            shelves.get(Temperature.OVERFLOW).removeOrder(orderToMove);
                            shelves.get(orderToMove.getTemp()).addOrder(orderToMove);
                            LOGGER.info("Moved order {} from {} to {}", orderToMove, Temperature.OVERFLOW, orderToMove.getTemp());
                        } else if (orderToWaste != null) {
                            shelves.get(Temperature.OVERFLOW).removeOrder(orderToWaste);
                            LOGGER.warn("Wasted order {}", orderToWaste);
                        }
                    }
                } else {
                    shelfKey = order.getTemp();
                }

                this.shelves.get(shelfKey).addOrder(order);
                LOGGER.info("Put order {} on shelf {}",order, shelfKey);
                this.shelfSemaphore.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
