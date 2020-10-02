package com.ohlc.trading.ohlcEngine.process.segregator;

import com.ohlc.trading.ohlcEngine.model.Trade;

public interface TradesSegregator {
    void sliceTradesBasedOnInterval(Trade trade, long intervalSecs);
}
