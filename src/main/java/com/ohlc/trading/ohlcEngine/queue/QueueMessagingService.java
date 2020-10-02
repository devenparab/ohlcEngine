package com.ohlc.trading.ohlcEngine.queue;

import com.ohlc.trading.ohlcEngine.model.BarChartDataWrapper;
import com.ohlc.trading.ohlcEngine.model.TradesInfo;
import com.ohlc.trading.ohlcEngine.queue.types.QueueName;
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

    public void sendObjMessage(String queueName, Object obj){
        //LOGGER.info("### Posted <{}> ###",obj);
        switch (queueName){
            case QueueName.FSM_Q:
                //LOGGER.info("### posting SLICED_TRADE_DATA Pojo ###");
                TradesInfo tradesInfo = (TradesInfo) obj;
                jmsTemplate.convertAndSend(queueName, tradesInfo);
                break;

            case QueueName.CHART_Q:
                //LOGGER.info("### posting CHART Pojo ###");
                BarChartDataWrapper barChartDataWrapper = (BarChartDataWrapper) obj;
                jmsTemplate.convertAndSend(queueName, barChartDataWrapper);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + queueName);
        }
    }

}
