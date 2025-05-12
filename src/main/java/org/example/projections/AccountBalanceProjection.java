package org.example.projections;

import org.example.events.Event;
import org.example.events.FundsCreditedEvent;
import org.example.events.FundsDebitedEvent;

import java.util.HashMap;
import java.util.Map;

public class AccountBalanceProjection {
    private final Map<String, Double> balances = new HashMap<>();

    public void apply(Event event) {
        if (event instanceof FundsDebitedEvent) {
            FundsDebitedEvent e = (FundsDebitedEvent) event;
            balances.put(e.getAccountId(), balances.getOrDefault(e.getAccountId(), 0.0) - e.getDebitedAmount());
        } else if (event instanceof FundsCreditedEvent) {
            FundsCreditedEvent e = (FundsCreditedEvent) event;
            balances.put(e.getAccountId(), balances.getOrDefault(e.getCreditedAmount(), 0.0) + e.getCreditedAmount());
        }
    }

    public double getBalance(String accountId) {
        return balances.getOrDefault(accountId, 0.0);
    }

}
