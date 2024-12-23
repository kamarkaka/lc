package com.kamarkaka.cloudkitchen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;

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

public class OrderFulfillment {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderFulfillment.class);
    private final ConcurrentLinkedQueue<Order> orderQueue;
    private final ConcurrentLinkedQueue<Order> pickupQueue;
    private final Semaphore shelfSemaphore;
    private final ConcurrentMap<Temperature, Shelf> shelves;

    public OrderFulfillment() {
        this.orderQueue = new ConcurrentLinkedQueue<>();
        this.pickupQueue = new ConcurrentLinkedQueue<>();
        this.shelfSemaphore = new Semaphore(1);
        this.shelves = new ConcurrentHashMap<>();

        this.shelves.put(Temperature.HOT, new Shelf(Temperature.HOT, 10, 1));
        this.shelves.put(Temperature.COLD, new Shelf(Temperature.COLD, 10, 1));
        this.shelves.put(Temperature.FROZEN, new Shelf(Temperature.FROZEN, 10, 1));
        this.shelves.put(Temperature.OVERFLOW, new Shelf(Temperature.OVERFLOW, 15, 2));
    }

    public void start(OrderStream orderStream) {
        try (ExecutorService orderPool = Executors.newFixedThreadPool(3);
             ExecutorService pickupPool = Executors.newCachedThreadPool()) {
            OrderIngestor orderIngestor = new OrderIngestor(orderQueue, orderStream);
            orderPool.execute(orderIngestor);

            Kitchen kitchen = new Kitchen(orderQueue, pickupQueue, shelves, shelfSemaphore);
            orderPool.execute(kitchen);

            Courier courier = new Courier(pickupQueue, pickupPool, shelves, shelfSemaphore);
            orderPool.execute(courier);

            while (true) {
                Thread.sleep(1000);

                shelfSemaphore.acquire();
                for (Shelf shelf : shelves.values()) {
                    shelf.decay();
                }
                shelfSemaphore.release();

                if (orderStream.isDrained() &&
                    orderQueue.isEmpty() &&
                    pickupQueue.isEmpty() &&
                    shelves.get(Temperature.HOT).isEmpty() &&
                    shelves.get(Temperature.COLD).isEmpty() &&
                    shelves.get(Temperature.FROZEN).isEmpty() &&
                    shelves.get(Temperature.OVERFLOW).isEmpty()) {
                    LOGGER.info("All orders are consumed!");
                    break;
                }
            }

            orderPool.shutdown();
            pickupPool.shutdown();
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
        System.exit(0);
    }
}
