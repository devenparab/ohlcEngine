package com.ohlc.trading.ohlcEngine.process.reader;

public interface WorkerReader {
    void startReadingTrade(String symbol, int intervalSecs);
}
