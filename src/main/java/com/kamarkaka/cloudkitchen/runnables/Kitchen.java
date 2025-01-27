package com.kamarkaka.cloudkitchen.runnables;

import com.kamarkaka.cloudkitchen.orderstream.Order;
import com.kamarkaka.cloudkitchen.Shelves;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

public class Kitchen implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Kitchen.class);
    private final ConcurrentLinkedQueue<Order> orderQueue;
    private final ConcurrentLinkedQueue<UUID> pickupQueue;
    private final Shelves shelves;

    private boolean shouldEnd;

    public Kitchen(ConcurrentLinkedQueue<Order> orderQueue,
                   ConcurrentLinkedQueue<UUID> pickupQueue,
                   Shelves shelves) {
        this.orderQueue = orderQueue;
        this.pickupQueue = pickupQueue;
        this.shelves = shelves;
        this.shouldEnd = false;
    }

    public void close() {
        this.shouldEnd = true;
    }

    @Override
    public void run() {
        while (true) {
            if (this.shouldEnd) break;
            if (this.orderQueue.isEmpty()) continue;

            Order order = this.orderQueue.poll();
            LOGGER.info("Kitchen gets order {}", order);
            LOGGER.info("Kitchen is processing order {}", order);
            LOGGER.info("Kitchen completed order {}", order);
            LOGGER.info("Order {} is ready for pickup", order);
            this.pickupQueue.add(order.getId());

            try {
                this.shelves.acquireLock();
                this.shelves.addOrder(order);
                this.shelves.releaseLock();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
