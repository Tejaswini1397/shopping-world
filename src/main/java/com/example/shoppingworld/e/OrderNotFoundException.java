package com.example.shoppingworld.e;

public class OrderNotFoundException extends Throwable {
    public OrderNotFoundException(String orderNotFoundWithOrderId) {
        super(orderNotFoundWithOrderId);

    }
}
