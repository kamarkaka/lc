package com.kamarkaka.cloudkitchen.runnables;

import com.kamarkaka.cloudkitchen.orderstream.Order;
import com.kamarkaka.cloudkitchen.orderstream.OrderStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

public class OrderIngestor implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderIngestor.class);
    private static final int INGESTION_RATE = 2; // # of orders per sec
    private final ConcurrentLinkedQueue<Order> queue;
    private final OrderStream OrderStream;

    public OrderIngestor(ConcurrentLinkedQueue<Order> queue, OrderStream OrderStream) {
        this.queue = queue;
        this.OrderStream = OrderStream;
    }

    @Override
    public void run() {
        Order order;
        int orderCount = 0;
        while ((order = OrderStream.nextOrder()) != null) {
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
