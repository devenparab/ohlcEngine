package com.ohlc.trading.ohlcEngine.process.reader.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohlc.trading.ohlcEngine.common.CommonConstants;
import com.ohlc.trading.ohlcEngine.model.Trades;
import com.ohlc.trading.ohlcEngine.process.reader.WorkerReader;
import com.ohlc.trading.ohlcEngine.queue.QueueMessagingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class WorkerReaderImpl implements WorkerReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerReaderImpl.class);

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    QueueMessagingService queueMessagingService;

    @Override
    public void startReadingTrade() {
        System.out.println("## startReadingTrade ##");
        InputStream inputStream;
        Resource resource = resourceLoader.getResource("classpath:tradeData/trades.json");
        ObjectMapper mapper = new ObjectMapper();
        //mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            inputStream = resource.getInputStream();//getClass().getClassLoader().getResourceAsStream("src/main/resources/tradeData/trades.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while(reader.ready()) {
                String singleTradeJson = reader.readLine();
                LOGGER.info("### Line ### "+singleTradeJson);
                Trades trade = mapper.readValue(singleTradeJson, Trades.class);
                LOGGER.info("### Pojo ### "+trade.toString());
                queueMessagingService.sendObMessage(trade, CommonConstants.TRADE_DATA);
            }
        } catch (IOException e) {
            LOGGER.error("### Exception of type [{}] occurred in [{}] >> startReadingTrade() ###",e.getClass().getCanonicalName(), this.getClass(),e);
        }
    }
}
