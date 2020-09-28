package com.ohlc.trading.ohlcEngine.queue;

import com.ohlc.trading.ohlcEngine.common.CommonConstants;
import com.ohlc.trading.ohlcEngine.model.Trade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class QueueMessagingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueueMessagingService.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendObMessage(Object obj, String messageType){
        LOGGER.info("### Posted <{}> ###",obj);
        switch (messageType){
            case CommonConstants.TRADE_DATA:
                LOGGER.info("### posting TRADE Pojo ###");
                Trade trade = (Trade) obj;
                jmsTemplate.convertAndSend(CommonConstants.FSM_Q, trade);
                break;

            case CommonConstants.CHART_DATA:
                LOGGER.info("### posting CHART Pojo ###");
                Trade tradesX = (Trade) obj;
                jmsTemplate.convertAndSend("WATCHER_Q", tradesX, message -> {
                    message.setStringProperty("jms-message-type", messageType);
                    return  message;
                });
                break;
        }
    }

}
