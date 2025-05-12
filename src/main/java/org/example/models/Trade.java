package org.example.models;

import org.example.models.Order;

public class Trade {

    private final Order BuyOrder;
    private final Order SellOrder;
    private float amount;

    public Trade(Order buyOrder, Order sellOrder) {

        BuyOrder = buyOrder;
        SellOrder = sellOrder;
        //Assumes the buy order was matched so the same amount remains
        this.amount= buyOrder.getAmount();
    }


    public Order getBuyOrder() {
        return BuyOrder;
    }

    public Order getSellOrder() {
        return SellOrder;
    }
    public float getAmount(){
        return amount;
    }
}
