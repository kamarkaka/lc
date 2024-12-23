package com.kamarkaka.cloudkitchen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentLinkedQueue;

public class OrderIngestor implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderIngestor.class);
    private static final int INGESTION_RATE = 2; // # of orders per sec
    private final ConcurrentLinkedQueue<Order> queue;
    private final OrderStream orderStream;

    public OrderIngestor(ConcurrentLinkedQueue<Order> queue, OrderStream orderStream) {
        this.queue = queue;
        this.orderStream = orderStream;
    }

    @Override
    public void run() {
        Order order;
        int orderCount = 0;
        while ((order = orderStream.nextOrder()) != null) {
            queue.add(order);
            LOGGER.info("Received order: {}", order);

            orderCount++;

            if (orderCount == INGESTION_RATE) {
                orderCount = 0;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        LOGGER.info("No more orders");
    }
}
