package com.kamarkaka.cloudkitchen.runnables;

import com.kamarkaka.cloudkitchen.orderstream.Order;
import com.kamarkaka.cloudkitchen.Shelves;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;

public class Courier implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Courier.class);

    private final ConcurrentLinkedQueue<UUID> pickupQueue;
    private final ExecutorService pickupPool;
    private final Shelves shelves;

    private boolean shouldEnd;

    public Courier(ConcurrentLinkedQueue<UUID> pickupQueue,
                   ExecutorService pickupPool,
                   Shelves shelves) {
        this.pickupQueue = pickupQueue;
        this.pickupPool = pickupPool;
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
            if (this.pickupQueue.isEmpty()) continue;

            UUID orderId = this.pickupQueue.poll();
            pickupPool.execute(() -> {
                try {
                    int delay = (int) Math.round(Math.random() * 4 + 2);
                    Thread.sleep(Duration.of(delay, ChronoUnit.SECONDS));

                    this.shelves.acquireLock();
                    boolean orderPickedUp = this.shelves.pickupOrder(orderId);
                    this.shelves.releaseLock();

                    if (orderPickedUp) {
                        LOGGER.info("Courier picked up order {}", orderId);
                    } else {
                        LOGGER.warn("Courier did not find order {}", orderId);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
