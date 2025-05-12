package org.example.commands;

import org.example.models.Trade;

public class ExecuteTradeCommand implements Command {

    private final String streamId;
    private final Trade trade;

    public ExecuteTradeCommand( String streamId, Trade trade) {
        this.streamId = streamId;
        this.trade = trade;
    }
    public String getStreamId() {
        return streamId;
    }

    public Trade getTrade() {
        return trade;
    }
}
