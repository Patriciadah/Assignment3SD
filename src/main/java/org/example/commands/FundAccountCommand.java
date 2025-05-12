package org.example.commands;

public class FundAccountCommand implements Command {
    private final int accountId;
    private final double amount;

    public FundAccountCommand(int accountId, double amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public int getAccountId() {
        return accountId;
    }

    public double getAmount() {
        return amount;
    }
}
