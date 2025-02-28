package com.kamarkaka.cloudkitchen;

import com.kamarkaka.cloudkitchen.orderstream.FileOrderStream;
import com.kamarkaka.cloudkitchen.orderstream.OrderStream;
import com.kamarkaka.cloudkitchen.orderstream.Order;
import com.kamarkaka.cloudkitchen.runnables.OrderIngestor;
import com.kamarkaka.cloudkitchen.runnables.Courier;
import com.kamarkaka.cloudkitchen.runnables.Kitchen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * Challenge Prompt
 * Create a real-time system that emulates the fulfillment of delivery orders for a kitchen. The kitchen should receive
 * 2 delivery orders per second. The kitchen should instantly cook the order upon receiving it, and then place the order
 * on the best-available shelf (see Shelves section) to await pick up by a courier. Upon receiving an order, the system
 * should dispatch a courier to pick up and deliver that specific order. The courier should arrive randomly between 2-6
 * seconds later. The courier should instantly pick up the order upon arrival. Once an order is picked up, the courier
 * should instantly deliver it.
 * You can use any programming language, framework, and IDE you'd like; however, we strongly discourage the use of
 * microservices, kafka, REST APIs, RPCs, DBs, etc due to time constraints.
 * -----
 * Orders
 * You can download a JSON file of food order structures from <a href="http://bit.ly/css_dto_orders">data.json</a>.
 * Orders must be parsed from the file and ingested into your system at a rate of 2 orders per second. You are expected
 * to make your order ingestion rate configurable, so that we can test your system's behavior with different ingestion
 * rates. Each order should only be ingested once; when all orders have been consumed, your system should terminate.
 * Example:
 * "order.json"
 * {
 *     {
 *         "id": "a8cfcb76-7f24-4420-a5ba-d46dd77bdffd",
 *         "name": "Banana Split",
 *         "temp": "frozen", // Preferred shelf storage temperature,
 *         "shelfLife": 20, // Shelf wait max duration (seconds)
 *         "decayRate": 0.63 // Value deterioration modifier
 *     }
 * }
 * -----
 * Shelves
 * The kitchen pick-up area has multiple shelves to hold cooked orders at different temperatures. Each order should be
 * placed on a shelf that matches the order's temperature. If that shelf is full, an order can be placed on the overflow
 * shelf. If the overflow shelf is full, an existing order of your choosing on the overflow should be moved to an
 * allowable shelf with room. If no such move is possible, a random order from the overflow shelf should be discarded as
 * waste (and will not be available for a courier pickup).
 * -----
 * The following table details the kitchen's shelves:
 * Name           Allowable Temperatures Capacity
 * Hot shelf      hot                    10
 * Cold shelf     cold                   10
 * Frozen shelf   frozen                 10
 * Overflow shelf any temperature        15
 * -----
 * Shelf Life
 * Orders have an inherent value that will deteriorate over time, based on the order's shelfLife and decayRate fields.
 * Orders that have reached a value of zero are considered wasted: they should never be delivered and should be removed
 * from the shelf. Please display the current order value when displaying an order in your system's output.
 * -----
 * value = (shelfLife - decayRate * orderAge * shelfDecayModifier) / shelfLife
 * -----
 * Important: shelfDecayModifier is 1 for single-temperature shelves and 2 for the overflow shelf.
 */


/***
 * test cases:
 * 1. orders can be parsed from json data (x)
 * 2. orders can be added to orderQueue
 * 3. kitchen can consume orders put into orderQueue
 * 4.
 */

public class OrderFulfillment {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderFulfillment.class);
    private final ConcurrentLinkedQueue<Order> orderQueue;
    private final ConcurrentLinkedQueue<UUID> pickupQueue;
    private final Shelves shelves;

    public OrderFulfillment() {
        this.orderQueue = new ConcurrentLinkedQueue<>();
        this.pickupQueue = new ConcurrentLinkedQueue<>();
        this.shelves = new Shelves();
    }

    public void start(OrderStream OrderStream) {
        try (ExecutorService orderPool = Executors.newFixedThreadPool(3);
             ExecutorService pickupPool = Executors.newCachedThreadPool()) {
            OrderIngestor orderIngestor = new OrderIngestor(orderQueue, OrderStream);
            orderPool.execute(orderIngestor);

            Kitchen kitchen = new Kitchen(orderQueue, pickupQueue, shelves);
            orderPool.execute(kitchen);

            Courier courier = new Courier(pickupQueue, pickupPool, shelves);
            orderPool.execute(courier);

            while (true) {
                Thread.sleep(1000);

                this.shelves.acquireLock();
                this.shelves.decay();
                this.shelves.releaseLock();

                if (OrderStream.isDrained() &&
                    orderQueue.isEmpty() &&
                    pickupQueue.isEmpty() &&
                    shelves.isEmpty()) {
                    LOGGER.info("All orders are consumed!");
                    break;
                }
            }

            kitchen.close();
            courier.close();
            LOGGER.info("Threadpool shut down");
        } catch (Exception e) {
            LOGGER.error("Exception thrown", e);
        } finally {
            LOGGER.info("System shut down");
        }
    }

    public static void main(String[] args) throws IOException {
        FileOrderStream fileOrderStream = new FileOrderStream("./data/orders.json");
        OrderFulfillment fulfillment = new OrderFulfillment();
        fulfillment.start(fileOrderStream);
    }
}
