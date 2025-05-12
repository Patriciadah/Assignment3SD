package org.example.models;

import java.util.Random;

public class Order {
    private static final Random random = new Random();
    private final String id;
    private final OrderType type;
    private final int amount;

    public Order(String orderId,OrderType type,int amount){
    this.id=orderId;
    this.type=type;
    this.amount=amount;

    }

    public String getId() {
        return id;
    }


    public OrderType getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }


    public String getOrderId() {
        return id;
    }
}
