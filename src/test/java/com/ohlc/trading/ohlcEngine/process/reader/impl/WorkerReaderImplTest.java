package com.ohlc.trading.ohlcEngine.process.reader.impl;

import com.ohlc.trading.ohlcEngine.process.reader.WorkerReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class WorkerReaderImplTest {

    @Autowired
    WorkerReader workerReader;

    @Test
    void startReadingTrade() {
        try {
            String symbol = "XXBTZUSD";
            int intervalSecs = 15;
            workerReader.startReadingTrade(symbol, intervalSecs);
            Assertions.assertTrue(true);
        } catch (Exception e) {
            Assertions.fail(e);
        }

    }
}