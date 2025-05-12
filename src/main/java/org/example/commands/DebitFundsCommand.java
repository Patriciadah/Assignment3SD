package org.example.commands;

import org.example.models.Trade;

public class DebitFundsCommand implements Command {

    private final String accountId;
    private final float debitedAmount;
    private final Trade trade;
    private final String reason;

    public DebitFundsCommand(String accountId, float debitedAmount, Trade trade, String reason) {

        this.accountId = accountId;
        this.debitedAmount = debitedAmount;
        this.trade = trade;
        this.reason = reason;
    }

    public String getAccountId() {
        return accountId;
    }

    public float getDebitedAmount() {
        return debitedAmount;
    }

    public Trade getTrade() {
        return trade;
    }

    public String getReason() {
        return reason;
    }
}
