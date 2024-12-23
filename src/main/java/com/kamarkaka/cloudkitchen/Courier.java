package com.kamarkaka.cloudkitchen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;

public class Courier implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Courier.class);

    private final ConcurrentLinkedQueue<Order> pickupQueue;
    private final ExecutorService pickupPool;
    private final ConcurrentMap<Temperature, Shelf> shelves;
    private final Semaphore shelfSemaphore;

    public Courier(ConcurrentLinkedQueue<Order> pickupQueue,
                   ExecutorService pickupPool,
                   ConcurrentMap<Temperature, Shelf> shelves,
                   Semaphore shelfSemaphore) {
        this.pickupQueue = pickupQueue;
        this.pickupPool = pickupPool;
        this.shelves = shelves;
        this.shelfSemaphore = shelfSemaphore;
    }

    @Override
    public void run() {
        while (true) {
            if (this.pickupQueue.isEmpty()) {
                continue;
            }

            Order order = this.pickupQueue.poll();
            pickupPool.execute(() -> {
                try {
                    int delay = (int)(Math.random() * 4 + 2);
                    Thread.sleep(Duration.of(delay, ChronoUnit.SECONDS));

                    this.shelfSemaphore.acquire();
                    boolean orderPickedUp = false;
                    for (Shelf shelf : shelves.values()) {
                        if (shelf.hasOrder(order)) {
                            shelf.removeOrder(order);
                            LOGGER.info("Courier picked up order {}", order);
                            orderPickedUp = true;
                            break;
                        }
                    }
                    if (!orderPickedUp) {
                        LOGGER.warn("Courier did not find order {}", order);
                    }
                    this.shelfSemaphore.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
