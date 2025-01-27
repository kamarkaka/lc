package com.kamarkaka.cloudkitchen.orderstream;

public interface OrderStream {
    Order nextOrder();
    boolean isDrained();
}
