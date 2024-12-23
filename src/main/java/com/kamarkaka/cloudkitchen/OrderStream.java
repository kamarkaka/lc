package com.kamarkaka.cloudkitchen;

public interface OrderStream {
    Order nextOrder();
    boolean isDrained();
}
