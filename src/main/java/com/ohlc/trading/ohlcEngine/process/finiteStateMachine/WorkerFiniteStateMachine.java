package com.ohlc.trading.ohlcEngine.process.finiteStateMachine;

import com.ohlc.trading.ohlcEngine.model.Trades;

public interface WorkerFiniteStateMachine {
    void computeFiniteStateMachine(Trades trades);
}
