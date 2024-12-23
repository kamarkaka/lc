package com.kamarkaka.cloudkitchen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Shelf {
    private static final Logger LOGGER = LoggerFactory.getLogger(Shelf.class);
    private final Temperature temp;
    private final int capacity;
    private final int shelfDecayModifier;
    private final ConcurrentMap<UUID, Order> orders;

    public Shelf(Temperature temp, int capacity, int shelfDecayModifier) {
        this.temp = temp;
        this.capacity = capacity;
        this.shelfDecayModifier = shelfDecayModifier;
        this.orders = new ConcurrentHashMap<>();
    }

    public boolean isEmpty() {
        return this.orders.isEmpty();
    }

    public boolean isFull() {
        return this.orders.size() >= this.capacity;
    }

    public boolean hasOrder(Order order) {
        return this.orders.containsKey(order.getId());
    }

    public void addOrder(Order order) {
        this.orders.put(order.getId(), order);
        order.setUpdated();
    }

    public void removeOrder(Order order) {
        this.orders.remove(order.getId());
    }

    public Collection<Order> getOrders() {
        return this.orders.values();
    }

    public void decay() throws InterruptedException {
        for (Order order : this.orders.values()) {
            Duration age = Duration.between(order.getUpdated(), LocalDateTime.now());
            float decayValue = order.getDecayRate() * age.getSeconds() * this.shelfDecayModifier;
            order.decay(decayValue);

            if (order.getRemainingValue() <= 0) {
                LOGGER.warn("Order {} wasted!", order);
                this.orders.remove(order.getId());
            }
        }
    }
}
