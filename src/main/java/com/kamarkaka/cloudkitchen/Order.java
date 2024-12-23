package com.kamarkaka.cloudkitchen;

import java.time.LocalDateTime;
import java.util.UUID;

public class Order {
    private final UUID id;
    private final String name;
    private final Temperature temp;
    private final float shelfLife;
    private final float decayRate;
    private final LocalDateTime created;
    private LocalDateTime updated;
    private float remainingValue;

    public Order(UUID id, String name, Temperature temp, float shelfLife, float decayRate) {
        this.id = id;
        this.name = name;
        this.temp = temp;
        this.shelfLife = shelfLife;
        this.decayRate = decayRate;
        this.created = LocalDateTime.now();
        this.updated = this.created;
        this.remainingValue = shelfLife;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Temperature getTemp() {
        return temp;
    }

    public float getShelfLife() {
        return shelfLife;
    }

    public float getDecayRate() {
        return decayRate;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() { return updated; }
    public void setUpdated() { updated = LocalDateTime.now(); }

    public float getRemainingValue() {
        return remainingValue;
    }

    public void decay(float decay) {
        remainingValue -= decay;
        updated = LocalDateTime.now();
    }

    public static Order convertFromJson(JsonOrder jsonOrder) {
        return new Order(
                UUID.fromString(jsonOrder.id),
                jsonOrder.name,
                Temperature.valueOf(jsonOrder.temp.toUpperCase()),
                jsonOrder.shelfLife,
                jsonOrder.decayRate
        );
    }

    @Override
    public String toString() {
        return "{id: " + id + ", " +
                "name: " + name + ", " +
                "temp: " + temp + ", " +
                "value: " + remainingValue + "}";
    }
}