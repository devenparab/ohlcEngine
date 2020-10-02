package com.ohlc.trading.ohlcEngine.process.reader.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohlc.trading.ohlcEngine.exception.TradeInfoException;
import com.ohlc.trading.ohlcEngine.model.Trade;
import com.ohlc.trading.ohlcEngine.process.reader.WorkerReader;
import com.ohlc.trading.ohlcEngine.process.segregator.TradesSegregator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    /*@Autowired
    QueueMessagingService queueMessagingService;*/

    @Autowired
    TradesSegregator dataSegegator;

    /**
     * read based on the user passed the Symbol value and will only look for that Symbol and interval
     * @param symbol
     * @param intervalSecs
     */
    @Override
    public void startReadingTrade(String symbol, int intervalSecs) {
        LOGGER.info("## Starting WorkerReaderImpl.java >> startReadingTrade() ##");
        InputStream inputStream;
        //Resource resource = resourceLoader.getResource("classpath:tradeData/trades.json");
        Resource resource = resourceLoader.getResource("classpath:tradeData/tradesSmall.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            inputStream = resource.getInputStream();//getClass().getClassLoader().getResourceAsStream("src/main/resources/tradeData/trades.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while(reader.ready()) {
                String singleTradeJsonObj = reader.readLine();
                //LOGGER.info("### Line ### "+singleTradeJsonObj);
                Trade trade = mapper.readValue(singleTradeJsonObj, Trade.class);
                if (trade.getSym().equals(symbol)) {
                    //LOGGER.info("### Pojo ### "+trade.toString());
                    dataSegegator.sliceTradesBasedOnInterval(trade, intervalSecs);
                    //queueMessagingService.sendObjMessage(trade, CommonConstants.TRADE_DATA);
                }
            }
            //Collections.sort(subscribedList, Comparator.comparing(Trade::gettS2));
            //Collections.sort(subscribedList, Comparator.comparing(Trade::gettS2_formattedTime));
        } catch (IOException e) {
            LOGGER.error("### Exception of type [{}] occurred in [{}] >> startReadingTrade() ###",e.getClass().getCanonicalName(), this.getClass(),e);
            throw new TradeInfoException("Invalid trade found when reading Trades info.");
        }
        LOGGER.info("## Finished WorkerReaderImpl.java >> startReadingTrade() ##");
    }
}
