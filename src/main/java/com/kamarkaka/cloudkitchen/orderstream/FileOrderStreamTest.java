package com.kamarkaka.cloudkitchen.orderstream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class FileOrderStreamTest {
   @Test
   public void testParseOrders() throws IOException {
      FileOrderStream fos = new FileOrderStream("./data/orders-short.json");
      Order order = fos.nextOrder();
      Assertions.assertEquals("a8cfcb76-7f24-4420-a5ba-d46dd77bdffd", order.getId().toString());
      Assertions.assertTrue(fos.isDrained());
   }
}
