package com.kamarkaka.cloudkitchen.runnables;

import com.kamarkaka.cloudkitchen.orderstream.FileOrderStream;
import com.kamarkaka.cloudkitchen.orderstream.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class OrderIngesterTest {
   private ConcurrentLinkedQueue<Order> orderQueue;

   @BeforeEach
   public void beforeEach() {
      this.orderQueue = new ConcurrentLinkedQueue<>();
   }

   @Test
   public void testEnqueue() throws IOException {
      FileOrderStream orderStream = new FileOrderStream("./data/orders-short.json");
      OrderIngestor ingestor = new OrderIngestor(orderQueue, orderStream);

      ingestor.run();

      Assertions.assertEquals(1, orderQueue.size());
   }
}
