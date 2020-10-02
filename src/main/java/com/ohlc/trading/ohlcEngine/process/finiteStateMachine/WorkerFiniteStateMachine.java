package com.ohlc.trading.ohlcEngine.process.finiteStateMachine;

import com.ohlc.trading.ohlcEngine.model.TradesInfo;

public interface WorkerFiniteStateMachine {
    void computeFiniteStateMachine(TradesInfo tradesInfo);
}
