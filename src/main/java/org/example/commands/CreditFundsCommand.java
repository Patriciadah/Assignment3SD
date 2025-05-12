package org.example.commands;

import org.example.models.Trade;

public class CreditFundsCommand implements Command {
    private final String accountId;
    private final float creditedAmount;
    private final Trade trade;

    public CreditFundsCommand( String accountId, float creditedAmount, Trade trade) {
        this.accountId = accountId;
        this.creditedAmount = creditedAmount;
        this.trade = trade;
    }


    public String getAccountId() {
        return accountId;
    }

    public float getCreditedAmount() {
        return creditedAmount;
    }

    public Trade getTrade() {
        return trade;
    }
}
