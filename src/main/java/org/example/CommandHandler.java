package org.example;

import org.example.commands.*;
import org.example.events.*;
import org.example.message_broker.MessageBus;

import java.util.UUID;

public class CommandHandler {
    private final EventStore eventStore;
    private final MessageBus messageBus;

    public CommandHandler(EventStore eventStore, MessageBus messageBus) {
        this.eventStore = eventStore;
        this.messageBus = messageBus;
    }

    public void handle(Command command) {
        String eventId = UUID.randomUUID().toString();
        if (command instanceof PlaceOrderCommand placeOrderCommand) {
            OrderPlacedEvent event = new OrderPlacedEvent(
                    placeOrderCommand.getOrder(),
                    eventId,
                    placeOrderCommand.getSide(),
                    placeOrderCommand.getAccountId()
            );
            publishEvent(event);
        } else if (command instanceof FundAccountCommand fundCommand) {
            FundsCreditedEvent event = new FundsCreditedEvent(
                    "fund-" + fundCommand.getAccountId() + "-" + System.currentTimeMillis(),
                    String.valueOf(fundCommand.getAccountId()),
                    (float) fundCommand.getAmount()
            );
            publishEvent(event);
        } else if (command instanceof CreditFundsCommand creditCommand) {
            FundsCreditedEvent event = new FundsCreditedEvent(
                    eventId,
                    creditCommand.getAccountId(),
                    creditCommand.getCreditedAmount(),
                    creditCommand.getTrade()
            );
            publishEvent(event);
        } else if (command instanceof DebitFundsCommand debitCommand) {
            FundsDebitedEvent event = new FundsDebitedEvent(
                    eventId,
                    debitCommand.getAccountId(),
                    debitCommand.getDebitedAmount(),
                    debitCommand.getTrade(),
                    debitCommand.getReason()
            );
            publishEvent(event);
        } else if (command instanceof ExecuteTradeCommand tradeCommand) {
            TradeExecutedEvent event = new TradeExecutedEvent(
                    eventId,
                    tradeCommand.getStreamId(),
                    tradeCommand.getTrade()
            );
            publishEvent(event);
        } else if (command instanceof CancelOrderCommand cancelCommand) {
            OrderCancelledEvent event = new OrderCancelledEvent(
                    eventId,
                    cancelCommand.getAccountId(),
                    cancelCommand.getOrder(),
                    cancelCommand.getReason()
            );
            publishEvent(event);
        }

        else {
            throw new IllegalArgumentException("Unknown command type: " + command.getClass().getSimpleName());
        }
    }

    private void publishEvent(Event event) {
        eventStore.append(event);
        messageBus.publish(event);
    }
}
