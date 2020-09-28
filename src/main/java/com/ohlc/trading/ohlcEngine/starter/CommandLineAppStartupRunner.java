package com.ohlc.trading.ohlcEngine.starter;

import com.ohlc.trading.ohlcEngine.process.reader.WorkerReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    @Autowired
    private WorkerReader workerReader;

    @Override
    public void run(String... args) throws Exception {
        workerReader.startReadingTrade();
    }
}
