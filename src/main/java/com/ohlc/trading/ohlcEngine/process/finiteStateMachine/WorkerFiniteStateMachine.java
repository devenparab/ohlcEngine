package com.ohlc.trading.ohlcEngine.process.finiteStateMachine;

import com.ohlc.trading.ohlcEngine.model.Trade;

public interface WorkerFiniteStateMachine {
    void computeFiniteStateMachine(Trade trade);
}
