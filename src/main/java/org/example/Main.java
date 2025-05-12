package org.example;

import org.example.commands.*;
import org.example.events.Event;
import org.example.message_broker.InMemoryMessageBus;
import org.example.models.Order;
import org.example.models.OrderType;
import org.example.models.Trade;
import org.example.projections.AccountBalanceProjection;
import org.example.projections.OrderBookProjection;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Setup
        EventStore eventStore = new EventStore();
        InMemoryMessageBus messageBus = new InMemoryMessageBus();

        OrderBookProjection orderBookProjection = new OrderBookProjection();
        AccountBalanceProjection accountBalanceProjection = new AccountBalanceProjection();

        EventProcessor processor = new EventProcessor(orderBookProjection, accountBalanceProjection);

        // 2. Subscribe projections to events
        messageBus.subscribe("OrderPlacedEvent", processor::process);
        messageBus.subscribe("OrderCancelledEvent", processor::process);
        messageBus.subscribe("TradeExecutedEvent", processor::process);
        messageBus.subscribe("FundsCreditedEvent", processor::process);
        messageBus.subscribe("FundsDebitedEvent", processor::process);

        // 3. Command handler
        CommandHandler commandHandler = new CommandHandler(eventStore, messageBus);

        // 4. Simulate commands that produce all 5 event types

        // --- FUNDS CREDITED EVENT (Funding account 1 with 1000.0) ---
        commandHandler.handle(new FundAccountCommand(1, 1000.0));

        // --- ORDER PLACED EVENT (Buy and Sell Orders) ---
        Order buyOrder = new Order("B1", OrderType.BuyOrder, 100);
        Order sellOrder = new Order("S1", OrderType.SellOrder, 100);
        Order buyOrder2 = new Order("C2", OrderType.BuyOrder, 200);

        commandHandler.handle(new PlaceOrderCommand(buyOrder,  "BUY", "1")); // accountId = "1"
        commandHandler.handle(new PlaceOrderCommand(sellOrder,  "SELL", "2")); // accountId = "2"

        commandHandler.handle(new PlaceOrderCommand(buyOrder2,  "BUY", "1")); // accountId = "1"

        SimpleTradeMatcher matcher = new SimpleTradeMatcher(orderBookProjection);
        Trade trade = matcher.tryMatch();
        if (trade != null) {
            commandHandler.handle(new ExecuteTradeCommand( "stream", trade));
            commandHandler.handle(new DebitFundsCommand( "1", trade.getAmount(), trade, "Match"));
            commandHandler.handle(new CreditFundsCommand( "2", trade.getAmount(), trade));
        } else {
            System.out.println("No matching orders found.");
        }


        // --- ORDER CANCELLED EVENT ---
       // commandHandler.handle(new CancelOrderCommand("evt-cancel", "1", buyOrder, "Buyer cancelled before match"));

        // 5. Simulate replay of all events
        System.out.println("\n--- REPLAYING EVENTS ---");


        OrderBookProjection replayedOrderBook = new OrderBookProjection();
        AccountBalanceProjection replayedAccountBalance = new AccountBalanceProjection();
        EventProcessor replayProcessor = new EventProcessor(replayedOrderBook, replayedAccountBalance);

        List<Event> storedEvents = eventStore.getAllEvents();
        for (Event e : storedEvents) {
            System.out.println(e.getType() + " - " + e.getOccurredOn());
            replayProcessor.process(e);
        }

        // 6. Display state after replay
        System.out.println("\n--- Final State After Replay ---");

        System.out.println("Active Orders:");
        for (var evt : replayedOrderBook.getActiveOrders()) {
            System.out.println("- OrderId: " + evt.getOrderId() + ", Side: " + evt.getSide() + ", AccountId: " + evt.getOrderBookId());
        }

        System.out.println("Account 1 balance: " + replayedAccountBalance.getBalance("1"));
        System.out.println("Account 2 balance: " + replayedAccountBalance.getBalance("2"));
    }
}
